package com.taiga.englishdiary

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
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

        // 画面上の要素を取得
        val dateVw : TextView = findViewById(R.id.dateVw)
        val btnDictionary :ImageButton = findViewById(R.id.btnDictinaly)
        val btnCounter:Button = findViewById(R.id.btnCounter)
        val counterVw:TextView = findViewById(R.id.counterVw)
        val btnStore:Button = findViewById(R.id.btnStore)
        val diaryTx: EditText = findViewById(R.id.diaryTx)

        // 日付を取得して表示
        dateVw.text = Utils.getDate(this)
        var count = 0
        counterVw.text = "音読数：$count"

        // 既存の日記があれば表示する
        val existingDiary = realm.where<MyModel>().equalTo("date", Utils.getDateWithFormat("yyyy-MM-dd",this)).findFirst()
        if (existingDiary != null) {
            diaryTx.setText(existingDiary.diary)
            count = existingDiary.read.toInt()
            counterVw.text = "音読数：$count"
        }

        // 音読数を増やすボタン
        btnCounter.setOnClickListener {
            count++
            counterVw.text = "音読数：$count"
        }

        // 日記を保存するボタン
        btnStore.setOnClickListener {
            val intent = Intent(this@WriteDiaryPage, StoreCheck::class.java)

            val diary: String = diaryTx.text.toString().trim() // 空白を除去

            if (diary.isEmpty()) {
                // 日記が空の場合、アラートを表示して処理を中断
                Toast.makeText(this@WriteDiaryPage, "日記を書いてください", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val readCountText = counterVw.text.toString()
            val readCount = readCountText.substringAfter("音読数：").toLong()

            intent.putExtra("diary", diary)
            intent.putExtra("readCount", readCount) // 音読数を Intent に追加
            startActivity(intent)
        }

        // 翻訳ボタンを押した時の処理
        btnDictionary.setOnClickListener{
            val dialogView = layoutInflater.inflate(R.layout.dictionary_dialog, null)
            val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Dictionary")

            val sourceEt : EditText = dialogView.findViewById(R.id.sourceEt)
            val resultTx : TextView = dialogView.findViewById(R.id.resultTx)
            val translateBtn : Button = dialogView.findViewById(R.id.translateBtn)

            translateBtn.setOnClickListener{
                val textToTranslate = sourceEt.text.toString()

                // テキストを翻訳して表示
                translateText(textToTranslate) { translatedText ->
                    val decodedText = Html.fromHtml(translatedText, Html.FROM_HTML_MODE_LEGACY).toString()
                    resultTx.text = decodedText
                }
            }

            // 戻るボタン
            builder.setPositiveButton("Back"){ dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    // テキスト翻訳の関数
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
