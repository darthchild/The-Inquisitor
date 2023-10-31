package com.ekagra.theinquisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.theinquisitor.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val userName: Button = findViewById(R.id.userName)

        btnStart.setOnClickListener{
            if(userName.text.isEmpty()){
                Toast.makeText(this,"Please enter your name!",Toast.LENGTH_LONG).show()
            } else {
                // defining where we want to go
                val intent = Intent(this, gameModeActivity::class.java)
                startActivity(intent)  // starts the new intent
                finish() // closes current activity (user wont be able to go back)
            }
        }

    }
}
