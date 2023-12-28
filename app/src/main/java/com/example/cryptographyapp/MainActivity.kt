package com.example.cryptographyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.Base64

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Encrpt = findViewById<Button>(R.id.btnEncrp)
        val Decrpt = findViewById<Button>(R.id.btnDecrp)

        Encrpt.setOnClickListener {
            val intent = Intent(this, Encoder::class.java )
            startActivity(intent)
        }

        Decrpt.setOnClickListener {
             val intent = Intent(this,Decoder::class.java)
             startActivity(intent)
        }
    }
}