package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnWrite: Button = findViewById(R.id.btnWrite)
        val btnArchive: Button = findViewById(R.id.btnArchive)
        val btnHelp: Button = findViewById(R.id.btnHelp)
        val dateTx: TextView = findViewById(R.id.textView2)
        dateTx.text = Utils.getDate(this)

        btnWrite.setOnClickListener {

            //日記を書きページへの遷移
            val intent = Intent(this@MainActivity,WriteDiaryPage::class.java)
            startActivity(intent)

        }

        btnArchive.setOnClickListener {

            //カレンダーページに飛ぶ遷移
            val intent = Intent(this@MainActivity,Calender::class.java)
            startActivity(intent)
        }
    }
}