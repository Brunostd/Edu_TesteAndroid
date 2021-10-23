package com.deny.eduedu.ui.addAluno.ui.addalunofragment2

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.deny.eduedu.R
import com.deny.eduedu.databinding.AddAlunoFragment2FragmentBinding
import com.deny.eduedu.model.Aluno
import com.deny.eduedu.helper.Base64Custom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.google.common.primitives.UnsignedBytes.toInt


class AddAlunoFragment2 : Fragment() {

    private lateinit var viewModel: AddAlunoFragment2ViewModel
    var firestoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
    var auxRecebeAnoEscolar: String = ""
    var recebeImagem: Int = R.drawable.avatar6

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


        var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
        var id: String = Base64Custom.codificarBase64(autenticacao.currentUser?.getEmail())
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

        recuperarEdicao()

        binding.imageButtonCadastrar.setOnClickListener(View.OnClickListener {
            if (binding.checkBoxCadastrar.isChecked) {
                if (!binding.editTextCadastrarAluno.toString().isEmpty() && !auxRecebeAnoEscolar.isEmpty()) {
                    var note: Aluno = Aluno(
                        id = id,
                        nome = binding.editTextCadastrarAluno.text.toString(),
                        anoEscolar = auxRecebeAnoEscolar,
                        avatar = recebeImagem
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

        binding.imageButtonMudarAvatar.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_addAlunoFragment2_to_escolherImagemFragment)
        })

        binding.imageViewAdicionarImagem.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_addAlunoFragment2_to_escolherImagemFragment)
        })

        return root
    }

    fun recuperarEdicao(){
        setFragmentResultListener("requestKey5"){requestKey, bundle ->
            val result5 = bundle.getInt("bundleKey5")
            recebeImagem = result5
            Glide.with(this).load(recebeImagem).into(binding.imageViewAdicionarImagem)
        }
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