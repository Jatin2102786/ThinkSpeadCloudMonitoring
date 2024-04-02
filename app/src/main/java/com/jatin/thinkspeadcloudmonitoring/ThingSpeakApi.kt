package com.jatin.thinkspeadcloudmonitoring


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ThingSpeakApi {
    private val client = OkHttpClient()

    suspend fun fetchDataFromThingSpeak(channelId: String, apiKey: String): String {
        return withContext(Dispatchers.IO) {
            val url = "https://api.thingspeak.com/channels/${channelId}/feeds.json?api_key=${apiKey}&results=1"

            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            return@withContext response.body?.string()?:""
        }
    }




}
