package com.deny.eduedu.configAuth

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class ConfiguracaoFireBase(p0: FirebaseApp) {
    lateinit var autenticacao: FirebaseAuth
    lateinit var firebase: DatabaseReference

}