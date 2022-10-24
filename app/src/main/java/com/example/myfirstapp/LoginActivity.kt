package com.example.myfirstapp

import android.app.FragmentTransaction
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val fragmentmanager=supportFragmentManager
            val transaction=fragmentmanager.beginTransaction()
            transaction.replace(R.id.loginfragment,Fragmentlogin())
            transaction.commit()
    }
}