package com.en.theinquisitor

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // gets data from previous Activity
        val intent = intent

        val score = intent.getIntExtra("score",0)
        val userName = intent.getStringExtra("user_name")
        val wrong = intent.getIntExtra("wrong_answers",0)
        val right = intent.getIntExtra("right_answers",0)
        Log.d("SPQR","score: $score, username: $userName, wrong: $wrong, right $right")



    }
}