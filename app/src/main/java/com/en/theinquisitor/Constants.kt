package com.en.theinquisitor

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        //"What country does this flag belong to"

        val que1 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que2 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que3 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que4 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )
        val que5 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que6 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )
        val que7 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que8 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que9 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val que10 = Question(
            1,
            "Which country was part of Pakistan until 1971?",
            null,
            "Afghanistan", "Bangladesh","Nepal", "India",
            2
        )

        val qArr = arrayOf(que1,que2,que3,que4,que5,que6,que7,que8,que9,que10)

        for ( i in 0..9){
            questionsList.add( qArr[i] )
        }

        return questionsList
    }
}

