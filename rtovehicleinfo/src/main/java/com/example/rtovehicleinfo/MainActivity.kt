package com.example.rtovehicleinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rtovehicleinfo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener(){
            var vehicleNumber:String=binding.searchPhone.text.toString()
            if(vehicleNumber.isNotEmpty()){
                fetchdata(vehicleNumber)
            }else{
                Toast.makeText(this,"Please enter vehicle number",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun fetchdata(vehicleNumber:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("People Data")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists()){
                val OwnerName=it.child("ownername").value
                val VehicleBrand=it.child("vehicleBrand").value
                val OwnerNumber=it.child("ownerNumber").value
                binding.searchPhone.text.clear()
                binding.readName.text=OwnerName.toString()
                binding.readOperator.text=VehicleBrand.toString()
                binding.readLocation.text=OwnerNumber.toString()
            }else{
                Toast.makeText(this,"Please Enter valid Vehicle Number",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener(){
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}