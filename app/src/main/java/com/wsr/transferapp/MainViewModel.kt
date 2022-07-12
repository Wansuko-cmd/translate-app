package com.wsr.transferapp

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // 履歴
    val history = mutableListOf<String>()
}
