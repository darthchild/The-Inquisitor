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
            val intent1 = Intent(this, QuestionActivity::class.java)
            val mode: String = "history"
            intent1.putExtra("gameMode", mode)
            startActivity(intent1)  // starts the new intent
            finish()
        }

        btnTech.setOnClickListener{
            val intent2 = Intent(this, QuestionActivity::class.java)
            val mode: String = "tech"
            intent2.putExtra("gameMode", mode)
            startActivity(intent2)
            finish()
        }

        btnRiddle.setOnClickListener{
            val intent3 = Intent(this, QuestionActivity::class.java)
            val mode: String = "riddle"
            intent3.putExtra("gameMode", mode)
            startActivity(intent3)
            finish()
        }

        btnMytho.setOnClickListener{
            val intent4 = Intent(this, QuestionActivity::class.java)
            val mode: String = "mytho"
            intent4.putExtra("gameMode", mode)
            startActivity(intent4)
            finish()
        }

    }
}