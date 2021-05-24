package com.coolcats.firebasenotetaker.view

import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.coolcats.firebasenotetaker.R
import com.coolcats.firebasenotetaker.model.SignUpUser
import com.coolcats.firebasenotetaker.view.fragment.SignupFragment
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signin_activity.*

class SignInActivity : AppCompatActivity(){

    private val signupFragment = SignupFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)

        signin_button.setOnClickListener {
            val email = email_edittext.text.toString().trim()
            val password = password_edittext.text.toString().trim()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true)
                        openHomePage()
                    else
                        showVerificationDialog(email)
                } else {
                    showError(it)
                }
            }
        }

        register_button.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                .addToBackStack(signupFragment.tag)
                .replace(R.id.signup_frame, signupFragment)
                .commit()
        }
    }

    fun signupUser(signUpUser: SignUpUser){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(signUpUser.email, signUpUser.password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true)
                        openHomePage()
                    else{
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        showVerificationDialog(signUpUser.email)
                    }
                }else{
                    showError(task)
                }
            }
    }


    private fun showError(task: Task<AuthResult>){
        Snackbar.make(
            root,
            "Error: ${task.exception?.localizedMessage}",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showVerificationDialog(signUpUser: String) {
        AlertDialog.Builder(
            ContextThemeWrapper(
                this,
                R.style.Theme_AppCompat_Dialog
            )
        ).setTitle("Registration Verification")
            .setMessage("Please check your email: ${signUpUser}. A confirmation email has been sent.")
            .setPositiveButton("Ok"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun openHomePage() {
        startActivity(Intent(this, HomePageActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}