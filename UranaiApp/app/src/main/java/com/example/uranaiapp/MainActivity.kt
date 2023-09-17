package com.example.uranaiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tx:TextView = findViewById(R.id.textFortune)
        val btnFortune : Button = findViewById(R.id.btnFortune)

        btnFortune.setOnClickListener {
            //tx.text = "大吉"

            val msg = arrayOf("大吉","中吉","末吉","小吉","凶","大凶")
            //val num = Random().nextInt(6)
            val num = Random().nextInt(msg.size)
            tx.text = msg[num]
        }
    }
}