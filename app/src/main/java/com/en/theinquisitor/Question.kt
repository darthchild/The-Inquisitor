package com.en.theinquisitor

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

data class Question(
    val correctAnswer: Int,
    val id: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val question: String,
)

// val image: String?,
