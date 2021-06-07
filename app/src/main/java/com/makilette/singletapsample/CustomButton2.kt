package com.makilette.singletapsample

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton

/**
 * 連続タップ制御: performClick
 *
 * 2019-07-01 21:02:40.679 25251-25251/com.makilette.singletapsample D/--ando--: Enable click.
 * 2019-07-01 21:02:40.683 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- onClick id: 2131165219
 * 2019-07-01 21:02:40.683 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- タップ制御: performClick
 * 2019-07-01 21:02:42.426 25251-25251/com.makilette.singletapsample D/MainActivity: --ando-- startActivity
 * 2019-07-01 21:02:42.471 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:02:42.471 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:02:42.525 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 * 2019-07-01 21:02:42.526 25251-25251/com.makilette.singletapsample D/--ando--: Disable click for a certain period.
 *
 * 検証端末:
 * Google Pixcel3
 * OS: 9
 */
class CustomButton2(context: Context, attrs: AttributeSet) : MaterialButton(context, attrs) {

    private var mLastClickTime: Long = 0

    /** クリック無効期間. */
    private var mClickDisablePeriod: Long = 2000

    /**
     * [android.view.View.performClick]
     * このビューのOnClickListenerを呼び出します.
     * performClickはあくまでOnClickListenerを呼ぶ/呼ばないの制御しかしない.
     *
     * @return true: OnClickイベント発火, false: OnClickイベント発火しない
     */
    override fun performClick(): Boolean {
        // 一定期間、クリックを無効化する.
        if (SystemClock.elapsedRealtime() - mLastClickTime < mClickDisablePeriod) {
            Log.d("--ando--", "Disable click for a certain period.")
            // OnClickListenerを呼び出さない.
            return false
        }
        mLastClickTime = SystemClock.elapsedRealtime()

        // 通常のクリックイベント処理を行う.OnClickListenerを呼び出す.
        Log.d("--ando--", "Enable click.")
        return super.performClick()
    }

    /**
     *  クリック無効期間を変更可能する.
     */
    fun setClickDisablePeriod(clickDisablePeriod: Long) {
        this.mClickDisablePeriod = clickDisablePeriod
    }
}