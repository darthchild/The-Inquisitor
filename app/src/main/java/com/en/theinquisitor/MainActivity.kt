package com.en.theinquisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.en.theinquisitor.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val etUserName: AppCompatEditText = findViewById(R.id.userName)

        btnStart.setOnClickListener{

            if(etUserName.text!!.isEmpty()){
                Toast.makeText(this,"Please enter your name!",Toast.LENGTH_LONG).show()
            } else {
                // defining where we want to go
                val intent = Intent(this, GameModeActivity::class.java)
                val userName: String = etUserName.text.toString()
                intent.putExtra("userName", userName)
                startActivity(intent)  // starts the new intent

                Log.d("SPQR","got the name : $userName")
            }
        }

    }
}
