package com.example.drawktapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myView: MyView = findViewById(R.id.myView)
        val btnClear:Button = findViewById(R.id.btnClear)

        btnClear.setOnClickListener {
            myView.ClearCanvas()
        }


    }
}