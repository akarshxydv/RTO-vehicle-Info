package com.example.rto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.rto.databinding.ActivityUpdateDetailsBinding
import com.example.rto.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class updateDetails : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDetailsBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.check.setOnClickListener(){
           var  vehicleNumber=binding.referenceVehicle.text.toString()
            databaseReference=FirebaseDatabase.getInstance().getReference("People Data")
            databaseReference.child(vehicleNumber).get().addOnSuccessListener {
                if(it.exists()){
                    binding.updatePnumber.visibility= View.VISIBLE
                    binding.updateName.visibility= View.VISIBLE
                    binding.updateButton.visibility= View.VISIBLE
                    binding.updatebrand.visibility= View.VISIBLE

                }else{
                    Toast.makeText(this,"Invalid Vehicle number",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.updateButton.setOnClickListener(){
            var  vehicleNumber=binding.referenceVehicle.text.toString()
            var name=binding.updateName.text.toString()
            var brand=binding.updatebrand.text.toString()
            var phone=binding.updatePnumber.text.toString()

            updateDetails(vehicleNumber, name, brand, phone)

                binding.updatePnumber.visibility= View.GONE
                binding.updateName.visibility= View.GONE
                binding.updateButton.visibility= View.GONE
                binding.updatebrand.visibility= View.GONE

        }

    }


    private fun updateDetails(vehicleNumber:String,name:String,brand:String,phone:String){
        databaseReference=FirebaseDatabase.getInstance().getReference("People Data")
        val updateVehicle= mapOf<String,String>("ownername" to name,"vehicleBrand" to brand,"ownerNumber" to phone)
        databaseReference.child(vehicleNumber).updateChildren(updateVehicle).addOnSuccessListener {
            binding.referenceVehicle.text.clear()
            binding.updatePnumber.text.clear()
            binding.updatebrand.text.clear()
            binding.updateName.text.clear()
            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener(){
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }

    }
}