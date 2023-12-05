package com.en.theinquisitor

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.en.theinquisitor.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), OnClickListener {

    // GLOBAL VARIABLES
    private var currPosition: Int = 1
    private var selectedOption: Int = 0
    private lateinit var questionsList: ArrayList<Question>
    private lateinit var tvOptionsArray: Array<TextView>
    private lateinit var bd: ActivityQuestionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bd = ActivityQuestionBinding.inflate(layoutInflater)
        // array of all the option's TextView
        tvOptionsArray = arrayOf(bd.tvOption1, bd.tvOption2, bd.tvOption3, bd.tvOption4)
        setContentView(bd.root)

        questionsList = Constants.getQuestions()
        setQuestion()

        // Makes the options Text View clickable
        for (option in tvOptionsArray) {
            option.setOnClickListener(this)
        }
        /*
        (here "this" is QuestionActivity class we're exporting the
        onClickLis.. job to the class, which is already a OnClickLis..,
        it will execute the OnClick() which we've defined)
         */
    }


    /**
     * Displays the question and options for the current position in the quiz.
     **/
    private fun setQuestion() {
        defaultOptionsView()
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

        val optionContentArray = arrayOf(currQuestion.option1, currQuestion.option2, currQuestion.option3, currQuestion.option4)
        for(i in 0..3){
            tvOptionsArray[i].text = optionContentArray[i]
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
            opt.setTextColor(Color.LTGRAY)
            opt.typeface = Typeface.DEFAULT
            opt.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_bg
            )
        }
    }

    /**
     * Sets a different style to the option that has been clicked
     */
    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int){

        // first, set all options to default color
        defaultOptionsView()
        // sets the selectedOption global var as the option that has
        // been clicked
        selectedOption = selectedOptionNum
        tv.setTextColor(Color.BLACK)
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border
        )
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

            R.id.btnSubmit -> {
                if(selectedOption == 0){
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
                    // when wrong option is selected
                    if( question.correctAnswer != selectedOption){
                        answerView(selectedOption, false)
                    }
                    // displays correct answer regardless
                    answerView(question.correctAnswer, true)

                    if(currPosition == questionsList.size){
                        bd.btnSubmit.text = "Finish"
                    } else {
                        bd.btnSubmit.text = "Next Question"
                    }
                    // reassigning to default value
                    selectedOption = 0
                }

            }
        }
    }

    private fun answerView(answer: Int, isCorrect: Boolean){
        var drawableView = 0

        drawableView = if(isCorrect){
            R.drawable.correct_option_bg
        } else {
            R.drawable.wrong_option_bg
        }

        when(answer){
            1-> { bd.tvOption1.background = ContextCompat.getDrawable(this, drawableView) }
            2-> { bd.tvOption2.background = ContextCompat.getDrawable(this, drawableView) }
            3-> { bd.tvOption3.background = ContextCompat.getDrawable(this, drawableView) }
            4-> { bd.tvOption4.background = ContextCompat.getDrawable(this, drawableView) }

        }
    }
}