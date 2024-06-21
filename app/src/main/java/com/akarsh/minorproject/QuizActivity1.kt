package com.akarsh.minorproject

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akarsh.minorproject.databinding.ActivityQuiz1Binding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityQuiz1Binding
    private lateinit var list: ArrayList<QuestionModel1>
    private var count: Int = 0
    private var score = 0

    private lateinit var mediaPlayerCorrect: MediaPlayer
    private lateinit var mediaPlayerIncorrect: MediaPlayer
    private lateinit var mediaPlayerCelebration: MediaPlayer
    private lateinit var mediaPlayerSad: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuiz1Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        list = ArrayList()

        mediaPlayerCorrect = MediaPlayer.create(this, R.raw.correct_sound)
        mediaPlayerIncorrect = MediaPlayer.create(this, R.raw.incorrect_sound)
        mediaPlayerCelebration = MediaPlayer.create(this, R.raw.celebration_sound)
        mediaPlayerSad = MediaPlayer.create(this, R.raw.sad_sound)

        Firebase.firestore.collection("quiz")
            .get().addOnSuccessListener { documents ->
                list.clear()
                for (document in documents) {
                    val questionModel1 = document.toObject(QuestionModel1::class.java)
                    list.add(questionModel1)
                }
                updateQuestionUI()
            }

        binding.option1.setOnClickListener {
            nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateQuestionUI() {
        binding.question.text = list[count].question
        binding.option1.text = list[count].option1
        binding.option2.text = list[count].option2
        binding.option3.text = list[count].option3
        binding.option4.text = list[count].option4
    }

    private fun nextData(selectedOption: String) {
        val correctAnswer = list[count].ans
        var isCorrect = false

        if (selectedOption == correctAnswer) {
            score++
            isCorrect = true
        }

        if (isCorrect) {
            mediaPlayerCorrect.start()
            binding.iconCorrect.visibility = View.VISIBLE
            binding.iconIncorrect.visibility = View.INVISIBLE
        } else {
            mediaPlayerIncorrect.start()
            binding.iconCorrect.visibility = View.INVISIBLE
            binding.iconIncorrect.visibility = View.VISIBLE
        }

        Handler(Looper.getMainLooper()).postDelayed({
            count++
            if (count >= list.size) {
                if (score >= 4) {
                    mediaPlayerCelebration.start()
                    Handler(Looper.getMainLooper()).postDelayed({
                        mediaPlayerCelebration.stop()
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra("SCORE", score)
                        startActivity(intent)
                        finish()
                    }, 7000)
                } else {
                    mediaPlayerSad.start()
                    Handler(Looper.getMainLooper()).postDelayed({
                        mediaPlayerSad.stop()
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra("SCORE", score)
                        startActivity(intent)
                        finish()
                    }, 9000)
                }
            } else {
                // Reset icons
                binding.iconCorrect.visibility = View.INVISIBLE
                binding.iconIncorrect.visibility = View.INVISIBLE
                updateQuestionUI()
            }
        }, 1000) // Delay in milliseconds
    }

    override fun onDestroy() {
        // Release MediaPlayer resources
        mediaPlayerCorrect.release()
        mediaPlayerIncorrect.release()
        mediaPlayerCelebration.release()
        mediaPlayerSad.release()
        super.onDestroy()
    }
}
