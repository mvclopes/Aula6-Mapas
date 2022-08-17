package com.mvclopes.mapas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.mvclopes.mapas.databinding.ActivityMainBinding
import com.mvclopes.mapas.model.Place
import com.mvclopes.mapas.utils.BitmapHelper

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // Lista de lugares, especificando nome e sua posição
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

        // Obtendo fragmento de mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        // Callback utilizado quando o mapa já está pronto para uso
        mapFragment.getMapAsync { googleMap ->
            addMarkers(googleMap)

            // Callback utilizando quando o mapa já foi renderizado
            googleMap.setOnMapLoadedCallback {
                // Objeto contendo latitude e longitude, representando uma região delimitada pelas localizações
                val bounds = LatLngBounds.builder()

                places.forEach {
                    // Adicionado as localizações  ao objeto Bounds
                    bounds.include(it.latLng)
                }

                // Movimenta a câmera de acordo com a região especificada (Bounds)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 250))
            }
        }
    }

    // Método responsável para adicionar marcadores no mapa
    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            googleMap.addMarker(
                // Configurando os marcadores, como por exemplo: título, ícone, posição
                MarkerOptions()
                    .title(place.name)
                    .snippet(place.address)
                    .position(place.latLng)
                    .icon(
                        // Chamada ao método responsável por converter vetor de imagem em Bitmap
                        BitmapHelper.vectorToBitmap(
                            context = this,
                            drawableId = R.drawable.ic_android_foreground,
                            color = ContextCompat.getColor(
                                this,
                                R.color.purple_700
                            )
                        )
                    )
            )
        }
    }
}