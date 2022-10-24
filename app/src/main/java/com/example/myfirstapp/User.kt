package com.example.myfirstapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("fname")
    @Expose
    val fname:String,
    @SerializedName("lname")
    @Expose
                val lname:String,
    @SerializedName("email")
    @Expose
                 val email:String,
    @SerializedName("password")
    @Expose
                 val password:String
                ): Serializable
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (fname != other.fname) return false
        if (lname != other.lname) return false
        if (email != other.email) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fname.hashCode()
        result = 31 * result + lname.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }

}
