package com.coolcats.firebasenotetaker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.SignUpUser
import kotlinx.android.synthetic.main.signup_fragment_layout.*

class SignupFragment(): Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup_button.setOnClickListener {
            val email = signup_username_edittext.text.toString().trim()
            val password = signup_password_edittext.text.toString().trim()

            (requireActivity() as SignInActivity).signupUser(SignUpUser(email, password))
        }
    }
}
