package com.example.chatfusion.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatfusion.model.ThreadModel
import com.example.chatfusion.model.UserModel
import com.example.chatfusion.utils.SharedPref
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.storage
import java.util.UUID

class AddThreadViewModel : ViewModel() {

    private val db = FirebaseDatabase.getInstance()
    private val userRef = db.getReference("threads")

  private val storageRef = Firebase.storage.reference
  private val imageRef = storageRef.child("threads/${UUID.randomUUID()}.jpg")


    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean> = _isPosted


     fun saveImage(
        thread: String,
        userId: String,
        imageUri: Uri

    )
    {
        val uploadTask = imageRef.putFile(imageUri)
        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                saveData(thread, userId, it.toString())
            }
        }
    }

     fun saveData(
                         thread: String,
                         userId: String,
                         image: String

    ){
        val threadData = ThreadModel(thread, image, userId, System.currentTimeMillis().toString())

        userRef.child(userRef.push().key!!).setValue(threadData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    _isPosted.postValue(true)
                }
                else
                {
                    _isPosted.postValue(false)
                }
            }
    }
  }