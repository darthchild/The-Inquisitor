package com.en.theinquisitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.en.theinquisitor.R

class QuestionActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var tvOption1: TextView? = null
    private var tvOption2: TextView? = null
    private var tvOption3: TextView? = null
    private var tvOption4: TextView? = null
    private var btnSubmit: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvOption1 = findViewById(R.id.tvOption1)
        tvOption2 = findViewById(R.id.tvOption2)
        tvOption3 = findViewById(R.id.tvOption3)
        tvOption4 = findViewById(R.id.tvOption4)
        btnSubmit = findViewById(R.id.btnSubmit)

        val quesList = Constants.getQuestions()

//        Log.i("q size = ","${quesList.size}")
//        for(i in quesList){
//            Log.e("Questions", i.question)
//        }

        var currPosition = 1
        val questionObj: Question = quesList[currPosition - 1]
        progressBar?.progress = currPosition
        tvProgress?.text = "$currPosition/ ${progressBar?.max}"

        // setting the text views
        tvQuestion?.text = questionObj.question
        tvOption1?.text = questionObj.option1
        tvOption2?.text = questionObj.option2
        tvOption3?.text = questionObj.option3
        tvOption4?.text = questionObj.option4



    }
}