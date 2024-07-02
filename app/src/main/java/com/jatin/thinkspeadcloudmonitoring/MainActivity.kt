package com.jatin.thinkspeadcloudmonitoring

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n", "ClickableViewAccessibility")
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




                binding.data1.text = "$fieldName1 = $dataStream1"
                binding.data2.text = "$fieldName2 = $dataStream2"
                binding.data3.text = "$fieldName3 = $dataStream3"
                binding.data4.text = "$fieldName4 = $dataStream4"
                binding.data5.text = "$fieldName5 = $dataStream5"
                binding.data6.text = "$fieldName6 = $dataStream6"
                binding.data7.text = "$fieldName7 = $dataStream7"
                binding.data8.text = "$fieldName8 = $dataStream8"

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
        binding.webView3.settings.setSupportZoom(true)
        binding.webView3.settings.builtInZoomControls = true
        binding.webView3.settings.displayZoomControls = false

        binding.webView4.settings.javaScriptEnabled = true // Enable JavaScript if needed
        binding.webView4.webViewClient = MyWebViewClient()
        binding.webView4.loadUrl("https://thingspeak.com/channels/${channelId}/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")
        binding.webView4.settings.setSupportZoom(true)
        binding.webView4.settings.builtInZoomControls = true
        binding.webView4.settings.displayZoomControls = false

        binding.webView5.settings.javaScriptEnabled = true // Enable JavaScript if needed
        binding.webView5.webViewClient = MyWebViewClient()
        binding.webView5.loadUrl("https://thingspeak.com/channels/${channelId}/charts/5?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")
        binding.webView5.settings.setSupportZoom(true)
        binding.webView5.settings.builtInZoomControls = true
        binding.webView5.settings.displayZoomControls = false

        binding.webView6.settings.javaScriptEnabled = true // Enable JavaScript if needed
        binding.webView6.webViewClient = MyWebViewClient()
        binding.webView6.loadUrl("https://thingspeak.com/channels/${channelId}/charts/6?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")
        binding.webView6.settings.setSupportZoom(true)
        binding.webView6.settings.builtInZoomControls = true
        binding.webView6.settings.displayZoomControls = false

        binding.webView7.settings.javaScriptEnabled = true // Enable JavaScript if needed
        binding.webView7.webViewClient = MyWebViewClient()
        binding.webView7.loadUrl("https://thingspeak.com/channels/${channelId}/charts/7?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")
        binding.webView7.settings.setSupportZoom(true)
        binding.webView7.settings.builtInZoomControls = true
        binding.webView7.settings.displayZoomControls = false

        binding.webView8.settings.javaScriptEnabled = true // Enable JavaScript if needed
        binding.webView8.webViewClient = MyWebViewClient()
        binding.webView8.loadUrl("https://thingspeak.com/channels/${channelId}/charts/8?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15")
//        binding.webView8.settings.setSupportZoom(true)
//        binding.webView8.settings.builtInZoomControls = true
//        binding.webView8.settings.displayZoomControls = false
//        binding.webView8.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
//        binding.webView8.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        binding.webView8.settings.useWideViewPort = true




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







