package com.example.whereis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.whereis.model.Ciudad

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val temp = findViewById<TextView>(R.id.temp)
        val city = findViewById<TextView>(R.id.city)
        val status = findViewById<TextView>(R.id.status)

        val lugar = intent.getStringExtra("lugar")
        val magnitud = intent.getStringExtra("magnitud")
        val title = intent.getStringExtra("title")

        temp.text = magnitud
        city.text = lugar
        status.text = title

    }
}