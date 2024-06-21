package com.example.whatsapp.VMs.API


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizAPI {
    @GET("user")
    suspend fun GetUserList(
    ):Response<UserInfoResponseList>


    @POST("user")
    suspend fun CreateUser(
        @Body userInfoResponseListItem: UserInfoResponseListItem
    ):Response<UserInfoResponseListItem>




}