package com.deny.eduedu

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.deny.eduedu.model.UsuarioLogin
import com.deny.eduedu.ui.dashboard.DashboardFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var usuario: UsuarioLogin = UsuarioLogin()
    lateinit var autenticacao: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val textEmail: EditText = findViewById(R.id.textInputEmail)
        val textSenha: EditText = findViewById(R.id.textInputSenha)
        val buttonLogin: ImageButton = findViewById(R.id.imageButtonLogin)
        val buttonCadastrar: TextView = findViewById(R.id.textViewCadastrar)
        val intent2 = Intent(this, CadastrarActivity::class.java)

        buttonLogin.setOnClickListener(View.OnClickListener {
            var recebeEmail: String = textEmail.text.toString()
            var recebeSenha: String = textSenha.text.toString()

            if (!recebeEmail.isEmpty()){
                if (!recebeSenha.isEmpty()){
                    usuario.emailId = recebeEmail
                    usuario.senha   = recebeSenha

                    var hash: String = intent.getStringExtra("chave").toString()
                    println("Nome: "+hash)
                    validarLogin()
                    //startActivity(intent)

                } else{
                    Toast.makeText(applicationContext, "Preencha a senha", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(applicationContext, "Preencha o email", Toast.LENGTH_LONG).show()
            }
        })

        buttonCadastrar.setOnClickListener(View.OnClickListener {
            startActivity(intent2)
        })
    }

    fun validarLogin(){
        autenticacao = FirebaseAuth.getInstance()

        autenticacao.signInWithEmailAndPassword(
            usuario.emailId,
            usuario.senha
        ).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(applicationContext, "Sucesso ao fazer login", Toast.LENGTH_LONG).show()
                abrirTelaDois()
                finish()
            } else{

                var excecao: String = ""
                try {
                   throw it.exception!!
                }
                catch (e: FirebaseAuthInvalidCredentialsException){
                    excecao = "Email ou senha não corespondem a um usuário cadastrado"
                }
                catch (e: FirebaseAuthInvalidUserException){
                    excecao = "Usuário não está cadastrado"
                }
                catch (e: Exception){
                    excecao = "Erro ao fazer login" + e.message
                    e.printStackTrace()
                }

                Toast.makeText(applicationContext, excecao, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun abrirTelaDois(){
        val intent = Intent(this, TelaDoisActivity::class.java)
        startActivity(intent)
    }

}