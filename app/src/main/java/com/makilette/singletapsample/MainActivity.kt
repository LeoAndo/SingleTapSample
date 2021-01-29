package com.makilette.singletapsample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.makilette.singletapsample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.activity = this

        button1.setOnClickListener {
            Log.d("MainActivity", "--ando-- タップ制御: onTouchEvent")
            startActivity2()
        }
        button2.setOnClickListener {
            Log.d("MainActivity", "--ando-- タップ制御: performClick")
            startActivity2()
        }
        button5.setOnClickListener {
            Log.d("MainActivity", "--ando-- タップ制御なし")
            startActivity2()
        }
        button7.setOnSingleClickListener(View.OnClickListener {
            Log.d("MainActivity", "--ando-- タップ制御: カスタムクリックリスナー")
            startActivity2()
        })
        button8.setOnDebounceClickListener {
            Log.d("MainActivity", "--ando-- タップ制御: setOnDebounceClickListener")
        }
    }

    fun startActivity2() {
        // TODO ここにブレークポイント貼ってデバックで連打試験する.
        Log.d("MainActivity", "--ando-- startActivity")
        val intent = Intent(this, SubActivity::class.java)
        startActivity(intent)
    }
}
