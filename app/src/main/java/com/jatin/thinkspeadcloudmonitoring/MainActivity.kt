package com.jatin.thinkspeadcloudmonitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val intent = intent
        val thingSpeakApi= ThingSpeakApi()
        val channelId = intent.getStringExtra("id")
        val apiKey = intent.getStringExtra("api")
        val channelName = intent.getStringExtra("name")




        binding.channelName.text = channelName.toString()
        binding.btnRefresh.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
            val data = thingSpeakApi.fetchDataFromThingSpeak(channelId!!, apiKey!!)
                val jsonObject = JSONObject(data)

                val channel = jsonObject.getJSONObject("channel")
                val feed = jsonObject.getJSONArray("feeds").getJSONObject(0)
                val fieldName1 = channel.getString("field1")
                val fieldName2 = channel.getString("field2")
                val fieldName3 = channel.getString("field3")
                val fieldName4 = channel.getString("field4")
                val fieldName5 = channel.getString("field5")
                val fieldName6 = channel.getString("field6")
                val fieldName7 = channel.getString("field7")
                val fieldName8 = channel.getString("field8")

                val dataStream1 = feed.getString("field1")
                val dataStream2 = feed.getString("field2")
                val dataStream3 = feed.getString("field3")
                val dataStream4 = feed.getString("field4")
                val dataStream5 = feed.getString("field5")
                val dataStream6 = feed.getString("field6")
                val dataStream7 = feed.getString("field7")
                val dataStream8 = feed.getString("field8")


                binding.field1.text = fieldName1
                binding.field2.text = fieldName2
                binding.field3.text = fieldName3
                binding.field4.text = fieldName4
                binding.field5.text = fieldName5
                binding.field6.text = fieldName6
                binding.field7.text = fieldName7
                binding.field8.text = fieldName8

                binding.data1.text = dataStream1
                binding.data2.text = dataStream2
                binding.data3.text = dataStream3
                binding.data4.text = dataStream4
                binding.data5.text = dataStream5
                binding.data6.text = dataStream6
                binding.data7.text = dataStream7
                binding.data8.text = dataStream8

            }
        }

    }
}







