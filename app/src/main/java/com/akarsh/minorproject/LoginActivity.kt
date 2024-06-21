package com.akarsh.minorproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akarsh.minorproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.selects.select


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.button.setOnClickListener{
            Firebase.auth.createUserWithEmailAndPassword(binding.email.editText?.text.toString(),
                binding.password.editText?.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, SelectActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "User created", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this, "User not created", Toast.LENGTH_LONG).show()
                    }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}