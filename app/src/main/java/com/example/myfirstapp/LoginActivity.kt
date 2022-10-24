package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.myfirstapp.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var responseTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        email = binding.email
        pass = binding.pass
        responseTV = findViewById(R.id.responseTV)
        var backbtn: ImageView = findViewById(R.id.imageView2)
        backbtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        var loginbtn: Button = binding.loginbtn
        loginbtn.setOnClickListener {
            if (email.text.toString().trim().isEmpty()) {
               // email.requestFocus()
                email.setError("Enter Email")
            } else if (!isEmailValid(email.text.toString())) {
                //email.requestFocus()
                email.setError("Enter Valid Email")
            } else if (pass.text.toString().isEmpty()) {
               // pass.requestFocus()
                pass.setError("Enter Password")
            } else {
                login(email.text.toString(),pass.text.toString())
            }
            return@setOnClickListener
        }
    }

    private fun login(email: String, pass: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: RetrofitApi = retrofit.create(RetrofitApi::class.java)
        val dataModal: Logininfo? = Logininfo(email, pass)
        val call: Call<ServerResponse?> = retrofitAPI.login(dataModal)

        call.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>,
                response: Response<ServerResponse?>
            ) {
                if (response.isSuccessful) {
                    "Successfully Login ${response.body().toString()}".toast(this@LoginActivity)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    "Unsuccessful Login ${response.body().toString()}".toast(this@LoginActivity)
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<ServerResponse?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.visibility = View.VISIBLE
                responseTV.setText("Error found is :   ${t.message.toString()} ")
            }
        })
    }
}