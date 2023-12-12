package com.en.theinquisitor

import android.annotation.SuppressLint
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

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class YourDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "questions.db", null, 1) {
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        // Handle database upgrades here if needed
//        // For simplicity, we'll drop and recreate the questions table in this example
//        db.execSQL("DROP TABLE IF EXISTS questions")
//        onCreate(db)
//    }
//
//    override fun onCreate(db: SQLiteDatabase) {
//        // No need to create or insert anything here since you've done it with DB Browser
//    }
//
//    @SuppressLint("Range")
//    fun getQuestions(): List<Question> {
//        val questions = mutableListOf<Question>()
//        val db = readableDatabase
//
//        // Query the questions table
//        val cursor: Cursor = db.rawQuery("SELECT * FROM questions", null)
//
//        val columns = cursor.columnNames
//        Log.d("ColumnNames", columns.joinToString())
//
//
//        // Iterate through the cursor to retrieve questions
//        while (cursor.moveToNext()) {
//            val id = cursor.getInt(cursor.getColumnIndex("id"))
//            val questionText = cursor.getString(cursor.getColumnIndex("question"))
//            val image = cursor.getString(cursor.getColumnIndex("image"))
//            val correctAnswer = cursor.getInt(cursor.getColumnIndex("theAnswer"))
//            val option1 = cursor.getString(cursor.getColumnIndex("option1"))
//            val option2 = cursor.getString(cursor.getColumnIndex("option2"))
//            val option3 = cursor.getString(cursor.getColumnIndex("option3"))
//            val option4 = cursor.getString(cursor.getColumnIndex("option4"))
//
//            // Create a Question object and add it to the list
//            val question = Question(id, questionText, image, option1, option2, option3, option4, correctAnswer)
//            questions.add(question)
//        }
//
//        // Close the cursor and the database
//        cursor.close()
//        db.close()
//
//        return questions
//    }
//}



@SuppressLint("SetTextI18n")
class QuestionActivity : AppCompatActivity(), OnClickListener {

    // GLOBAL VARIABLES
    private var currPosition: Int = 1
    private var selectedOption: Int = 100 // arbitrary default value
    private var score: Int = 0
    private lateinit var questionsList: ArrayList<Question>
    private lateinit var tvOptionsArray: Array<TextView>
    private lateinit var bd: ActivityQuestionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setting up View Binding
        bd = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(bd.root)

        // array of all the option's TextView
        tvOptionsArray = arrayOf(bd.tvOption1, bd.tvOption2, bd.tvOption3, bd.tvOption4)

        // sets default SCORE
        bd.tvScore.text = "the Score: 0"

        // gets the GAME MODE
        val receivedValue = intent.getIntExtra("gameMode", -1)
        Log.d("gameModeValue","$receivedValue")

        questionsList = Constants.getQuestions()
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

//        // Assuming you have TextViews with IDs questionTextView, option1TextView, option2TextView, option3TextView, option4TextView
//        val questionTextView: TextView = findViewById(R.id.questionTextView)
//        val option1TextView: TextView = findViewById(R.id.option1TextView)
//        val option2TextView: TextView = findViewById(R.id.option2TextView)
//        val option3TextView: TextView = findViewById(R.id.option3TextView)
//        val option4TextView: TextView = findViewById(R.id.option4TextView)
//
//        // Get questions from the database
//        val dbHelper = YourDatabaseHelper(context)
//        val questions = dbHelper.getQuestions()
//
//        // Display the first question and options (modify as needed)
//        if (questions.isNotEmpty()) {
//            val firstQuestion = questions[0]
//
//            questionTextView.text = firstQuestion.questionText
//            option1TextView.text = "1. ${firstQuestion.option1}"
//            option2TextView.text = "2. ${firstQuestion.option2}"
//            option3TextView.text = "3. ${firstQuestion.option3}"
//            option4TextView.text = "4. ${firstQuestion.option4}"
//        }

        // Gets the current position in the quiz & Sets the progress bar
        val currQuestion: Question = questionsList[currPosition - 1]

        /**
         * currQuestion is a element of questionsList, which is an array of elements
         * of type:Question, "Question" is a data class, which has properties
         * like question,option1,option2..,correctAnswer
         **/
        bd.progressBar.progress = currPosition
        bd.tvProgress.text = "$currPosition/ ${bd.progressBar.max}"

        // Sets the Question & Options Text Views
        bd.tvQuestion.text = currQuestion.question

        val currOptionsArray = arrayOf(currQuestion.option1, currQuestion.option2, currQuestion.option3, currQuestion.option4)
        for(i in 0..3){
            tvOptionsArray[i].text = currOptionsArray[i]
        }

        // Sets the submit button's text
        if(currPosition == questionsList.size){
            bd.btnSubmit.text = "Finish"
        } else {
            bd.btnSubmit.text = "Submit"
        }
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
        if(selectedOption == 100){
            currPosition++
            when{
                // if more Qs available, sets the Q
                currPosition <= questionsList.size ->{
                    setQuestion()
                }
                else -> {
                    Toast.makeText(this, "Game Finished Homeboy!", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            val question = questionsList[currPosition -1]

            // when WRONG option is selected
            if( question.correctAnswer != selectedOption){
                answerView(selectedOption, false)
                setScore(false)
            }
            // when RIGHT option is selected
            else {
                setScore(true)
            }

            // displays correct answer anyway
            answerView(question.correctAnswer, true)

            if(currPosition == questionsList.size){
                bd.btnSubmit.text = "Finish"
            } else {
                bd.btnSubmit.text = "Next Question"
            }
            // reassigning to default value
            selectedOption = 100
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