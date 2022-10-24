package com.example.myfirstapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Logininfo(
    @SerializedName("email")
    @Expose
    var email:String,
    @SerializedName("password")
    @Expose
    var password:String):Serializable
{

}
