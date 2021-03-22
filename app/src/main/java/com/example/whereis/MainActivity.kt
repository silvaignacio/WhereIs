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

        val ciudad = intent.getStringExtra("com.example.whereis.ciudades.ciudad")

        val santiago = Ciudad("Santiago", 12, "Soleado")

        if(ciudad.equals("santiago")) {
            temp.text = santiago.nombre
            city.text = santiago.grados.toString()
            status.text = santiago.status
        }

    }
}