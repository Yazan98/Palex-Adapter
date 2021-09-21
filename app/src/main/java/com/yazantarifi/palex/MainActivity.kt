package com.yazantarifi.palex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yazantarifi.palex.multiViews.MultiViewFragmentExample

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MultiViewFragmentExample())
            .commit()
    }
}