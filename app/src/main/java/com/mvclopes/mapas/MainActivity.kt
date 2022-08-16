package com.mvclopes.mapas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mvclopes.mapas.databinding.ActivityMainBinding
import com.mvclopes.mapas.model.Place

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val places = arrayListOf(
        Place(
            name = "FIAP Campus Vila Olimpia",
            latLng = LatLng(
                -23.5955843,
                -46.6851937
            ),
            address = "Rua Olimpíadas,186 - São Paulo - SP",
            rating = 4.8f
        ),
        Place(
            name = "FIAP Campus Paulista",
            latLng = LatLng(
                -23.5643721,
                -46.652857
            ),
            address = "Avenida Paulista, 1106 - São Paulo - SP",
            rating = 5.0f
        ),
        Place(
            name = "FIAP Campus Vila Mariana",
            latLng = LatLng(
                -23.5746685,
                -46.6232043
            ),
            address = "Avenida Paulista, 1106 - São Paulo - SP",
            rating = 4.8f
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
            )
        }
    }
}