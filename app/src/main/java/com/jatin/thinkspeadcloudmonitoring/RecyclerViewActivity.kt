package com.jatin.thinkspeadcloudmonitoring

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var channelList: ArrayList<Channel>
    private lateinit var database: FirebaseDatabase
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        auth = FirebaseAuth.getInstance()
        binding.apply {
        }
//        binding.accountIV.setOnClickListener {
//            val dialog = Dialog(this)
//            dialog.setContentView(R.layout.log_out_message)
//            dialog.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )
//            dialog.show()
//            val gmailTV = dialog.findViewById<TextView>(R.id.userGmailTV)
//            val btnLogOut = dialog.findViewById<Button>(R.id.logOutBTN)
//
//            gmailTV.text = auth.currentUser?.email
//
//            btnLogOut.setOnClickListener {
//                AlertDialog.Builder(this)
//                    .setTitle("Are you sure you want to Log Out!")
//                    .setCancelable(true)
//                    .setPositiveButton("Yes") { _, _ ->
//                               logoutUser(this)
//                    }
//                    .setNegativeButton("No") { _, _ ->
//                        dialog.dismiss()
//                    }
//
//                    .show()
//            }

//        }
        binding.helpTV.setOnClickListener {
            val intent = Intent(this,HelpGuideActivity::class.java)
            startActivity(intent)

        }


        binding.addChannel.setOnClickListener {
            val intent = Intent(this, AddChannelActivity::class.java)
            startActivity(intent)
            channelList.clear()

        }

        binding.channelRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        channelList = arrayListOf()

        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("channels").child(auth.currentUser?.uid.toString())

        dbRef.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val channel = data.getValue(Channel::class.java)
                        channel?.let {
//                            channelList.clear()
                            channelList.add(channel)
                        }
                    }
                    binding.channelRecyclerView.adapter = ChannelAdapter(
                        this@RecyclerViewActivity,
                        channelList,
                        this@RecyclerViewActivity
                    )
                    binding.channelRecyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@RecyclerViewActivity,
                    "Failed to show channels",
                    Toast.LENGTH_SHORT
                ).show()

            }

        })
    }

    fun onItemClick(position: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", channelList[position].id)
        intent.putExtra("api", channelList[position].apiKey)
        intent.putExtra("name", channelList[position].name)

        startActivity(intent)

    }

    // Function to logout the user
    fun logoutUser(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("access_token") // Replace "access_token" with your actual key
        editor.apply() // Apply changes to Shared Preferences

        // Navigate to Login Activity
        val intent = Intent(context, LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }

}