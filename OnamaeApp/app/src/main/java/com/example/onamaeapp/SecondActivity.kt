package com.example.onamaeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvName :TextView = findViewById(R.id.textView2)
        val btnBack :Button = findViewById(R.id.btnBack)

        val myName = intent.getStringExtra("MY_NAME")
        tvName.text = "Mr. " + myName

        btnBack.setOnClickListener {
            finish()
        }
    }
}