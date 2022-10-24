package com.example.myfirstapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ServerResponse(
    @SerializedName("id")
    @Expose
    var id:String?=null,
    @SerializedName("token")
    @Expose
    var tokes:String?=null,
    @SerializedName("error")
    @Expose
    var error:String?=null
):Serializable
