package com.en.theinquisitor

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.en.theinquisitor.databinding.ActivityQuestionBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

// TODO:
// <X> FIX: text cutting at the left & right edges
// <?> FIX: absolute position for options & submit button,
//      not to go up and down according to teh question text length
// <X> FIX: quiz going on even after 10 Qs, no "finish" btn

@SuppressLint("SetTextI18n")
class QuestionActivity : AppCompatActivity(), OnClickListener {

    // GLOBAL VARIABLES
    private var currPosition: Int = 1
    private var selectedOption: Int = -1 // arbitrary default value
    private var score: Int = 0
    private var wrongAnswers: Int = 0
    private var rightAnswers: Int = 0
    private val questionsList = arrayListOf<Question>()
    private lateinit var gameMode: String
    private lateinit var userName: String
    private lateinit var tvOptionsArray: Array<TextView>
    private lateinit var bd: ActivityQuestionBinding
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Firebase Initialization
        database = FirebaseDatabase.getInstance()

        // Sets up View Binding
        bd = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(bd.root)

        // gets the GAME MODE, from previous activity
        gameMode = intent.getStringExtra("game_mode")!!
        Log.d("gameModeValue","gameMode")

        // Gets & sets the username
        userName = intent.getStringExtra("user_name")!!
        bd.tvUserName.text = userName

        // array of all the option's TextView
        tvOptionsArray = arrayOf(bd.tvOption1, bd.tvOption2, bd.tvOption3, bd.tvOption4)

        // sets default SCORE
        bd.tvScore.text = "the Score: 0"

        setQuestion()

        // Makes the options TVs & submit btn clickable
        for (option in tvOptionsArray) {
            option.setOnClickListener(this)
        }
        bd.btnSubmit.setOnClickListener(this)

        /*
        (here "this" is QuestionActivity class we're exporting the
        onClickLis.. job to the class, which is already a OnClickLis..,
        it will execute the OnClick() which we've defined)
         */
    }


    /**
     * Sets a different style to the option that has been clicked
     */
    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int){
        // first, set all options to default color
        defaultOptionsView()
        // sets the selectedOption global var as the option that has been clicked
        selectedOption = selectedOptionNum
        tv.setTextColor(Color.BLACK)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_bg
        )
    }

    /**
     * Displays the question and options for the current position in the quiz.
     **/
    private fun setQuestion() {

        defaultOptionsView()
        // Reference to Firebase's Realtime Database
        val ref = database.reference.child("game_modes").child(gameMode).child("questions")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                questionsList.clear()  // Clear the list before populating

                for (questionSnapshot in snapshot.children) {
                    val question = questionSnapshot.getValue(Question::class.java)
                    question?.let {
                        questionsList.add(it)
                    }
                } // Now 'questionsList' list contains all the questions for the specified game mode

                // Gets question of the current position
                val currQuestion = questionsList[currPosition - 1]

                // Sets the Question & Options Text Views
                bd.tvQuestion.text = currQuestion.question
                val currOptionsArray = arrayOf(currQuestion.option1, currQuestion.option2, currQuestion.option3, currQuestion.option4)
                for(i in 0..3){
                    tvOptionsArray[i].text = currOptionsArray[i]
                }

                // Sets the submit button's text
                if(currPosition < questionsList.size){
                    bd.btnSubmit.text = "Submit"
                }

                // Sets the progress bar
                bd.progressBar.progress = currPosition
                bd.tvProgress.text = "$currPosition/ ${bd.progressBar.max}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                val activityContext = this@QuestionActivity
                Toast.makeText(
                    activityContext,
                    "Firebase Error: ${databaseError.message}",
                    Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * Sets the options' style to the default look
     */
    private fun defaultOptionsView(){

        val options = arrayListOf(bd.tvOption1,bd.tvOption2,bd.tvOption3,bd.tvOption4)
        for(opt in options){
            // for custom color - Color.parseColor("#7A8089")
            opt.setTextColor(Color.parseColor("#323232"))
            opt.typeface = Typeface.DEFAULT
            opt.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_bg
            )
        }
    }

    private fun setScore(isCorrect: Boolean){
        if(isCorrect){
            score += 10
        } else {
            score -= 5
        }
        bd.tvScore.text = "the Score: $score"
    }

    private fun onSubmit(){

        // NO OPTION SELECTED
        if(selectedOption == -1){
            currPosition++
            Log.d("AAAA","$currPosition")

            // if more Qs available, sets the Ques.
            if (currPosition <= questionsList.size) {
                setQuestion()
            } else {
                // Quiz finished
                // Toast.makeText(this, "Game Finished Homeboy! $rightAnswers/$wrongAnswers", Toast.LENGTH_SHORT).show()

                 //Sends data to next Activity
                val intent1 = Intent(this, ResultActivity::class.java)
                intent1.putExtra("score",score)
                intent1.putExtra("user_name",userName)
                intent1.putExtra("wrong_answers",wrongAnswers)
                intent1.putExtra("right_answers",rightAnswers)
                startActivity(intent1)
                //finish()
            }

        }
        // OPTION SELECTED
        else {
            val question = questionsList[currPosition -1]

            // when WRONG option selected
            if( question.correctAnswer != selectedOption){
                answerView(selectedOption, false)
                setScore(false)
                wrongAnswers++
            }
            // when RIGHT option selected
            else {
                setScore(true)
                rightAnswers++
            }

            // displays correct answer anyway
            answerView(question.correctAnswer, true)

            // sets the Submit btn text
            if (currPosition == questionsList.size) {
                bd.btnSubmit.text = "Finish"
            } else {
                bd.btnSubmit.text = "Next Question"
            }

            // reassigning to default value
            selectedOption = -1
        }
    }

    /**
     * Describes what to do when a View is clicked
     */
    override fun onClick(view: View?) {

        when(view?.id){ // runs when the "view" is clicked

            R.id.tvOption1 -> selectedOptionsView(bd.tvOption1, 0)
            R.id.tvOption2 -> selectedOptionsView(bd.tvOption2, 1)
            R.id.tvOption3 -> selectedOptionsView(bd.tvOption3, 2)
            R.id.tvOption4 -> selectedOptionsView(bd.tvOption4, 3)
            R.id.btn_submit -> onSubmit()
        }
    }

    private fun answerView(answer: Int, isCorrect: Boolean) {

        val drawableView: Int = if(isCorrect){
            R.drawable.correct_option_bg
        }else{
            R.drawable.wrong_option_bg
        }
        tvOptionsArray[answer].background = ContextCompat.getDrawable(this, drawableView)

    }

}