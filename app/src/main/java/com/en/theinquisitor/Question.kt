package com.en.theinquisitor

data class Question(
    val id: Int,
    val question: String,
    val image: String?,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int,
)
