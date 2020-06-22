package com.example.startapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showTips(view: View) {
        var myIntent = Intent(this.baseContext, TipsActivity::class.java)
        startActivity(myIntent)

    }

    fun showPlaces(view: View) {
        var myIntent = Intent(this.baseContext, PlacesActivity::class.java)
        startActivity(myIntent)
    }

    fun showRiderMap(view: View) {
        var intent = Intent(this.baseContext, RaiderActivity::class.java)
        startActivity(intent)
    }
}
