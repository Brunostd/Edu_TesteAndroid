package com.deny.eduedu.ui.addAluno.ui.addalunofragment2

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.ActionBarContextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.deny.eduedu.R
import com.deny.eduedu.TelaDoisActivity
import com.deny.eduedu.databinding.AddAlunoFragment2FragmentBinding
import com.deny.eduedu.model.Aluno
import com.deny.eduedu.helper.Base64Custom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.app.AppCompatActivity




class AddAlunoFragment2 : Fragment() {

    private lateinit var viewModel: AddAlunoFragment2ViewModel
    lateinit var firestoreDB : FirebaseFirestore
    var auxRecebeAnoEscolar: String = ""

    private var _binding: AddAlunoFragment2FragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(AddAlunoFragment2ViewModel::class.java)

        (context as AppCompatActivity).supportActionBar!!.title = "Adicionar aluno"


        _binding = AddAlunoFragment2FragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        buttonAnoEscolar(root)

        firestoreDB = FirebaseFirestore.getInstance()

        var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
        var id: String = Base64Custom.codificarBase64(autenticacao.currentUser?.getEmail())
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

        binding.imageButtonCadastrar.setOnClickListener(View.OnClickListener {
            if (binding.checkBoxCadastrar.isChecked) {
                if (!binding.editTextCadastrarAluno.toString().isEmpty() && !auxRecebeAnoEscolar.isEmpty()) {
                    var note: Aluno = Aluno(
                        id = id,
                        nome = binding.editTextCadastrarAluno.text.toString(),
                        anoEscolar = auxRecebeAnoEscolar
                    )
                    firestoreDB.collection("cadastro").add(note.toMap())
                    Toast.makeText(root.context, "Aluno cadastrado com sucesso", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(root).navigate(R.id.action_addAlunoFragment2_to_navigation_dashboard)
                } else{
                    Toast.makeText(root.context, "Você não digitou um nome ou não escolheu nenhum ano escolar", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(root.context, "Aceite os termos para cadastrar", Toast.LENGTH_LONG).show()
            }
        })

        binding.imageViewAdicionarImagem.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_addAlunoFragment2_to_escolherImagemFragment)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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