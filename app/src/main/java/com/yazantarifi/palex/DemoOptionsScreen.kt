package com.yazantarifi.palex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_options.*

class DemoOptionsScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        multiViewsButton?.setOnClickListener {
            startActivity(Intent(this@DemoOptionsScreen, MainActivity::class.java))
        }
    }

}