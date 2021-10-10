package com.example.post

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getdat(): Call<List<dat.People>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body data: dat.People): Call<dat.People>
}