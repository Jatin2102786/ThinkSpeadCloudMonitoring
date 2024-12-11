package com.jatin.thinkspeadcloudmonitoring

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class RecyclerViewActivity : AppCompatActivity(),ChannelAdapter.OnItemClickListener {

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
        channelList = arrayListOf()

        setupToolbar()
        setupRecyclerView()
        fetchChannels()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        binding.helpTV.setOnClickListener {
            startActivity(Intent(this, HelpGuideActivity::class.java))
        }

        binding.addChannel.setOnClickListener {
            startActivity(Intent(this, AddChannelActivity::class.java))
            channelList.clear() // Ensure this is really necessary
        }
    }

    private fun setupRecyclerView() {
        binding.channelRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.channelRecyclerView.adapter = ChannelAdapter(this, channelList, this)
    }

    private fun fetchChannels() {
        database = FirebaseDatabase.getInstance()
        dbRef = database.getReference("channels").child(auth.currentUser?.uid.toString())

        dbRef.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val newChannels = ArrayList<Channel>() // Use a temporary list to avoid modifying the original during iteration
                    for (data in snapshot.children) {
                        val channel = data.getValue(Channel::class.java)
                        channel?.let { newChannels.add(it) }
                    }
                    updateChannelList(newChannels)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecyclerViewActivity, "Failed to show channels", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateChannelList(newChannels: List<Channel>) {
        val initialSize = channelList.size
        channelList.clear() // Clear the existing list
        channelList.addAll(newChannels) // Add the new channels

        // Notify the adapter of the changes
        binding.channelRecyclerView.adapter?.notifyItemRangeRemoved(0, initialSize) // Notify for old items
        binding.channelRecyclerView.adapter?.notifyItemRangeInserted(0, newChannels.size) // Notify for new items
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", channelList[position].id)
            putExtra("api", channelList[position].apiKey)
            putExtra("name", channelList[position].name)
        }
        startActivity(intent)
    }

    override fun onItemLongClick(position: Int) {
        val channelId = channelList[position].id


        AlertDialog.Builder(this)
            .setTitle("Delete Channel")
            .setMessage("Are you sure you want to delete the channel?")
            .setPositiveButton("Yes") {_,_ -> deleteChannel(channelId,position)}
            .setNegativeButton("No",null)
            .show()
    }

    private fun deleteChannel(channelId: String, position: Int) {
        val dbref = FirebaseDatabase.getInstance()
            .getReference("channels")
            .child(auth.currentUser?.uid.toString())
            .child(channelId)

        dbref.removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Remove the item from the list
                channelList.removeAt(position)

                // Notify the adapter that an item was removed
                binding.channelRecyclerView.adapter?.notifyItemRemoved(position)
                binding.channelRecyclerView.adapter?.notifyItemRangeChanged(position, channelList.size)

                Toast.makeText(this, "Channel deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to delete channel", Toast.LENGTH_SHORT).show()
            }
        }
    }



}
