package com.example.post

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getdat(): Call<List<dat.People>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun adddat(@Body data: dat.People): Call<dat.People>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun updatedat(@Path("id")id:Int, @Body data: dat.People): Call<dat.People>

    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun deldat(@Path("id")id:Int): Call<dat.People>
}