package com.en.theinquisitor

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // correctAnswer indexing starts from 0

        val que1 = Question(
            1,
            1,
            "Afghanistan", "Bangladesh","Nepal", "India",
            "Which country was part of Pakistan until 1971?"
        )

        val que2 = Question(
            1,
             2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que3 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que4 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )
        val que5 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )
        val que6 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que7 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que8 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que9 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",
        )

        val que10 = Question(
            1,
            2,
            "Opt1", "Opt2","Opt3", "Opt4",
            "This is the next question",

        )

        val qArr = arrayOf(que1,que2,que3,que4,que5,que6,que7,que8,que9,que10)

        for ( i in 0..9){
            questionsList.add( qArr[i] )
        }

        return questionsList
    }
}

