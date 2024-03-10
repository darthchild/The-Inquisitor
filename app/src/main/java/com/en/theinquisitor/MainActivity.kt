package com.en.theinquisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.en.theinquisitor.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

    // handles the menu options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle menu item clicks here if needed
        when (item.itemId) {
            R.id.optSettings -> {
                // Handle option 1 click
                return true
            }
            R.id.optAbout -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("About")
                    .setMessage("Developed by Ekagra Nigam\n Version: 1.0.0.1")
                    .setNeutralButton("Close") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
                return true
            }
            R.id.optPrivacy -> {
                // Handle option 3 click
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
