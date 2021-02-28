package com.example.apnisadak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_other_feedback.*

class OtherFeedbackActivity : AppCompatActivity() {

    lateinit var editTextsubject: EditText
    lateinit var editTextlacolity: EditText
    lateinit var editTextfeedback: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_feedback)

        submitButton =findViewById(R.id.submit)
        editTextsubject =findViewById(R.id.subject)
        editTextlacolity =findViewById(R.id.locality)
        editTextfeedback =findViewById(R.id.feedback2)

        progressotherreport.visibility = View.INVISIBLE

        submitButton.setOnClickListener{
            savefeedback()
            progressotherreport.visibility = View.VISIBLE
        }
    }
    private fun savefeedback(){
        val Subject =editTextsubject.text.toString().trim()
        val Locality = editTextlacolity.text.toString().trim()
        val Feedback = editTextfeedback.text.toString().trim()
//        val Image = image.setImageURI()

        if (Subject.isEmpty()){
            editTextsubject.error ="Plz enter Subject Of feedback"
            return
        }
        if (Locality.isEmpty()){
            editTextlacolity.error ="Plz Provide your Locality"
            return
        }
        if (Feedback.isEmpty()){
            editTextfeedback.error ="Plz Provide your Locality"
            return
        }

//        val FEEDBACK = FeedBack(Subject,Locality,Feedback)

        val ref = FirebaseDatabase.getInstance().getReference("OtherFeedBack")
        val heroId = ref.push().key

        val FEEDBACK = FeedBack(heroId,Subject,Locality,Feedback)

        if (heroId != null) {
            ref.child(heroId).setValue(FEEDBACK).addOnCompleteListener {
                startActivity(Intent(this, AfterAllFeedBackActivity::class.java))
                Toast.makeText(this,"FeedBack SavedSuchefully", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Somthing went wrong Plz check your internet connection",Toast.LENGTH_LONG).show()
        }

    }
}
