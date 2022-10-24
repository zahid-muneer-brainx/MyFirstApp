package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel:ViewModel() {
    val dataModal = MutableLiveData<Logininfo>()
    lateinit var myresponse: Response<ServerResponse?>
    lateinit var mythrowable:Throwable
    fun login(email: String, pass: String):Boolean {
        var flag:Boolean=true
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: RetrofitApi = retrofit.create(RetrofitApi::class.java)
        dataModal.value=Logininfo(email,pass)
        val call: Call<ServerResponse?> = retrofitAPI.login(dataModal)

        call.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                myresponse: Response<ServerResponse?>)
            {
              flag=true
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                flag=false
            }
        })
        return flag
    }
}