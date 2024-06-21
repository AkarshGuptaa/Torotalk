package com.akarsh.minorproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akarsh.minorproject.databinding.ActivityLoginBinding
import android.content.Intent
import androidx.appcompat.widget.AppCompatButton

class Lesson2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson2)

        val button3 = findViewById<AppCompatButton>(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, QuizActivity2::class.java)
            startActivity(intent)
        }
    }
}