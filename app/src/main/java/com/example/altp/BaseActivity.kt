package com.example.altp

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun Back() {
       super.onBackPressed()

    }
}