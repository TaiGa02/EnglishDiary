package com.example.onamaeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et:EditText = findViewById(R.id.et)
        val btnStart:Button = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {

            if (et.text.toString()==("")){
                //Toast.makeText(this,"Please Write Your Name",Toast.LENGTH_LONG).show()
                AlertDialog.Builder(this)
                    .setTitle("ERROR!")
                    .setMessage("Please Write Your Name")
                    .setPositiveButton("OK",null)
                    .show()
            }else{
                val intent = Intent(this,SecondActivity::class.java)


                intent.putExtra("MY_NAME",et.text.toString())

                startActivity(intent)
            }
        }
    }
}