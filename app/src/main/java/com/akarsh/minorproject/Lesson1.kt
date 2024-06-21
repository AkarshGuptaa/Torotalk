package com.akarsh.minorproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akarsh.minorproject.databinding.ActivityLoginBinding
import android.content.Intent
import androidx.appcompat.widget.AppCompatButton

class Lesson1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)

        val button2 = findViewById<AppCompatButton>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, Lesson101::class.java)
            startActivity(intent)
        }
    }
}
