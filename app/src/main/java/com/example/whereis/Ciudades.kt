package com.example.whereis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NavUtils
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Ciudades : AppCompatActivity() {

    val TAG = "com.example.whereis.ciudades.ciudad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val santiagoButton = findViewById<Button>(R.id.santiagoButton)
        var terremotos = JSONObject(obtenerTerremotos())
        println(terremotos.getJSONArray("features"))
        santiagoButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "santiago")
            startActivity(intent)
        }
    }

    @Throws(IOException::class)
    private fun obtenerTerremotos(): String {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var inputStream: InputStream? = null

        try {
            val url = URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02")
            val conn = url.openConnection() as HttpsURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return inputStream.bufferedReader().use {
                it.readText()
            }
        }finally {
            if(inputStream!= null) {
                inputStream.close()
            }
        }
    }
}