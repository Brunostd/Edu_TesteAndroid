package com.deny.eduedu.model

import android.graphics.drawable.Drawable
import android.widget.ImageView

class Imagem(
    var escolherImagem: ImageView
) {
    fun toMap() : HashMap<String, Any> {

        var resultImagem: HashMap<String, Any> = HashMap<String, Any>()
        resultImagem.put("imagem", this.escolherImagem)

        return resultImagem
    }
}