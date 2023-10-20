package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class StoreCheck : AppCompatActivity() {
    private lateinit var realm :Realm
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_check)

        val intentForDiary = intent

        val todayDiary = intentForDiary.getStringExtra("diary")

        val todayDiaryTx:TextView = findViewById(R.id.todayDiaryTx)

        todayDiaryTx.text = todayDiary

        val backBtn :Button = findViewById(R.id.Backbtn)
        val storeBtn :Button = findViewById(R.id.Storebtn)
        realm = Realm.getDefaultInstance()

        backBtn.setOnClickListener {
            finish()
        }

        storeBtn.setOnClickListener{
            var diary :String = ""
            var title :String = ""
            var date :String = ""
            var read :Long =0
            if(!todayDiaryTx.text.isNullOrEmpty()){
                diary = todayDiaryTx.toString()
            }
            realm.executeTransaction{
                val currentId = realm.where<MyModel>().max("id")//現時点のidを取得
                val nextId = (currentId?.toLong()?:0L) + 1L//idの最高値に1を追加

                val myModel = realm.createObject<MyModel>(nextId)
                myModel.diary = diary
                myModel.title = title
                myModel.date = date
                myModel.read = read
            }
            Toast.makeText(applicationContext,"Your diary was saved.",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}