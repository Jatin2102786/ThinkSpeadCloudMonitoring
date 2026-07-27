package com.jatin.thinkspeadcloudmonitoring

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding




    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n", "ClickableViewAccessibility",
        "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val intent = intent
        val thingSpeakApi = ThingSpeakApi()
        val channelId = intent.getStringExtra("id")
        val apiKey = intent.getStringExtra("api")
        val channelName = intent.getStringExtra("name")

        binding.channelName.text = channelName.toString()
        binding.imgButtonBack.setOnClickListener {
            finish()
        }

        CoroutineScope(Dispatchers.Main).launch {
            val data = thingSpeakApi.fetchDataFromThingSpeak(channelId!!, apiKey!!)
            val jsonObject = JSONObject(data)

            val channel = jsonObject.getJSONObject("channel")
            val feed = jsonObject.getJSONArray("feeds").getJSONObject(0)
            val fieldName1 = channel.optString("field1", "Field 1")
            val fieldName2 = channel.optString("field2", "Field 2")
            val fieldName3 = channel.optString("field3", "Field 3")
            val fieldName4 = channel.optString("field4", "Field 4")
            val fieldName5 = channel.optString("field5", "Field 5")
            val fieldName6 = channel.optString("field6", "Field 6")
            val fieldName7 = channel.optString("field7", "Field 7")
            val fieldName8 = channel.optString("field8", "Field 8")




            val dataStream1 = feed.optString("field1", "No Data")
            val dataStream2 = feed.optString("field2", "No Data")
            val dataStream3 = feed.optString("field3", "No Data")
            val dataStream4 = feed.optString("field4", "No Data")
            val dataStream5 = feed.optString("field5", "No Data")
            val dataStream6 = feed.optString("field6", "No Data")
            val dataStream7 = feed.optString("field7", "No Data")
            val dataStream8 = feed.optString("field8", "No Data")





            if (dataStream1 == null || dataStream1.isNullOrEmpty() || dataStream1 == "No Data") {
                binding.layout1.visibility = View.GONE
            } else {
                binding.data1.text = "$fieldName1 = $dataStream1"
                binding.layout1.visibility = View.VISIBLE
            }

            if (dataStream2 == null || dataStream2.isNullOrEmpty() || dataStream2 == "No Data") {
                binding.layout2.visibility = View.GONE
            } else {
                binding.data2.text = "$fieldName2 = $dataStream2"
                binding.layout2.visibility = View.VISIBLE
            }

            if (dataStream3 == null || dataStream3.isNullOrEmpty() || dataStream3 == "No Data") {
                binding.layout3.visibility = View.GONE
            } else {
                binding.data3.text = "$fieldName3 = $dataStream3"
                binding.layout3.visibility = View.VISIBLE
            }

            if (dataStream4 == null || dataStream4.isNullOrEmpty() || dataStream4 == "No Data") {
                binding.layout4.visibility = View.GONE
            } else {
                binding.data4.text = "$fieldName4 = $dataStream4"
                binding.layout4.visibility = View.VISIBLE
            }

            if (dataStream5 == null || dataStream5.isNullOrEmpty() || dataStream5 == "No Data") {
                binding.layout5.visibility = View.GONE
            } else {
                binding.data5.text = "$fieldName5 = $dataStream5"
                binding.layout5.visibility = View.VISIBLE
            }

            if (dataStream6 == null || dataStream6.isNullOrEmpty() || dataStream6 == "No Data") {
                binding.layout6.visibility = View.GONE
            } else {
                binding.data6.text = "$fieldName6 = $dataStream6"
                binding.layout6.visibility = View.VISIBLE
            }

            if (dataStream7 == null || dataStream7.isNullOrEmpty() || dataStream7 == "No Data") {
                binding.layout7.visibility = View.GONE
            } else {
                binding.data7.text = "$fieldName7 = $dataStream7"
                binding.layout7.visibility = View.VISIBLE
            }


            if (dataStream8 == null || dataStream8.isNullOrEmpty() || dataStream8 == "No Data") {
                binding.layout8.visibility = View.GONE
            }
            else{
                binding.data8.text = "$fieldName8 = $dataStream8"
                binding.layout8.visibility = View.VISIBLE
            }


        }


            binding.webView1.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView1.webViewClient = MyWebViewClient()
            binding.webView1.loadUrl("https://thingspeak.com/channels/${channelId}/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView2.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView2.webViewClient = MyWebViewClient()
            binding.webView2.loadUrl("https://thingspeak.com/channels/${channelId}/charts/2?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView3.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView3.webViewClient = MyWebViewClient()
            binding.webView3.loadUrl("https://thingspeak.com/channels/${channelId}/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView4.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView4.webViewClient = MyWebViewClient()
            binding.webView4.loadUrl("https://thingspeak.com/channels/${channelId}/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView5.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView5.webViewClient = MyWebViewClient()
            binding.webView5.loadUrl("https://thingspeak.com/channels/${channelId}/charts/5?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView6.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView6.webViewClient = MyWebViewClient()
            binding.webView6.loadUrl("https://thingspeak.com/channels/${channelId}/charts/6?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView7.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView7.webViewClient = MyWebViewClient()
            binding.webView7.loadUrl("https://thingspeak.com/channels/${channelId}/charts/7?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")

            binding.webView8.settings.javaScriptEnabled = true // Enable JavaScript if needed
            binding.webView8.webViewClient = MyWebViewClient()
            binding.webView8.loadUrl("https://thingspeak.com/channels/${channelId}/charts/8?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")



    }

    

    private fun MyWebViewClient(): WebViewClient {
        return object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            @SuppressLint("NewApi")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return if (url.startsWith("https://thingspeak.com")) {
                    false
                } else {
                    view.loadUrl(url)
                    true
                }
            }
        }
    }
}







