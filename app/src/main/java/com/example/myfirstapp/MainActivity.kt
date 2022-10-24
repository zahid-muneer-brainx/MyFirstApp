package com.example.myfirstapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myfirstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val btn1:Button=binding.button1
        val btn2:Button=binding.button2
        btn1.setOnClickListener{
          startActivity(Intent(this, LoginActivity::class.java))
        }
        btn2.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
