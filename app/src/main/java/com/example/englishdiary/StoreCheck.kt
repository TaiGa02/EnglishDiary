package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.text.SimpleDateFormat
import java.util.Date
import androidx.appcompat.app.AlertDialog

class StoreCheck : AppCompatActivity() {
    private lateinit var realm: Realm

    @SuppressLint("MissingInflatedId", "WrongViewCast", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_check)

        // Intentから日記と音読数を取得し、表示するTextViewにセット
        val intentForDiary = intent
        val todayDiary = intentForDiary.getStringExtra("diary")
        val todayDiaryTx: TextView = findViewById(R.id.todayDiaryTx)
        todayDiaryTx.text = todayDiary

        val readCount = intentForDiary.getLongExtra("readCount", 0L)

        val titleTx: EditText = findViewById(R.id.editTitle)

        val backBtn: Button = findViewById(R.id.Backbtn)
        val storeBtn: Button = findViewById(R.id.Storebtn)
        realm = Realm.getDefaultInstance()

        // 既存の日記があればタイトルをセット
        val existingDiary = realm.where<MyModel>().equalTo("date", Utils.getDateWithFormat("yyyy-MM-dd",this)).findFirst()
        if (existingDiary != null) {
            titleTx.setText(existingDiary.title)
        }

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
                // タイトルが空の場合、アラートを表示して処理を中断
                Toast.makeText(this@StoreCheck, "タイトルを書いてください", Toast.LENGTH_SHORT).show()
                return@StoreCheck
            }

            if (existingDiary != null) {
                // 既存の日記がある場合、上書きするか確認するダイアログを表示
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("既にこの日付の日記が存在します。上書きしますか？")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        // ユーザーがはいを選択した場合、日記を上書きして画面を更新
                        realm.executeTransaction {
                            existingDiary.diary = diary
                            existingDiary.title = title
                            existingDiary.read = read
                        }
                        Toast.makeText(applicationContext, "Your diary was updated.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@StoreCheck, Calender::class.java)
                        intent.putExtra("selectedDate", date)
                        startActivity(intent)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.cancel()
                    }
                val alertDialog = dialogBuilder.create()
                alertDialog.show()
            }
            else{
                // 新規の日記を追加
                realm.executeTransaction {
                    val currentId = realm.where<MyModel>().max("id")
                    val nextId = (currentId?.toLong() ?: 0L) + 1L

                    val myModel = realm.createObject<MyModel>(nextId)
                    myModel.diary = diary
                    myModel.title = title
                    myModel.date = date
                    myModel.read = read

                }
                Toast.makeText(applicationContext, "Your diary was saved.", Toast.LENGTH_SHORT).show()
                // 日付情報をIntentに追加してカレンダー画面に遷移
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
