package com.example.chatfusion.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref
{
    @SuppressLint("CommitPrefEdits")
    fun storeData(email:String,
                  fullName:String,
                  role:String,
                  imageUrl :String,
                  context : Context
    ){
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("fullName",fullName)
        editor.putString("email",email)
        editor.putString("role",role)
        editor.putString("imageUrl",imageUrl)
        editor.apply()

    }
    fun  getFullName(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("fullName","")!!
    }

    fun  getEmail(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("email","")!!
    }

    fun  getRole(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("role","")!!
    }
    fun  getImage(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users", MODE_PRIVATE)
        return sharedPreferences.getString("imageUrl","")!!
    }



}