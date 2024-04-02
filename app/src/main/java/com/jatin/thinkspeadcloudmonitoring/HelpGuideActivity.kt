package com.jatin.thinkspeadcloudmonitoring

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityHelpGuideBinding

class HelpGuideActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHelpGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        setSupportActionBar(binding.toolbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        binding.apply {
            toolbar.setNavigationOnClickListener {
                intent()

            }
        }

    }
    private fun intent(){
        val intent = Intent(this,RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}