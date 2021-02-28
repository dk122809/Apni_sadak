package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_after_all_feed_back.*

class AfterAllFeedBackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_all_feed_back)

        mainfeedback.setOnClickListener{
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        otherfeedback.setOnClickListener(){
            startActivity(Intent(this, OtherFeedbackActivity::class.java))
        }
    }
}
