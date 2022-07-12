package com.wsr.transferapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wsr.transferapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val history = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main.xmlに書いてあるViewを取ってきて変数に入れる
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // ↑で取ってきた内容の最上位要素を画面に表示する
        setContentView(binding.root)

        // Controllerを作成
        val mainEpoxyController = MainEpoxyController()

        // RecyclerViewに設定
        binding.recyclerView.apply {
            // 要素のサイズが変わらないということを設定（少し早くなる）
            setHasFixedSize(true)

            // どのように表示されるのかをコントロールする「adapter」を設定
            // 本来はこれを自力で作成するが、Epoxyを使うことで勝手にいい感じに生成される
            adapter = mainEpoxyController.adapter
        }

        // Buttonを押したときの挙動を定義
        binding.button.setOnClickListener {

            // 入力欄の文字列を取得
            val inputText = binding.editText.text

            // 猛虎弁に変換
            val translatedText = inputText.toString()
                .replace("。", "やで。")
                .replace("！", "やで！")
                .replace("？", "やで？")
                .replace("\n", "やで。")

            // 変換した文字列を出力欄に代入
            binding.textView.text = translatedText

            // 履歴に文字列を追加
            history.add(translatedText)

            // EpoxyControllerに表示する文字列を通達
            mainEpoxyController.setData(history)
        }
    }
}
