package com.mercadolibre.android.mlbusinesscomponents.components.common

import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.util.AttributeSet
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

class WrapContentDraweeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : SimpleDraweeView(context, attrs, defStyle) {

    private val listener = object : BaseControllerListener<ImageInfo?>() {
        override fun onIntermediateImageSet(id: String, imageInfo: ImageInfo?) {
            updateViewSize(imageInfo)
        }

        override fun onFinalImageSet(id: String, imageInfo: ImageInfo?, animatable: Animatable?) {
            updateViewSize(imageInfo)
        }
    }

    override fun setImageURI(uri: Uri, callerContext: Any?) {
        val controller = controllerBuilder
            .setControllerListener(listener)
            .setCallerContext(callerContext)
            .setUri(uri)
            .setOldController(controller)
            .build()
        setController(controller)
    }

    fun updateViewSize(imageInfo: ImageInfo?) {
        if (imageInfo != null) {
            aspectRatio = imageInfo.width.toFloat() / imageInfo.height
        }
    }
}
