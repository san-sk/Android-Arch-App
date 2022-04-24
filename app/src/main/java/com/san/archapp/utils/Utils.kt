package com.san.archapp.utils

import android.content.ContentResolver
import android.content.Context
import android.net.*
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File


val networkRequest: NetworkRequest? = NetworkRequest.Builder()
    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .build()

private val networkCallback = object : ConnectivityManager.NetworkCallback() {
    // network is available for use
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
    }

    // Network capabilities have changed for the network
    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        val unmetered =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
    }

    // lost network connection
    override fun onLost(network: Network) {
        super.onLost(network)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun registerNetworkCallBack(context: Context) {
    val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    if (networkRequest != null) {
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

}


fun getRawUri(context: Context, filename: String): Uri? {
    return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + context.packageName.toString() + "/raw/" + filename)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}