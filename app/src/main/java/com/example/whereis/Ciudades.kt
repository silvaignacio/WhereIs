package com.example.whereis

import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.whereis.model.Terremoto
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val listaTerremotos = findViewById<ListView>(R.id.listaTerremotos)
        val gson = Gson()
        val listaNombres:ArrayList<String> = ArrayList()
        val terremotos = gson.fromJson<ArrayList<Terremoto>>(obtenerTerremotos(), object :TypeToken<ArrayList<Terremoto>>(){}.type)

        terremotos.forEach {
            listaNombres.add(it.title)
        }

        println(listaNombres)
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombres)

        listaTerremotos.adapter = adaptador

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