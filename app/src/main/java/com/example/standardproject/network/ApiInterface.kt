package com.example.standardproject.network


import com.example.standardproject.utilities.UserLoginData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface WebServices {
    @POST("ENDPOINT")
    suspend fun login(@Body userData: UserLoginData): Response<Any>

    @Multipart
    @POST("ENDPOINT")
    suspend fun updateGroupInfo(
        @Part("key") name: RequestBody?,
        @Part("key") id: RequestBody?,
        @Part("key") is_archived: RequestBody?,
        @Part list: List<MultipartBody.Part>,
        @Part list2: List<MultipartBody.Part>,
        @Part file: MultipartBody.Part?
    ): Response<Any>




}