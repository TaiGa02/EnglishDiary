package com.example.englishdiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StoreCheck : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_check)

        val intentForDiary = intent

        val diary = intentForDiary.getStringExtra("diary")

        val todayDiaryTx:TextView = findViewById(R.id.todayDiaryTx)

        todayDiaryTx.text = diary

        val backBtn :Button = findViewById(R.id.BackBtn)

        backBtn.setOnClickListener {
            finish()
        }

    }
}