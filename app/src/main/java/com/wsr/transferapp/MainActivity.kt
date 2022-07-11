package com.wsr.transferapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wsr.transferapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main.xmlに書いてあるViewを取ってきて変数に入れる
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // ↑で取ってきた内容の最上位要素を画面に表示する
        setContentView(binding.root)

        // Buttonを押したときの挙動を定義
        binding.button.setOnClickListener {

            // 入力欄の文字列を取得
            val newText = binding.editText.text

            // 入力欄の文字列を出力欄に代入
            binding.textView.text = newText
        }
    }
}
