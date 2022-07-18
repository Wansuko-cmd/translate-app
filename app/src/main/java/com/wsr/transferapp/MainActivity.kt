package com.wsr.transferapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wsr.transferapp.databinding.ActivityMainBinding
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val client = HttpClient(Android.create()) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main.xmlに書いてあるViewを取ってきて変数に入れる
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // ↑で取ってきた内容の最上位要素を画面に表示する
        setContentView(binding.root)

        // Controllerを作成
        val mainEpoxyController = MainEpoxyController()
        mainEpoxyController.setData(viewModel.history)

        // RecyclerViewに設定
        binding.recyclerView.apply {
            // 要素のサイズが変わらないということを設定（少し早くなる）
            setHasFixedSize(true)

            // どのように表示されるのかをコントロールする「adapter」を設定
            // 本来はこれを自力で作成するが、Epoxyを使うことで勝手にいい感じに生成される
            adapter = mainEpoxyController.adapter
        }

        // Buttonを押したときの挙動を定義
        binding.encryptButton.setOnClickListener {

            // 入力欄の文字列を取得
            val inputText = binding.editText.text

            // 暗号化
            val translatedText = inputText.toString()
                .map { it.code }
                .map { it.xor(10) }
                .map { it.toChar() }
                .toCharArray()
                .fold("") { acc, c -> acc + c }

            // 変換した文字列を出力欄に代入
            binding.textView.text = translatedText

            // 履歴に文字列を追加
            viewModel.history.add(translatedText)

            // EpoxyControllerに表示する文字列を通達
            mainEpoxyController.setData(viewModel.history)
        }

        // Buttonを押したときの挙動を定義
        binding.englishButton.setOnClickListener {

            // 非同期処理であるため、別の場所で実行する
            lifecycleScope.launch {
                // 入力欄の文字列を取得
                val inputText = binding.editText.text

                // 英語に変換
                val translatedText = client.get("") {
                    parameter("text", inputText)
                    parameter("source", "ja")
                    parameter("target", "en")
                }.body<EnglishResponse>().text

                // 変換した文字列を出力欄に代入
                binding.textView.text = translatedText

                // 履歴に文字列を追加
                viewModel.history.add(translatedText)

                // EpoxyControllerに表示する文字列を通達
                mainEpoxyController.setData(viewModel.history)
            }
        }

        binding.copyButton.setOnClickListener {
            val clipboardManager = applicationContext
                .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.textView.text))
            Toast.makeText(applicationContext, "コピーしました", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}
