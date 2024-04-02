package com.jatin.thinkspeadcloudmonitoring

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityAddChannelBinding

class AddChannelActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityAddChannelBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddChannelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbRef = database.getReference("channels").child(auth.currentUser?.uid.toString())


        binding.btnAddChannel.setOnClickListener {
            if (
                binding.ChannelID.text!!.isEmpty()
            ) {
                binding.ChannelID.error = "Please fill this field"
            } else if (
                binding.apiKey.text!!.isEmpty()
            ) {
                binding.apiKey.error = "Please fill this field"
            }
            else {


                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val email = auth.currentUser?.email.toString()
                    val id = binding.ChannelID.text.toString().trim()
                    val key = binding.apiKey.text.toString().trim()
                    val name = binding.channelName.text.toString().trim()


                    val newChannel = Channel(email,name,id,key)

                    dbRef.push().setValue(newChannel)
                        .addOnSuccessListener {
                            Toast.makeText(this,"Channel added successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Failed to add channel due ${it.toString()}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                }
            }
        }
    }
}