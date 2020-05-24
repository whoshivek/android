package com.shivek.map2

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

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
            isZoomGesturesEnabled
            isZoomControlsEnabled
            isCompassEnabled
            isMyLocationButtonEnabled
        }
        mMap.setMaxZoomPreference(22f)

        // Add a marker in Sydney and move the camera
      //  val sydney = LatLng(-34.0, 151.0)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onStart() {
        askpermission()
        super.onStart()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
     if (requestCode ==123)
     {
         if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
         {
             if (checkgpsstatus()==true)
             {
                     //  setuplistener()
                 fusedlocation()
             }

             else{
                 makedialogbox()
             }

         }
         else
         {
             Toast.makeText(this,"permision not granted , kindly go to setings and allow it" , Toast.LENGTH_LONG).show()
         }
     }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun fusedlocation() {
        val fusedlocationclient = LocationServices.getFusedLocationProviderClient(this)
        val locationRequest = LocationRequest()
                .setSmallestDisplacement(1f)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(2000)
                .setFastestInterval(2000)

        fusedlocationclient.requestLocationUpdates(locationRequest,
                object : LocationCallback(){
                    override fun onLocationResult(p0: LocationResult?) {
                        super.onLocationResult(p0)
                        if (p0 != null) {
                            for (i in p0.locations)
                            {
                                val sydney = LatLng( i.latitude,i.longitude )

                                if (::mMap.isInitialized) {

                                    mMap.addMarker(MarkerOptions().position(sydney).title("Marker in current area"))
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                                }
                            }

                        }

                    }
                },
                Looper.myLooper())
    }

  /*  @SuppressLint("MissingPermission")
    private fun setuplistener() {

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
    }*/

    private fun makedialogbox() {
        AlertDialog.Builder(this)
                .setTitle("GPS NOT ENABLE")
                .setMessage("KINDLY ENABLE GPS")
                .setCancelable(false)
                .setPositiveButton("gps setting"){ dialogInterface: DialogInterface, i: Int ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    dialogInterface.dismiss()
                }
                .show()
    }

    private fun askpermission() {
          this.requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) , 123)

    }

    fun checkgpsstatus() : Boolean{
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }




}


