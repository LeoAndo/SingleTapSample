package com.makilette.singletapsample

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

/**
 * 連続タップ制御: onTouchEvent.
 *
 * 2019-07-01 21:01:26.622 25251-25251/com.makilette.singletapsample D/--ando--: action is ACTION_UP
 * 2019-07-01 21:01:26.631 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- onClick id: 2131165218
 * 2019-07-01 21:01:26.631 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- タップ制御: onTouchEvent
 * 2019-07-01 21:01:28.334 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- startActivity
 * 2019-07-01 21:01:28.355 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:01:28.359 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:01:28.374 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:01:28.377 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 *
 * 検証端末:
 * Google Pixcel3
 * OS: 9
 */
class CustomAppCompatButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {

    private var mLastClickTime: Long = 0
    /** クリック無効期間. */
    private var mClickDisablePeriod: Long = 2000

    /**
     * [android.view.View.onTouchEvent]
     *
     * @return true: 消化. false: 消化しないで、後続処理(click event等)を行う.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 一定期間、クリックイベント発火させない.
        if (SystemClock.elapsedRealtime() - mLastClickTime < mClickDisablePeriod) {
            Log.d("--ando--", "Disable click for a certain period.")
            // trueで、「TouchEventを消化」したことになり、クリックイベント発火させない.
            // true: 消化. false: 消化しないで、後続処理(click event等)を行う.
            return true
        }

        // Viewのタッチアップ後、一定期間onClickを発火させないでダブルタップを抑止する.
        if (event.action == MotionEvent.ACTION_UP) {
            Log.d("--ando--", "action is ACTION_UP")

            mLastClickTime = SystemClock.elapsedRealtime()
        }

        // 通常のタッチイベント処理を行う.
        return super.onTouchEvent(event)
    }

    /**
     *  クリック無効期間を変更可能する.
     */
    fun setClickDisablePeriod(clickDisablePeriod: Long) {
        this.mClickDisablePeriod = clickDisablePeriod
    }
}