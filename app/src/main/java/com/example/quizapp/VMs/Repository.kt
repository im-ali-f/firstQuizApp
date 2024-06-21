package com.example.whatsapp.VMs.API



import com.example.quizapp.VMs.Room.RoomModel
import com.example.quizapp.VMs.Room.db
import retrofit2.Response

class Repository(val db: db) {
    suspend fun GetUsersList(): Response<UserInfoResponseList> {

        return RetrofitInstance.api.GetUserList()
    }


    suspend fun CreateUser(body: UserInfoResponseListItem): Response<UserInfoResponseListItem> {

        return RetrofitInstance.api.CreateUser(body)
    }


    //room

    suspend fun InsertQuestion(RoomModel: RoomModel){
        db.QUIZDAO().InsertQuestion(RoomModel)
    }


    suspend fun GetAllQuestions()= db.QUIZDAO().GetAllQuestions()

/*
    suspend fun UpdatePDF(RoomModel:RoomModel){
        db.PDFDAO().UpdatePDF(RoomModel)
    }

     */
}