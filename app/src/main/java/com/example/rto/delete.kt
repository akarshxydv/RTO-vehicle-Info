package com.example.rto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rto.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class delete : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deleteButton.setOnClickListener(){
            var vehicleNumber=binding.deletevehicle.text.toString()
            if(vehicleNumber.isNotEmpty()){
                deletedata(vehicleNumber)
            }else{
                Toast.makeText(this,"Please Enter Vehicle Number",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun deletedata(vehicleNumber:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("People Data")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener(){
            if(it.exists()){
                databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener(){
                   binding.deletevehicle.text.clear()
                    Toast.makeText(this,"Data deleted",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Enter valid vehicle number",Toast.LENGTH_SHORT).show()
            }
        }
    }
}