package com.example.touchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewを取得　idで
        val tx :TextView = findViewById(R.id.tv)
        val btn1 :Button = findViewById(R.id.btnDog)
        val btn2 :Button = findViewById(R.id.btnCat)
        val btn3 :Button = findViewById(R.id.btnClear)
        //クリック処理
        btn1.setOnClickListener {
            //ボタンを押した時の処理
            tx.text = "いぬ"
        }

        btn2.setOnClickListener {
            tx.text = "ねこ"
        }

        btn3.setOnClickListener {
            tx.text = "・・・"
        }
    }
}