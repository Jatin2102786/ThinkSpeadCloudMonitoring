package com.jatin.thinkspeadcloudmonitoring

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
class RecyclerViewActivity : AppCompatActivity(), ChannelAdapter.OnItemClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: ChannelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        setupRecyclerView()
        fetchChannels()

        binding.helpTV.setOnClickListener {
            startActivity(Intent(this, HelpGuideActivity::class.java))
        }

        binding.addChannel.setOnClickListener {
            startActivity(Intent(this, AddChannelActivity::class.java))
        }

        binding.toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))

        }
    }

    private fun setupRecyclerView() {
        adapter = ChannelAdapter(this)
        binding.channelRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.channelRecyclerView.adapter = adapter
    }

    private fun fetchChannels() {

        binding.pgBar.visibility = View.VISIBLE
        val dbRef = FirebaseDatabase.getInstance().getReference("channels")
            .child(auth.currentUser?.uid.toString())

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newChannels = mutableListOf<Channel>()
                for (data in snapshot.children) {
                    data.getValue(Channel::class.java)?.let { newChannels.add(it) }
                }
                // ListAdapter handles the notification automatically
                adapter.submitList(newChannels)
                binding.pgBar.visibility = View.GONE

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecyclerViewActivity, "Failed to load", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(position: Int) {
        val item = adapter.currentList[position]
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", item.id)
            putExtra("api", item.apiKey)
            putExtra("name", item.name)
        }
        startActivity(intent)
    }

    override fun onItemLongClick(position: Int) {
        val channelId = adapter.currentList[position].id
        AlertDialog.Builder(this)
            .setTitle("Delete Channel")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { _, _ -> deleteChannel(channelId) }
            .setNegativeButton("No", null)
            .show()
    }

    private fun deleteChannel(channelId: String) {
        FirebaseDatabase.getInstance().getReference("channels")
            .child(auth.currentUser?.uid.toString())
            .child(channelId).removeValue()
    }
}