package com.example.myfirstapp

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {

    @Headers("Content-type: application/json; charset=UTF-8")
    @POST("/api/register")
    fun register(@Body logininfo: Logininfo?): Call<ServerResponse?>


    @Headers("Content-type: application/json; charset=UTF-8")
    @POST("/api/login")
    fun login(@Body info:MutableLiveData<Logininfo>): Call<ServerResponse?>
}