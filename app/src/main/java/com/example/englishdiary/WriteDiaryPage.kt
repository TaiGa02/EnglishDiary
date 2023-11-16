package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView


class WriteDiaryPage : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary_page)

        val dateVw : TextView = findViewById(R.id.dateVw)
        val btnDictionary :ImageButton = findViewById(R.id.btnDictinaly)
        val btnCounter:Button = findViewById(R.id.btnCounter)
        val counterVw:TextView = findViewById(R.id.counterVw)
        val btnStore:Button = findViewById(R.id.btnStore)
        dateVw.text = Utils.getDate(this)
        var count = 0
        counterVw.text = "音読数：$count"

        btnCounter.setOnClickListener {
            count++
            counterVw.text = "音読数：$count"
        }
        btnStore.setOnClickListener {
            val intent = Intent(this@WriteDiaryPage,StoreCheck::class.java)

            val diaryTx: EditText = findViewById(R.id.diaryTx)
            val diary: String = diaryTx.text.toString()
            val readCountText = counterVw.text.toString()
            val readCount = readCountText.substringAfter("音読数：").toLong()


            intent.putExtra("diary",diary)
            intent.putExtra("readCount", readCount) // 音読数を Intent に追加
            startActivity(intent)
        }
    }
}