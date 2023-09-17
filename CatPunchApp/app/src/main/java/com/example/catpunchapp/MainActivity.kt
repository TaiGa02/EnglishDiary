package com.example.catpunchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val iv :ImageView = findViewById(R.id.iv)
        when(event?.action){
            MotionEvent.ACTION_DOWN -> iv.setImageResource(R.drawable.catpunch2)
            MotionEvent.ACTION_MOVE -> iv.setImageResource(R.drawable.catpunch3)
            MotionEvent.ACTION_UP -> iv.setImageResource(R.drawable.catpunch1)
        }
        return super.onTouchEvent(event)
    }
}