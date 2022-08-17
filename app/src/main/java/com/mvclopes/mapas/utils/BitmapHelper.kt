package com.mvclopes.mapas.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object BitmapHelper {

    // Método responsável por converter vetor de imagem em bitmap descriptor
    fun vectorToBitmap(
        context: Context,
        @DrawableRes drawableId: Int,
        @ColorInt color: Int
    ): BitmapDescriptor {
        // Obtido vetor drawable a partir dos recursos e parâmetro fornecido (drawableId)
        val vectorDrawable = ResourcesCompat.getDrawable(context.resources, drawableId, null)
            ?: return BitmapDescriptorFactory.defaultMarker()

        // Criação de bitmap a partir do vetor de drawable
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // Criado canvas a partir do bitmap
        val canvas = Canvas(bitmap)

        // Configurado vetor drawable (largura, altura e cor)
        vectorDrawable.setBounds(0,0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}