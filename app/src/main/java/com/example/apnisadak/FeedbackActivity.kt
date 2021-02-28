package com.example.apnisadak

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_feedback.*
import java.util.*

class FeedbackActivity : AppCompatActivity() {




    lateinit var editTextsubject:EditText
    lateinit var editTextlacolity: EditText
    lateinit var editTextfeedback: EditText
    lateinit var submitButton: Button
    lateinit var image:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        submitButton =findViewById(R.id.submit1)
        editTextsubject =findViewById(R.id.subject1)
        editTextlacolity =findViewById(R.id.locality1)
        editTextfeedback =findViewById(R.id.feedback1)
        image =findViewById(R.id.imageView)
        progressfeedback.visibility =View.INVISIBLE

        submitButton.setOnClickListener{
            savefeedback()
            uploadImage()
            progressfeedback.visibility =View.VISIBLE
        }
        image.setOnClickListener{

            var intent =Intent(Intent.ACTION_PICK)
            intent.type ="image/*"
            startActivityForResult(intent,0)
        }
    }

    private fun uploadImage() {

        if (selectedPhotoUri == null) return
        val filename =UUID.randomUUID().toString()
        val ref =FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
//               Toast.makeText(this,"Image Uploaded Successfuly",Toast.LENGTH_SHORT).show()

            }

    }

    var selectedPhotoUri:Uri? =null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode  == 0 && resultCode==Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)
            val bitmapDrawable =BitmapDrawable(bitmap)
            imageView.setBackgroundDrawable(bitmapDrawable)
        }
        else{
            Toast.makeText(this,"Somthing went wrong Plz check your internet connection",Toast.LENGTH_LONG).show()
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

        val ref =FirebaseDatabase.getInstance().getReference("Feedback")
        val heroId = ref.push().key

        val FEEDBACK = FeedBack(heroId,Subject,Locality,Feedback)

        if (heroId != null) {
            ref.child(heroId).setValue(FEEDBACK).addOnCompleteListener {
                startActivity(Intent(this, AfterAllFeedBackActivity::class.java))
                Toast.makeText(this,"FeedBack Saved Suchefully",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Somthing went wrong Plz check your internet connection",Toast.LENGTH_LONG).show()
        }

    }
}
