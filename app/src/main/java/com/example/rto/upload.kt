package com.example.rto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rto.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class upload : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(){
            var name=binding.uploadName.text.toString()
            var operator=binding.uploadOperator.text.toString()
            var location=binding.uploadLocation.text.toString()
            var phone=binding.uploadPhone.text.toString()
            databaseReference=FirebaseDatabase.getInstance().getReference("People Data")
            var peopleData=PeopleData(name, operator, location, phone)
            databaseReference.child(location).setValue(peopleData).addOnCompleteListener {
                binding.uploadName.text.clear()
                binding.uploadOperator.text.clear()
                binding.uploadLocation.text.clear()
                binding.uploadPhone.text.clear()

                Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }.addOnFailureListener(){
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        }
    }
}