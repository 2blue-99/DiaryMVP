package com.example.diarymvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class loading : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        startLoading()

    }
    private fun startLoading(){
        val handler = Handler()
        handler.postDelayed({finish()}, 2000)
    }
}