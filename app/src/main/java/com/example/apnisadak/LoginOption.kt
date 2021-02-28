package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_option.*

class LoginOption : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_option)
        loginemail.setOnClickListener{
            startActivity(Intent(this,LoginByEmailActivity::class.java))
        }

        register.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
        }

        phonelogin.setOnClickListener{
            startActivity(Intent(this,PhoneAuthentication::class.java))
        }
    }
}
