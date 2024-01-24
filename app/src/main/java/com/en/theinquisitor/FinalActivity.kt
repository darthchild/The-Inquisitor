package com.en.theinquisitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        // gets data from previous Activity
        val score = intent.getStringExtra("score")!!
        val userName = intent.getStringExtra("user_name")!!
        val wrong = intent.getStringExtra("wrong_answers")!!
        val right = intent.getStringExtra("right_answers")!!



    }
}