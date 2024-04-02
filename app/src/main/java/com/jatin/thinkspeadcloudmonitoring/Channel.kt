package com.jatin.thinkspeadcloudmonitoring

import android.provider.ContactsContract.CommonDataKinds.Email


data class Channel(
    val email: String,
    val name: String,
    val id: String,
    val apiKey: String){
    constructor() : this("", "","","")

}
