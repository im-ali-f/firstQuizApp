package com.example.quizapp.VMs.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertQuestion(roomModel:RoomModel)


    @Query("SELECT * From RoomModel")
    suspend fun GetAllQuestions():List<RoomModel>


/*
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun UpdatePDF(roomModel:RoomModel)

     */


}
