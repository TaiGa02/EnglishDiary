package com.example.calculateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et1:EditText = findViewById(R.id.et1)
        val et2:EditText = findViewById(R.id.et2)
        val btnAdd:Button = findViewById(R.id.btnAdd)
        val btnMinus:Button = findViewById(R.id.btnMinus)
        val btnTimes:Button = findViewById(R.id.btnTimes)
        val btnDivide:Button = findViewById(R.id.btnDivide)
        val answer :TextView = findViewById(R.id.answer)
        val clear :Button = findViewById(R.id.clear)

        btnAdd.setOnClickListener {
            if(et1.text.toString() == "" || et2.text.toString() == ""){

                Toast.makeText(this,"数字を入力してください",Toast.LENGTH_SHORT).show()
            }
            else {
                val sum = et1.text.toString().toInt() + et2.text.toString().toInt()
                answer.text = sum.toString()
            }
        }

        btnMinus.setOnClickListener {
            if(et1.text.toString()=="" || et2.text.toString()==""){
                AlertDialog.Builder(this)
                    .setTitle("ERROR!")
                    .setMessage("数字を入力してください")
                    .setPositiveButton("OK",null)
                    .show()
            }
            else{
                val sum = et1.text.toString().toInt() - et2.text.toString().toInt()
                answer.text = sum.toString()
            }
        }

        btnTimes.setOnClickListener {
            if(et1.text.toString()=="" || et2.text.toString()==""){
                AlertDialog.Builder(this)
                    .setTitle("ERROR!")
                    .setMessage("数字を入力してください")
                    .setPositiveButton("OK",null)
                    .show()
            }
            else{
                val sum = et1.text.toString().toInt() * et2.text.toString().toInt()
                answer.text = sum.toString()
            }
        }

        btnDivide.setOnClickListener {
            if(et1.text.toString()=="" || et2.text.toString()==""){
                AlertDialog.Builder(this)
                    .setTitle("ERROR!")
                    .setMessage("数字を入力してください")
                    .setPositiveButton("OK",null)
                    .show()
            }
            else{
                val sum = et1.text.toString().toInt() / et2.text.toString().toInt()
                answer.text = sum.toString()
            }
        }

        clear.setOnClickListener {
            et1.text.clear()
            et2.text.clear()
            answer.text = "答え"
        }
    }
}