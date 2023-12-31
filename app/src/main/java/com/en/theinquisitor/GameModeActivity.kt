package com.en.theinquisitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.en.theinquisitor.databinding.ActivityGameModeBinding

class GameModeActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userName: String
    private lateinit var bd: ActivityGameModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bd = ActivityGameModeBinding.inflate(layoutInflater)
        setContentView(bd.root)

        // Sets the username
        userName = intent.getStringExtra("userName")!!
        bd.tvUserName.text = userName

        // Sets the onClickListener to all buttons
        val gameModeArray = arrayOf(bd.historyMode,bd.mythoMode,bd.techMode,bd.riddleMode)
        for(i in gameModeArray){
            i.setOnClickListener(this)
        }
    }

    // method for sending data to the next Activity
    private fun sendForward(mode: String){

        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("gameMode",mode)
        intent.putExtra("userName",userName)
        startActivity(intent)  // starts the new intent
        finish()
    }

    override fun onClick(view: View?){

        when(view?.id){
            R.id.historyMode -> sendForward("history")
            R.id.techMode -> sendForward("tech")
            R.id.riddleMode -> sendForward("riddle")
            R.id.mythoMode -> sendForward("mytho")
        }
    }

}