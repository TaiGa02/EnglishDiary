package com.taiga.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 「日記を書く」ボタン
        val btnWrite: Button = findViewById(R.id.btnWrite)

        // 「カレンダーを見る」ボタン
        val btnCalendar: Button = findViewById(R.id.btnCalendar)

        // 日付を表示するTextView
        val dateTx: TextView = findViewById(R.id.textView2)
        dateTx.text = Utils.getDate(this)

        btnWrite.setOnClickListener {
            // 日記を書く画面へ遷移
            val intent = Intent(this@MainActivity, WriteDiaryPage::class.java)
            startActivity(intent)
        }

        btnCalendar.setOnClickListener {
            // カレンダー画面へ遷移
            val intent = Intent(this@MainActivity, Calender::class.java)
            startActivity(intent)
        }
    }
}
