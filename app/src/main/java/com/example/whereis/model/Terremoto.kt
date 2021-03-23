package com.example.whereis.model

class Terremoto(title: String, place: String, mag: Long) {
    var title: String = ""
    var place: String = ""
    var mag: Long = 0

    init {
        this.title = title
        this.place = place
        this.mag = mag
    }
}