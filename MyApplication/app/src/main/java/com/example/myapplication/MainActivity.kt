package com.example.myapplication

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

        val tv:TextView = findViewById(R.id.textView)
        val btnPlus:Button = findViewById(R.id.btnPlus)
        val btnMinus:Button = findViewById(R.id.btnMinus)
        var num = 0

        btnPlus.setOnClickListener {


            num++

            if (num == 5){
                val intent = Intent(this,MainActivity3::class.java)
                startActivity(intent)
                finish()
            }
            else{
                tv.text= num.toString()
            }
        }

        btnMinus.setOnClickListener {
            num--
            if(num == -5){
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
                finish()
            }
            else{
                tv.text  = num.toString()
            }
        }

    }
}