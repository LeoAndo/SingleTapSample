package com.makilette.singletapsample

import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(listener: View.OnClickListener) {
    val clickDisablePeriod = 2000L // クリック無効期間
    var lastClickTime = 0L
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < clickDisablePeriod) {
            Log.d("--ando--", "クリック無効です")
            return@setOnClickListener
        }
        Log.d("--ando--", "通常のクリックイベント処理を行う")
        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(it)
    }
}

// https://github.com/ApturiCOVID/apturicovid-android/blob/master/app/src/main/java/lv/spkc/apturicovid/extension/View.kt#L29
fun View.setOnDebounceClickListener(debounceTime: Long = 2000L, onClickAction: () -> Unit) {
    setOnClickListener(object : DebouncedClickListener(debounceTime) {
        override fun performClick(v: View) {
            Log.d("--ando--", "通常のクリックイベント処理を行う")
            onClickAction.invoke()
        }
    })
}