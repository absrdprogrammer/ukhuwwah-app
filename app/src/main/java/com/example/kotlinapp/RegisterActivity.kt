package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var nameText : EditText = findViewById(R.id.editTextPersonName)
        var phoneNumberText : EditText = findViewById(R.id.editTextPhoneNumber)
        var emailText : EditText = findViewById(R.id.editTextEmailID)
        var passwordText : EditText = findViewById(R.id.editTextPassword)
        var addressText : EditText = findViewById(R.id.editTextAddress)


    }
}