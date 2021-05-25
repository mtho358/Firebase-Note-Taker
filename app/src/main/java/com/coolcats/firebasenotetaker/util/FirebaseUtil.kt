package com.coolcats.firebasenotetaker.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtil {

    fun getNotesDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference.child("NotePosts")
    fun getUsersDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

}