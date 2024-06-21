package com.akarsh.minorproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select)

        val lesson1 = findViewById<AppCompatButton>(R.id.lesson1)
        lesson1.setOnClickListener {
            val intent = Intent(this, Lesson1::class.java)
            startActivity(intent)
        }

        val lesson2 = findViewById<AppCompatButton>(R.id.lesson2)
        lesson2.setOnClickListener {
            val intent = Intent(this, Lesson2::class.java)
            startActivity(intent)
        }

        val lesson3 = findViewById<AppCompatButton>(R.id.lesson3)
        lesson3.setOnClickListener {
            val intent = Intent(this, Lesson3::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}