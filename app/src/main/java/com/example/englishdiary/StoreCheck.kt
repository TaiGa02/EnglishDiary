package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.text.SimpleDateFormat
import java.util.Date

class StoreCheck : AppCompatActivity() {
    private lateinit var realm: Realm

    @SuppressLint("MissingInflatedId", "WrongViewCast", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_check)

        val intentForDiary = intent
        val todayDiary = intentForDiary.getStringExtra("diary")
        val todayDiaryTx: TextView = findViewById(R.id.todayDiaryTx)
        todayDiaryTx.text = todayDiary

        val readCount = intentForDiary.getLongExtra("readCount", 0L)

        val titleTx: EditText = findViewById(R.id.editTitle)

        val backBtn: Button = findViewById(R.id.Backbtn)
        val storeBtn: Button = findViewById(R.id.Storebtn)
        realm = Realm.getDefaultInstance()

        backBtn.setOnClickListener {
            finish()
        }

        storeBtn.setOnClickListener StoreCheck@{
            var diary: String = ""
            var title: String = titleTx.text.toString() // ユーザーが入力したタイトルを使用
            var date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()) // フォーマットを変更
            var read: Long = 0
            if (!todayDiaryTx.text.isNullOrEmpty()) {
                diary = todayDiaryTx.text.toString()
                read = readCount
            }
            if(title.isEmpty()){
                // テキストが空の場合、アラートを表示
                Toast.makeText(this@StoreCheck, "タイトルを書いてください", Toast.LENGTH_SHORT).show()
                return@StoreCheck // ページ遷移を行わないため、処理を中断
            }
            else{
                realm.executeTransaction {
                    val currentId = realm.where<MyModel>().max("id")
                    val nextId = (currentId?.toLong() ?: 0L) + 1L

                    val myModel = realm.createObject<MyModel>(nextId)
                    myModel.diary = diary
                    myModel.title = title
                    myModel.date = date
                    myModel.read = read

                    // デバッグログを追加して保存されたデータが正しいか確認
                    Log.d("StoreCheck", "Diary saved - Title: $title, Diary: $diary")

                }
            Toast.makeText(applicationContext, "Your diary was saved.", Toast.LENGTH_SHORT).show()
            // 日付情報をIntentに追加
            val intent = Intent(this@StoreCheck, Calender::class.java)
            intent.putExtra("selectedDate", date) // dateはStoreCheck内で定義された日付情報
            startActivity(intent)
            finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
