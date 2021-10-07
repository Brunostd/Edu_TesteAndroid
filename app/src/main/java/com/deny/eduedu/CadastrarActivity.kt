package com.deny.eduedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.deny.eduedu.helper.Base64Custom
import com.deny.eduedu.model.UsuarioLogin
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastrarActivity : AppCompatActivity() {

    lateinit var autenticacao: FirebaseAuth
    lateinit var firebase: DatabaseReference
    var usuario: UsuarioLogin = UsuarioLogin()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        var buttonCadastar: Button = findViewById(R.id.buttonCadastrarUsuario)
        var textRecebeEmail: EditText = findViewById(R.id.editTextRecebeEmail)
        var textRecebeSenha: EditText = findViewById(R.id.editTextRecebeSenha)

        fireBaseAutenticacao()

        buttonCadastar.setOnClickListener(View.OnClickListener {
            var textEmail: String = textRecebeEmail.text.toString()
            var textSenha: String = textRecebeSenha.text.toString()

            if (!textEmail.isEmpty()){
                if (!textSenha.isEmpty()){
                    usuario.emailId = textEmail
                    usuario.senha   = textSenha

                    autenticacao.createUserWithEmailAndPassword(usuario.emailId, usuario.senha)
                        .addOnCompleteListener(this, OnCompleteListener<AuthResult> {
                            if(it.isSuccessful){
                                var idUsuario: String = Base64Custom.codificarBase64(usuario.emailId)
                                usuario.idUsuario = idUsuario
                                usuario.salvar()
                                Toast.makeText(applicationContext, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show()
                                finish()
                            } else {

                                var excecao: String = ""

                                try {
                                    throw it.exception!!
                                } catch (e: FirebaseAuthWeakPasswordException){
                                    excecao = "Digite uma senha mais forte"
                                } catch (e: FirebaseAuthInvalidCredentialsException){
                                    excecao = "Por favor digite um e-mail válido"
                                } catch (e: FirebaseAuthUserCollisionException){
                                    excecao = "Esta conta já foi cadastrada"
                                } catch (e: Exception){
                                    excecao = "Erro ao cadastrar usuário" + e.message
                                    e.printStackTrace()
                                }
                                Toast.makeText(applicationContext, excecao, Toast.LENGTH_LONG).show()
                            }
                        })
                } else{
                    Toast.makeText(applicationContext, "Digite o campo senha", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Digite o campo email", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun fireBaseAutenticacao(){
            autenticacao = FirebaseAuth.getInstance()
    }

    fun fireBaseDataBase(){
        firebase = FirebaseDatabase.getInstance().reference
    }
}