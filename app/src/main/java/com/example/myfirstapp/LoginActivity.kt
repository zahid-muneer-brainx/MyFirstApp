package com.example.myfirstapp

import android.app.FragmentTransaction
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.myfirstapp.databinding.ActivityLoginBinding
import com.example.myfirstapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
 lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val fragmentmanager=supportFragmentManager
            val transaction=fragmentmanager.beginTransaction()
            transaction.replace(R.id.frag1,Fragmentlogin())
            transaction.commit()


    }
}