package com.example.quizapp.VMs.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RoomModel")
data class RoomModel(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val text : String,
    val answer1  : String,
    val answer2  : String,
    val answer3  : String,
    val answer4  : String,
    var correctAnswer : String,

    )

