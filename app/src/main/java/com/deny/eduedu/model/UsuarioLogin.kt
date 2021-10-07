package com.deny.eduedu.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UsuarioLogin(
    var emailId: String = "",
    var senha: String = "",
    var idUsuario: String = ""
) {
    fun salvar(){
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        databaseReference.child("inscritos")
                            .setValue(this)
    }
}