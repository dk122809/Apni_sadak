package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedback.setOnClickListener{
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        otherreport.setOnClickListener {
            startActivity(Intent(this,OtherFeedbackActivity::class.java))
        }
        aboutus.setOnClickListener {
            startActivity(Intent(this,AboutUsActivity::class.java))
        }
        contactus.setOnClickListener {
            startActivity(Intent(this,ContactUSActivity::class.java))
        }
        ourservice.setOnClickListener {
            startActivity(Intent(this,OurExtraServicesActivity::class.java))
        }







        mAuth = FirebaseAuth.getInstance()
//        signOut.setOnClickListener {
//                view: View? -> mAuth.signOut()
//            startActivity(Intent(this, PhoneAuthentication::class.java))
//            Toast.makeText(this, "Logged out Successfully :)", Toast.LENGTH_LONG).show()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        when (item.itemId){
            R.id.signOut ->{
                 mAuth.signOut()
                startActivity(Intent(this, LoginOption::class.java))
            }

        }



        return super.onOptionsItemSelected(item)
    }


    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null) {
            startActivity(Intent(this, LoginOption::class.java))
        }else {
            Toast.makeText(this, "Already Signed in :)", Toast.LENGTH_LONG).show()
        }
    }


}