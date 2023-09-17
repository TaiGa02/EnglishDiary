package com.example.englishdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView


class WriteDiaryPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary_page)

        val dateVw : TextView = findViewById(R.id.dateVw)
        val btnDictionary :ImageButton = findViewById(R.id.btnDictinaly)
        val diaryTx:EditText = findViewById(R.id.diaryTx)
        val btnCounter:Button = findViewById(R.id.btnCounter)
        val counterVw:TextView = findViewById(R.id.counterVw)
        val btnStore:Button = findViewById(R.id.btnStore)
        dateVw.text = Utils.getDate()
        var count = 0
        counterVw.text = "音読数："+count.toString()

        btnCounter.setOnClickListener {
            count++
            counterVw.text = "音読数："+count.toString()
        }
    }
}