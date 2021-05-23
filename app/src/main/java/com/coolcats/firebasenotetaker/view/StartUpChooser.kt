package com.coolcats.firebasenotetaker.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class StartUpChooser : AppCompatActivity() {

    private lateinit var openIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified != false) {
            openIntent = Intent(this, HomePageActivity::class.java)
        }else {
            openIntent = Intent(this, SignInActivity::class.java)
        }

        startActivity(openIntent.also{
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}