package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private lateinit var fb: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        fb = FirebaseAuth.getInstance()


        vb.signup.setOnClickListener {
            signup()
        }
        vb.signin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        fb.fetchSignInMethodsForEmail(vb.email.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                if (task.result != null)
                fb.signInWithEmailAndPassword(vb.email.text.toString(), vb.pass.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
//                    fb.currentUser?.updatePassword("hello1234")
//                    fb.currentUser?.updateEmail("email@gmail.com")
                            fb.currentUser?.sendEmailVerification()
                            Log.e("Login", "success ${fb.currentUser}")
                        } else {
                            Log.e("Failed ", "${task.exception}")
                        }
                    }
            }
        }
    }

    private fun signup() {
        val email = vb.email.text.toString()
        val pass = vb.pass.text.toString()
        Log.e("email", email + " " + pass)
        fb.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            Log.e("taksing ", "$task")
            if (task.isSuccessful) {
                Log.e("hello", "success")
            } else {
                Log.e("warn ", "${task.exception}")
            }
        }
    }
}