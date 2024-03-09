package com.en.theinquisitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.en.theinquisitor.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var bd: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets up View Binding
        bd = ActivityResultBinding.inflate(layoutInflater)
        setContentView(bd.root)

        // gets data from previous Activity
        val intent = intent
        val userName = intent.getStringExtra("user_name")
        val score = intent.getIntExtra("score",0)
        val wrong = intent.getIntExtra("wrong_answers",0)
        val right = intent.getIntExtra("right_answers",0)
        Log.d("SPQR","score: $score, username: $userName, wrong: $wrong, right $right")


        // displays the values
        bd.tvUserName.text = userName
        bd.tvScore.text = "Score: $score"
        bd.tvRight.text = "Right Qs: $right"
        bd.tvWrong.text = "Wrong Qs: $wrong"

        bd.topAppBar.setNavigationOnClickListener {
            finish()
        }

    }
}