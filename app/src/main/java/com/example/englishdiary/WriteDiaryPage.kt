package com.example.englishdiary

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import io.realm.Realm
import io.realm.kotlin.where

class WriteDiaryPage : AppCompatActivity() {
    private val apikey = BuildConfig.MY_API_KEY
    private lateinit var realm: Realm

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_diary_page)

        realm = Realm.getDefaultInstance()

        val dateVw : TextView = findViewById(R.id.dateVw)
        val btnDictionary :ImageButton = findViewById(R.id.btnDictinaly)
        val btnCounter:Button = findViewById(R.id.btnCounter)
        val counterVw:TextView = findViewById(R.id.counterVw)
        val btnStore:Button = findViewById(R.id.btnStore)
        val diaryTx: EditText = findViewById(R.id.diaryTx)
        dateVw.text = Utils.getDate(this)
        var count = 0
        counterVw.text = "音読数：$count"

        val existingDiary = realm.where<MyModel>().equalTo("date", Utils.getDateWithFormat("yyyy-MM-dd",this)).findFirst()

        if (existingDiary != null) {
            diaryTx.setText(existingDiary.diary)
            count = existingDiary.read.toInt()
            counterVw.text = "音読数：$count"
        }

        btnCounter.setOnClickListener {
            count++
            counterVw.text = "音読数：$count"
        }
        btnStore.setOnClickListener {
            val intent = Intent(this@WriteDiaryPage, StoreCheck::class.java)

            val diary: String = diaryTx.text.toString().trim() // trim() を使って空白を除去

            if (diary.isEmpty()) {
                // テキストが空の場合、アラートを表示
                Toast.makeText(this@WriteDiaryPage, "日記を書いてください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // ページ遷移を行わないため、処理を中断
            }

            val readCountText = counterVw.text.toString()
            val readCount = readCountText.substringAfter("音読数：").toLong()

            intent.putExtra("diary", diary)
            intent.putExtra("readCount", readCount) // 音読数を Intent に追加
            startActivity(intent)
        }

        btnDictionary.setOnClickListener{
            val dialogView = layoutInflater.inflate(R.layout.dictionary_dialog, null)
            val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Dictionary")

            val sourceEt : EditText = dialogView.findViewById(R.id.sourceEt)
            val resultTx : TextView = dialogView.findViewById(R.id.resultTx)
            val translateBtn : Button = dialogView.findViewById(R.id.translateBtn)

            translateBtn.setOnClickListener{
                val textToTranslate = sourceEt.text.toString()

                translateText(textToTranslate) { translatedText ->
                    resultTx.text = translatedText
                }
            }

            builder.setPositiveButton("Back"){ dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun translateText(textToTranslate: String, onTranslationComplete: (String) -> Unit) {
        val targetLang = "en"
        val translate = TranslateOptions.newBuilder().setApiKey(apikey).build().service
        Thread {
            val translation = translate.translate(textToTranslate, Translate.TranslateOption.targetLanguage(targetLang))
            runOnUiThread {
                onTranslationComplete(translation.translatedText)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}