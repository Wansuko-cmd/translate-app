package com.wsr.transferapp;

import com.airbnb.epoxy.EpoxyDataBindingPattern;

// 名前が「model」から始まる全てのlayoutをEpoxyに認識させる
@EpoxyDataBindingPattern(rClass = R.class, layoutPrefix = "model")
interface EpoxyConfig {
}