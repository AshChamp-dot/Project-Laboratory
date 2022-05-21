package com.instamobile.kotlinlogin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        registeruser.setOnClickListener {
            if (email.text.toString().isEmpty()) {
                email.error = "Please enter email"
                email.requestFocus()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Please enter valid email"
                email.requestFocus()
            }

            if (password.text.toString().isEmpty()) {
                email.error = "Please enter password"
                email.requestFocus()
            }

            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener(this) {
                task -> if(task.isSuccessful){
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener(){
                        task -> if(task.isSuccessful){
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    }

            }
                else{
                    Toast.makeText(baseContext,"Sign up failed, try after some time",Toast.LENGTH_SHORT).show()


            }


            }


        }
    }
}