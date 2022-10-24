package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapp.databinding.ActivityLoginBinding
import com.example.myfirstapp.databinding.FragmentFragmentloginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Fragmentlogin : Fragment() {


    lateinit var binding: FragmentFragmentloginBinding
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var responseTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        var loginbtn: Button = binding.loginbtn
        lateinit var response:Response<ServerResponse?>
        var flag:Boolean=true
        var backbtn: ImageView = binding.imageView2
        backbtn.setOnClickListener {
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
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
                flag=model.login(email.text.toString(),pass.text.toString())
                response=model.myresponse
            }
            return@setOnClickListener
        }
       if(flag)
       {
           if(response.isSuccessful)
           {
               Toast.makeText(context, "Successfully loggedin",Toast.LENGTH_SHORT).show()

           }
           else {
               Toast.makeText(context, "${response.body().toString()}", Toast.LENGTH_SHORT).show()
               activity?.let{
                   val intent = Intent (it, MainActivity::class.java)
                   it.startActivity(intent)
               }
           }

       }
        else
       {
           var error:Throwable=model.mythrowable
           Toast.makeText(context,error.message.toString(),Toast.LENGTH_SHORT).show()
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmentlogin, container, false)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmentlogin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmentlogin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}