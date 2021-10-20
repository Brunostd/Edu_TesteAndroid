package com.deny.eduedu.ui.editarAluno

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.deny.eduedu.R
import com.deny.eduedu.databinding.EditarAlunoFragmentBinding
import com.deny.eduedu.helper.Base64Custom
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class EditarAlunoFragment : Fragment() {

    private lateinit var editarAlunoViewModel: EditarAlunoViewModel
    private var _binding: EditarAlunoFragmentBinding? = null

    lateinit var firestoreDB : FirebaseFirestore
    var auxRecebeAnoEscolar: String = ""

    var auxNome: String = ""
    var auxAnoEscolar: String = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editarAlunoViewModel =
            ViewModelProvider(this).get(EditarAlunoViewModel::class.java)

        _binding = EditarAlunoFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (context as AppCompatActivity).supportActionBar!!.title = "Editar aluno"

        buttonAnoEscolar(root)

        firestoreDB = FirebaseFirestore.getInstance()

        var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
        var id: String = Base64Custom.codificarBase64(autenticacao.currentUser?.getEmail())
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

        recuperarEnvio()

        binding.imageButtonEditar.setOnClickListener(View.OnClickListener {
            if (binding.checkBoxEditar.isChecked) {
                if (!binding.editTextEditarAluno.toString().isEmpty() && !auxRecebeAnoEscolar.isEmpty()) {
                    val result = binding.editTextEditarAluno.text.toString()
                    val result2 = auxRecebeAnoEscolar
                    var bundle: Bundle = Bundle()
                    // Use the Kotlin extension in the fragment-ktx artifact
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    setFragmentResult("requestKey2", bundleOf("bundleKey2" to result2))


                    //Alterar o nome do aluno
                    var userDetail: MutableMap<String, Any> = HashMap()
                    userDetail.put("nome", binding.editTextEditarAluno.text.toString())

                    firestoreDB.collection("cadastro")
                        .whereEqualTo("nome", auxNome).get()
                        .addOnCompleteListener(OnCompleteListener {
                            if (it.isSuccessful && !it.getResult()?.isEmpty!!) {

                                println("NOME: " + auxNome)
                                var documentSnapShot: DocumentChange? =
                                    it.getResult()!!.getDocumentChanges().get(0)
                                var documentID: String = documentSnapShot!!.document.id
                                firestoreDB.collection("cadastro")
                                    .document(documentID)
                                    .update(userDetail)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            root.context,
                                            "Nome atualizado com sucesso",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    .addOnFailureListener(OnFailureListener {
                                        Toast.makeText(
                                            root.context,
                                            "Falha ao atualizar o nome",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    })
                            } else {
                                Toast.makeText(root.context, "Erro nome", Toast.LENGTH_LONG).show()
                            }
                        })

                    //Alterar o ano escolar do aluno
                    var userDetail2: MutableMap<String, Any> = HashMap()
                    userDetail2.put("anoEscolar", auxRecebeAnoEscolar)

                    firestoreDB.collection("cadastro")
                        .whereEqualTo("nome", auxNome).get()
                        .addOnCompleteListener(OnCompleteListener {
                            if (it.isSuccessful && !it.getResult()?.isEmpty!!) {

                                println("NOME: " + auxAnoEscolar)
                                var documentSnapShot: DocumentChange? =
                                    it.getResult()!!.getDocumentChanges().get(0)
                                var documentID: String = documentSnapShot!!.document.id
                                firestoreDB.collection("cadastro")
                                    .document(documentID)
                                    .update(userDetail2)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            root.context,
                                            "Ano escolar atualizado com sucesso",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    .addOnFailureListener(OnFailureListener {
                                        Toast.makeText(
                                            root.context,
                                            "Falha ao atualizar o ano escolar",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    })
                            } else {
                                Toast.makeText(root.context, "Erro ano escolar", Toast.LENGTH_LONG)
                                    .show()
                            }
                        })

                    //Aqui se tudo deu certo o coroutine vai fazer um navigation para a tela alunos
                    //Com a mesma tela atualizada
                    coroutineVoltarTelaAlunos(root)
                } else {
                    Toast.makeText(root.context, "Você não digitou um nome ou não escolheu nenhum ano escolar", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(root.context, "Aceite os termos para atualizar", Toast.LENGTH_LONG).show()
            }
        })

        return root
    }

    fun coroutineVoltarTelaAlunos(view: View) = runBlocking {
        launch {
            delay(3000L)
            Navigation.findNavController(view)
                .navigate(R.id.action_editarAlunoFragment_to_navigation_dashboard)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun recuperarEnvio(){
        setFragmentResultListener("requestKey3"){requestKey, bundle ->
            val result3 = bundle.getString("bundleKey3")
             auxNome = result3.toString()
        }
        setFragmentResultListener("requestKey4"){requestKey, bundle ->
            val result4 = bundle.getString("bundleKey4")
            auxAnoEscolar = result4.toString()
        }
    }

    fun buttonAnoEscolar(
        root: View = binding.root
    ){
        binding.buttonPre.setOnClickListener(View.OnClickListener {
            auxRecebeAnoEscolar = "Pré-Escolar"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(root.context, "Você escolheu o ano "+ auxRecebeAnoEscolar, duration).show()
        })

        binding.buttonPrimeiroAno.setOnClickListener(View.OnClickListener {
            auxRecebeAnoEscolar = "1º ano do fundamental"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(root.context, "Você escolheu o "+ auxRecebeAnoEscolar, duration).show()
        })

        binding.buttonSegundoAno.setOnClickListener(View.OnClickListener {
            auxRecebeAnoEscolar = "2º ano do fundamental"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(root.context, "Você escolheu o "+ auxRecebeAnoEscolar, duration).show()
        })

        binding.buttonTerceiroAno.setOnClickListener(View.OnClickListener {
            auxRecebeAnoEscolar = "3º ano do fundamental"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(root.context, "Você escolheu o "+ auxRecebeAnoEscolar, duration).show()
        })
    }
}