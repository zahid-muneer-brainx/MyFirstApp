package com.example.myfirstapp

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel:ViewModel() {
    val dataModal:MutableLiveData<Logininfo> = MutableLiveData<Logininfo>()
    lateinit var mythrowable:Throwable
    val serverresponse:MutableLiveData<ServerResponse?> = MutableLiveData()
    fun login(email: String, pass: String){
    viewModelScope.launch() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI: RetrofitApi = retrofit.create(RetrofitApi::class.java)
        dataModal.value = Logininfo(email, pass)
        val call: Call<ServerResponse?> = retrofitAPI.login(dataModal)

        call.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                if (response.isSuccessful) {
                    serverresponse.postValue(null)

                } else {
                    serverresponse.postValue(response.body())
                    for (i in 1..10)
                        println(response.body().toString())
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                t.also {
                    mythrowable = it
                }

                //     Toast.makeText(AndroidViewModel,t.message.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }

    }
}