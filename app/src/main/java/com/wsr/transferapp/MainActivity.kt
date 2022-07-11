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

        // ↑のViewの中の「text_view」のidがついている要素の文字を「Kotlin」に変更する
        binding.textView.text = "Kotlin"
    }
}
