package com.en.theinquisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.en.theinquisitor.R

class GameModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode)

        val btnHistory: Button = findViewById(R.id.historyMode)
        val btnTech: Button = findViewById(R.id.techMode)
        val btnRiddle: Button = findViewById(R.id.riddleMode)
        val btnMytho: Button = findViewById(R.id.mythoMode)

        btnHistory.setOnClickListener{
            val intent0 = Intent(this, QuestionActivity::class.java)
            startActivity(intent0)  // starts the new intent
            finish()
        }

        btnTech.setOnClickListener{
            val intent1 = Intent(this, QuestionActivity::class.java)
            startActivity(intent1)  // starts the new intent
            finish()
        }

        btnRiddle.setOnClickListener{
            val intent2 = Intent(this, QuestionActivity::class.java)
            startActivity(intent2)  // starts the new intent
            finish()
        }

        btnMytho.setOnClickListener{
            val intent3 = Intent(this, QuestionActivity::class.java)
            startActivity(intent3)  // starts the new intent
            finish()
        }

    }
}