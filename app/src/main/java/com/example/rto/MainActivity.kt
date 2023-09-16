package com.example.rto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rto.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener(){
            startActivity(Intent(this,upload::class.java))
        }
        binding.mainUpdate.setOnClickListener(){
            startActivity(Intent(this,updateDetails::class.java))
        }
        binding.mainDelete.setOnClickListener(){
            startActivity(Intent(this,delete::class.java))
        }

    }


}