package com.example.whereis.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.whereis.MainActivity

class Network {
    companion object {
        fun comprobarRed(activity: AppCompatActivity):Boolean {
            val conectividad = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = conectividad.activeNetwork
            return networkInfo != null
        }
    }
}