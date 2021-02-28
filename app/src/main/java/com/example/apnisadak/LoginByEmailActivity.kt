package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_by_email.*

class LoginByEmailActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_by_email)

        progressloginemail.visibility =View.INVISIBLE
        val loginBtn = findViewById<View>(R.id.btn) as Button
        val sbtn = findViewById<View>(R.id.btn1) as TextView

        back.setOnClickListener {
            startActivity(Intent(this,LoginOption::class.java))
        }

        loginBtn.setOnClickListener(View.OnClickListener { view ->
            login()
            progressloginemail.visibility =View.VISIBLE
        })

        sbtn.setOnClickListener(View.OnClickListener { view ->
            register()

        })

    }

    private fun register() {
        startActivity(Intent(this,Register::class.java))
    }

    private fun login() {
        val emailTxt =findViewById<View>(R.id.email)as EditText
        val password =findViewById<View>(R.id.pass)as EditText

        var Email = emailTxt.text.toString().trim()
        var Password = password.text.toString().trim()

        if (!Email.isEmpty() && !Password.isEmpty()){
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"LoginSuceesful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))}
                    else{
                        progressloginemail.visibility =View.INVISIBLE
                        Toast.makeText(this,"Invalid Enail or Password!", Toast.LENGTH_SHORT).show()
                    }
                })
        }else{
            Toast.makeText(this,"Plz fill Details", Toast.LENGTH_SHORT).show()
        }
    }
}