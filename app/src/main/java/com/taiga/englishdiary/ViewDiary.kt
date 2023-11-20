package com.taiga.englishdiary

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.where
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ViewDiary : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var diaryDateTx: TextView
    private lateinit var diaryTitleTx: TextView
    private lateinit var viewDiaryTx: TextView
    private lateinit var readCountTx: TextView
    private lateinit var backToCalendarBtn: Button
    private lateinit var btnRead: Button
    private var readCount: Long = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_diary)

        // Realmのインスタンスを取得
        realm = Realm.getDefaultInstance()

        // Realmのインスタンスを取得
        diaryDateTx = findViewById(R.id.DiaryDatetx)
        diaryTitleTx = findViewById(R.id.DiaryTitletx)
        viewDiaryTx = findViewById(R.id.ViewDiarytx)
        readCountTx = findViewById(R.id.ReadCounttx)
        backToCalendarBtn = findViewById(R.id.BackToCalenderbtn)
        btnRead = findViewById(R.id.btnRead)

        // カレンダーからの選択された日付を取得
        var selectedDate = intent.getStringExtra("selectedDate")

        // 日付のフォーマットを定義
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateFormatterOutput = DateTimeFormatter.ofPattern("dd MMMM", Locale.ENGLISH)

        // 日付文字列を LocalDate に変換
        val localDate = LocalDate.parse(selectedDate, dateFormatter)

        // 新しい日付フォーマットに変換
        val formattedDate = localDate.format(dateFormatterOutput)


        // データベースから日記を取得
        val diaryData = realm.where<MyModel>().equalTo("date", selectedDate).findFirst()

        if (diaryData != null) {
            diaryDateTx.text = formattedDate
            diaryTitleTx.text = diaryData.title
            viewDiaryTx.text = diaryData.diary
            readCount = diaryData.read
            readCountTx.text = "音読数: $readCount"

        }

        // 戻るボタンのクリック処理
        backToCalendarBtn.setOnClickListener {
            updateReadCount()
            finish()
        }

        // 音読ボタンのクリック処理
        btnRead.setOnClickListener {
            readCount++
            readCountTx.text = "音読数: $readCount"
        }
    }

    // 音読数を更新する関数
    private fun updateReadCount() {
        // カレンダーからの選択された日付を取得
        val selectedDate = intent.getStringExtra("selectedDate")
        realm.executeTransaction {
            val diaryData = realm.where<MyModel>().equalTo("date", selectedDate).findFirst()
            diaryData?.read = readCount
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
