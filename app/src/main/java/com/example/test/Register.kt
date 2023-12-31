package com.example.test

import android.annotation.SuppressLint
import android.content.*
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.*
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register: ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_layout)
        val firebaseAuth= FirebaseAuth.getInstance()
        val name:EditText=findViewById(R.id.editTextName)
        val NumberPlate:EditText=findViewById(R.id.editTextNumberPlate)
        val email=findViewById<EditText>(R.id.editTextUsername)
        val password:EditText=findViewById(R.id.editTextPassword)
        val loginbtn:Button=findViewById(R.id.buttonLogin)
        val registerbtn:Button=findViewById(R.id.buttonRegister)
        loginbtn.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        }
        registerbtn.setOnClickListener {
            if(email.text.toString()!="" ||password.text.toString()!=""||name.text.toString()!=""||NumberPlate.text.toString()!="")
            {
                Toast.makeText(this,email.text.toString(),Toast.LENGTH_LONG).show()
                firebaseAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val database:FirebaseDatabase=FirebaseDatabase.getInstance()
                        val ref=database.reference
                        ref.child("Users").child(email.text.toString()).setValue(User(name.text.toString(), NumberPlate.text.toString(),email.text.toString()).toString()).addOnCompleteListener {
                            Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show()
                        }

                    }
                    else{
                        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_LONG).show()
            }
        }

    }
}
