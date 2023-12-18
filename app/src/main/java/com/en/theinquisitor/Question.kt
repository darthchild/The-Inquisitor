package com.en.theinquisitor

data class Question(
    val correctAnswer: Int,
    val id: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val question: String
) {
    // No-argument constructor for Firebase deserialization
    constructor() : this(0,0,"","","","","")
}

// val image: String?,
