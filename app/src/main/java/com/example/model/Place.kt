package com.example.model

import android.location.Location

/**
 * Created by ahmad Mansour on 11,January,2020
 */
class Place {

    var placeID: Int? = null
    var placeName: String? = null
    var placeDescription: String? =null
    var placeImg: Int? = null
    var placeLocation: Location? = null
    var isVisitedPlace: Boolean = false

    constructor(id: Int, pn: String, pd: String, pi: Int) {
        this.placeID = id
        this.placeName = pn
        this.placeDescription = pd
        this.placeImg = pi
    }

    constructor(id: Int, pn: String, pd: String, pi: Int, lat: Double, long: Double) {
        this.placeID = id
        this.placeName = pn
        this.placeDescription = pd
        this.placeImg = pi
        this.placeLocation = Location(this.placeName)
        this.placeLocation!!.latitude = lat
        this.placeLocation!!.longitude = long
        this.isVisitedPlace = false
    }
}