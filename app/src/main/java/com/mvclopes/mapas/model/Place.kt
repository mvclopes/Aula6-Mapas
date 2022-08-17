package com.mvclopes.mapas.model

import com.google.android.gms.maps.model.LatLng

/*
    Classe modelo que representa um lugar e suas características,
    latitude e longitude (posição), endereço, e avaliação (nota)
 */
data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
)
