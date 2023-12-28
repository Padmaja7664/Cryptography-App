package com.example.cryptographyapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class Encoder : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encoder)

        val inputStr =findViewById<EditText>(R.id.str)
        val encrptedStr = findViewById<TextView>(R.id.tvEn)

        val encrptbtn = findViewById<Button>(R.id.btnenr)
        val cpybtn = findViewById<Button>(R.id.btnCpy)

        // create a clipboard manager variable to copy text
        val cpb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


        encrptbtn.setOnClickListener {
              val temp = inputStr.text.toString()
              val rv = Encode.encode(temp)
              encrptedStr.text= rv
        }

        cpybtn.setOnClickListener {
              val data =encrptedStr.text.toString().trim()

            if (!data.isEmpty()) {

                // copy the text in the clip board
                val temp = ClipData.newPlainText("text", data)
                cpb.setPrimaryClip(temp)

                // display message that the text has been copied
                Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
            }

        }


    }
}