package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.myfirstapp.databinding.ActivityLoginBinding
import com.example.myfirstapp.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    lateinit var fnamebox:EditText
    lateinit var lnamebox:EditText
    lateinit var mailbox:EditText
    lateinit var passbox:EditText
    lateinit var pass2box:EditText
    lateinit var signup:Button
    lateinit var responseTV:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        var backbtn: ImageView =binding.img1
        backbtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
         fnamebox=binding.fname
         lnamebox=binding.lname
         mailbox=binding.email1
         passbox=binding.pass1
          pass2box=binding.repass
         signup=binding.signupbtn
        responseTV=binding.responseTV1
        signup.setOnClickListener {
            if (fnamebox.text.toString().isEmpty()) {
                fnamebox.requestFocus()
                fnamebox.setError("Enter First Name")
            }
            else if (lnamebox.text.toString().isEmpty()) {
                lnamebox.requestFocus()
                lnamebox.setError("Enter Last Name")
            }
            else if (mailbox.text.toString().trim().isEmpty()) {
                mailbox.requestFocus()
                mailbox.setError("Enter Email")
            }
            else if (!isEmailValid(mailbox.text.toString())) {
                mailbox.requestFocus()
                mailbox.setError("Enter Valid Email")
            }
            else if (passbox.text.toString().isEmpty()) {
                passbox.requestFocus()
                passbox.setError("Enter Password")
            }
           else if (passbox.text.toString().length<5) {
                passbox.requestFocus()
                passbox.setError("At least 8 characters")
            }
            else if (pass2box.text.toString().isEmpty()) {
                pass2box.requestFocus()
                pass2box.setError("ReEnter Password")
            }
            else if (pass2box.text.toString() != passbox.text.toString()) {
                pass2box.requestFocus()
                pass2box.setError("Password not Matched")
            }
            else
                register(fnamebox.text.toString(),lnamebox.text.toString(),
                    mailbox.text.toString(),passbox.text.toString())
            return@setOnClickListener
        }
    }
    private fun register(fname: String, lname: String, email: String, pass: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI: RetrofitApi = retrofit.create(RetrofitApi::class.java)
        val dataModal: Logininfo? = Logininfo( email, pass)
        val call: Call<ServerResponse?> = retrofitAPI.register(dataModal)
        call.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(
                call: Call<ServerResponse?>?,
                response: Response<ServerResponse?>
            )
            {
                if(response.isSuccessful)
                {
                    "Registered Successfully ${response.body().toString()}".toast(this@SignupActivity)
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                }
                else{
                    "Error: ${response.body().toString()}".toast(this@SignupActivity)
                }
            }
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.visibility=View.VISIBLE
                responseTV.setText("Error found is :   ${t.message.toString()}")
            }
        })
}
}

