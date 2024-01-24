package com.oleksiikravchuk.littlelemon

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MyApplication : Application() {
    val itemDao by lazy {
        InventoryDatabase.getDatabase(applicationContext).itemDao()
    }

    val ktoClient by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(contentType = ContentType("text", "plain"))
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun getNetworkStatus(context: Context = this): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}