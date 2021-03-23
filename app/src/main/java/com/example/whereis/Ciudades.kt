package com.example.whereis

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.whereis.adapter.CustomAdapter
import com.example.whereis.model.Terremoto
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection
import kotlin.collections.ArrayList


class Ciudades : AppCompatActivity() {

    val TAG = "com.example.whereis.ciudades.ciudad"
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        val listaTerremotos = findViewById<ListView>(R.id.listaTerremotos)
        val gson = Gson()
        val listaNombres:ArrayList<Terremoto> = ArrayList()
        val terremotos = gson.fromJson<ArrayList<Terremoto>>(obtenerTerremotos(), object :TypeToken<ArrayList<Terremoto>>(){}.type)

        terremotos.forEach {
            listaNombres.add(Terremoto(it.title, it.place, it.mag))
        }


        val adaptador = CustomAdapter(this, listaNombres)

        listaTerremotos.adapter = adaptador

        listaTerremotos.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as Terremoto
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("title", element.title)
            intent.putExtra("magnitud", element.mag)
            intent.putExtra("lugar", element.place)
            startActivity(intent)
        }

    }

    @Throws(IOException::class)
    private fun obtenerTerremotos(): String {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var inputStream: InputStream? = null

        try {
            val url =
                URL("https://eartquake-api.herokuapp.com/terremoto/")
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return inputStream.bufferedReader().use {
                it.readText()
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }
}