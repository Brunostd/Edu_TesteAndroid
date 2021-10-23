package com.deny.eduedu.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Aluno(
    var id: String = "",
    var nome: String = "",
    var anoEscolar: String = "",
    var avatar: Int = 0
) {

    fun toMap() : HashMap<String, Any> {

        var result : HashMap<String, Any> = HashMap<String, Any>()
        result.put("id", this.id)
        result.put("nome", this.nome)
        result.put("anoEscolar", this.anoEscolar)
        result.put("avatar", this.avatar)

        return result
    }
}