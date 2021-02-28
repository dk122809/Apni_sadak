package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    val mAuth =FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//        backregister.setOnClickListener {
//            startActivity(Intent(this,MainActivity::class.java))
//        }


        progressregister.visibility =View.INVISIBLE

        val regBtn =findViewById<View>(R.id.btn2) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        backregister.setOnClickListener {
            startActivity(Intent(this,LoginOption::class.java))
        }



        regBtn.setOnClickListener(View.OnClickListener {
                view-> register()
            progressregister.visibility =View.VISIBLE
        })
    }

    private fun register(){
        val emailtxt =findViewById<View>(R.id.email1)as EditText
        val passwordtxt =findViewById<View>(R.id.pass1)as EditText
        val Name=findViewById<View>(R.id.name)as EditText

        val email =emailtxt.text.toString().trim()
        val password =passwordtxt.text.toString().trim()
        val UserName=Name.text.toString().trim()

        if (!email.isEmpty() && !password.isEmpty() &&!UserName.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user =mAuth.currentUser
                        val uid =user!!.uid
                        mDatabase.child(uid).child("Name").setValue(UserName)
                        startActivity(Intent(this,LoginOption::class.java))
                    }else{
                        progressregister.visibility =View.INVISIBLE
                        Toast.makeText(this,"Invalid Credential", Toast.LENGTH_SHORT).show()
                    }
                })
        }else{
            Toast.makeText(this,"plz enter details", Toast.LENGTH_SHORT).show()
        }
    }
}
