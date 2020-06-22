package com.example.startapp

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.example.model.Place

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.roundToInt

class RaiderActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var accessLocationRequestCode = 200
    private var listOfPlaces: ArrayList<Place> = ArrayList()
    private var currentMarker: Marker ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.rider_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
        loadLocations()
    }

    private fun loadLocations() {
        var place1 = Place(1,"Paris","Paris is the Capital of France asdasd asd asd dqwdwq da sd " +
                "asdqwd a  qwnjoqwnflaskfnlaskf  sd awdwf askfmalkfma;mf;asf;lasf;"
                , R.drawable.place_1, 48.8566, 2.3522)
        var place2 = Place(2,"Spain","Spain has a mediterranean country", R.drawable.place_2, 40.4637, 3.7492)
        var place3 = Place(3,"Turkey","Turkey is a middle eastern country", R.drawable.place_3, 38.9637, 35.2433)
        var place4 = Place(4,"Italy","Italy is in Europe", R.drawable.place_4, 41.8719, 12.5674)
        var place5 = Place(5,"Rio De Janeiro","Rio De Janeiro is in south America", R.drawable
                .place_5, -22.9068, -43.1729)
        var place6 = Place(6,"Jerusalem","Jerusalem is the capital of State of Palestine", R
                .drawable
                .place_6,31.7683 ,35.2137)

        listOfPlaces?.add(place1)
        listOfPlaces?.add(place2)
        listOfPlaces?.add(place3)
        listOfPlaces?.add(place4)
        listOfPlaces?.add(place5)
        listOfPlaces?.add(place6)
    }

    private fun checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                            .PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        accessLocationRequestCode)
                return
            }
        }
        // continue because you have permission
       getCurrentLocation()
    }

    private fun getCurrentLocation() {
        Toast.makeText(this.baseContext, "user enabled the location permession", Toast
                .LENGTH_LONG).show()
        var locationOnMap = mapLocationListener(this.baseContext)
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3,
                3f, locationOnMap)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

/*        //25.2048° N, 55.2708° E
        // Add a marker in Dubai and move the camera
        val dubai = LatLng(25.2048, 55.2708)
        mMap.addMarker(MarkerOptions().position(dubai).title("new Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dubai))*/

        for (i in 0 until listOfPlaces.size) {
            //mMap.clear()
            var place = listOfPlaces[i]
            val location = place.placeLocation?.let { LatLng(it.latitude, it.longitude) }
            mMap.addMarker(location?.let {
                MarkerOptions()
                        .position(it)
                        .title(place.placeName)
                        .snippet(place.placeDescription)
                        .icon(BitmapDescriptorFactory.fromBitmap(place.placeImg?.let { it1 ->
                            scaleImage(resources, it1,
                                    50)
                        }))
            })

            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        }

    }

    var location: Location? = null

    inner class mapLocationListener: LocationListener {
        var context: Context?= null

        constructor(context: Context) {
            location = Location("Start")
            location!!.latitude = 0.0
            location!!.longitude = 0.0
            this.context = context
        }

        override fun onLocationChanged(location: Location?) {
            Toast.makeText(context, "user location has been changed" + location!!.latitude + ", "
                    + location!!.longitude, Toast
                    .LENGTH_LONG).show()
            //mMap.clear()

            val location = LatLng(location!!.latitude, location!!.longitude)

            if (currentMarker != null)
                currentMarker?.remove()

           currentMarker =  mMap.addMarker(MarkerOptions()
                    .position(location)
                    .title("i am here")
                    .snippet("this is my current location")
                    .icon(BitmapDescriptorFactory.fromBitmap(scaleImage(resources, R.drawable
                            .face, 50))))

            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

    }

    fun scaleImage(res: Resources, id: Int, lessSideSize: Int) : Bitmap {
        var b: Bitmap? = null
        var o = BitmapFactory.Options()
        BitmapFactory.decodeResource(res, id, o)
        o.inJustDecodeBounds = true

        var scale = 1
        var sc = 0.0f

        if (o.outHeight > o.outWidth) {
            sc = (o.outHeight / lessSideSize).toFloat()
            scale = sc.roundToInt()
        } else {
            sc = (o.outWidth / lessSideSize).toFloat()
            scale = sc.roundToInt()
        }

        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        b = BitmapFactory.decodeResource(res, id, o2)
        return b
    }
}
