package com.shivek.maps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
        mMap.uiSettings.apply {
            isCompassEnabled = true
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
            isMyLocationButtonEnabled = true

        }

        mMap.setMaxZoomPreference(12f)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.addPolyline(
            PolylineOptions().add(
                sydney, LatLng(28.7041, 77.1025)
            ).width(23f).color(ContextCompat.getColor(baseContext, R.color.colorPrimary))
        )
    }

    override fun onStart() {
        loacationoptin()

        if (isfinelocationgranted())
        {
           if (islocationenabled())
           {
               setuplocationlistener()
           }

            else
           {
               showdialogbo()
           }
        }

        else
        {
            loacationoptin()

        }


        super.onStart()
    }

    fun isfinelocationgranted(): Boolean {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun loacationoptin() {
        this.requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
    }

  override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            123 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  if (islocationenabled())
                  {
                      setuplocationlistener()
                  }
                    else{
                      showdialogbo()
                  }
                }
                else   {
                    Toast.makeText(this, "PERMISSION NOT GRANTED", Toast.LENGTH_LONG).show()
                }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun islocationenabled() : Boolean
    {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||  lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    }

    fun showdialogbo()
    {
        AlertDialog.Builder(this)
            .setTitle("GPS NOT ENABLED")
            .setMessage("OPEN YOUR GPS")
            .setCancelable(false)
            .setPositiveButton("open gps") { dialogInterface: DialogInterface, i: Int ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                dialogInterface.dismiss()
            }
            .show()


    }

    @SuppressLint("MissingPermission")
    private fun setuplocationlistener() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = lm.getProviders(true)

        var l: Location? = null

        for (i in providers.indices.reversed()) {

            l = lm.getLastKnownLocation(providers[i])
            if (l != null) break
        }
        l?.let {
            if (::mMap.isInitialized) {
                val sydney = LatLng(it.latitude, it.longitude)
                mMap.addMarker(MarkerOptions().position(sydney).title("Marker in current area"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }
        }
    }
}
