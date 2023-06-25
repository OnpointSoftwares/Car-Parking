package com.example.test

import android.content.*
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.*
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        val firebaseAuth= FirebaseAuth.getInstance()
        val email=findViewById<EditText>(R.id.editTextUsername)
        val password:EditText=findViewById(R.id.editTextPassword)
        val loginbtn:Button=findViewById(R.id.buttonLogin)
        val regbtn:Button=findViewById(R.id.buttonRegister)
        regbtn.setOnClickListener {
            val intent=Intent(this,Register::class.java)
            startActivity(intent)
        }
        loginbtn.setOnClickListener {
            if(email.text.toString()!="" && password.text.toString()!="")
            {
                firebaseAuth.signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,"Invalid credentials",Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_LONG).show()
            }
        }

    }
}
