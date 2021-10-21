package com.deny.eduedu.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deny.eduedu.R
import com.deny.eduedu.TelaDoisActivity
import com.deny.eduedu.adapter.AlunosAdapter2
import com.deny.eduedu.databinding.FragmentDashboardBinding
import com.deny.eduedu.helper.Base64Custom
import com.deny.eduedu.helper.RecyclerItemClickListener
import com.deny.eduedu.helper.RecyclerItemClickListener.OnItemClickListener
import com.deny.eduedu.model.Aluno
import com.deny.eduedu.ui.editarAluno.EditarAlunoFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import android.os.Build
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.runInterruptible


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    var firestoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
    private var listAlunos: MutableList<Aluno> = ArrayList<Aluno>()
    var aluno: Aluno = Aluno()
    var alunosAdapter2: AlunosAdapter2 = AlunosAdapter2(listAlunos)


    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        coroutine()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)*/

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var recyclerView: RecyclerView = binding.recyclerAluno

        recuperarEdicao()

        var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
        var id: String = Base64Custom.codificarBase64(autenticacao.currentUser?.getEmail())
        aluno.id = id


        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(context, recyclerView, object : OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {

                    val result3 = listAlunos[position].nome
                    val result4 = listAlunos[position].anoEscolar
                    var bundle2: Bundle = Bundle()
                    // Use the Kotlin extension in the fragment-ktx artifact
                    setFragmentResult("requestKey3", bundleOf("bundleKey3" to result3))
                    setFragmentResult("requestKey4", bundleOf("bundleKey4" to result4))

                    listAlunos.set(position, aluno)
                    alunosAdapter2.notifyItemChanged(position)

                    Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_editarAlunoFragment)
                }

                override fun onLongItemClick(view: View, position: Int) {

                }
            })
        )
        binding.fab.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_addAlunoFragment24)
        })


        return root
    }

    fun coroutine() = runBlocking {
        launch {
            consultar()
        }
    }

    fun consultar(){

        var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
        var id: String = Base64Custom.codificarBase64(autenticacao.currentUser?.getEmail())
        var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

        firestoreDB.collection("cadastro").get().addOnCompleteListener(OnCompleteListener {
            for (dataObject in it.getResult()!!.documents){
                var note = dataObject.toObject(Aluno::class.java)

                if (note!!.id.equals(id)) {
                    note!!.id = dataObject.id

                    var p: Aluno = Aluno(nome = note!!.nome, anoEscolar = note!!.anoEscolar)
                    this.listAlunos.add(p)

                    binding.recyclerAluno.adapter = AlunosAdapter2(listAlunos)
                    binding.recyclerAluno.layoutManager = GridLayoutManager(context, 2)
                }
            }
        })
    }

    fun recuperarEdicao(){
        setFragmentResultListener("requestKey"){requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            aluno.nome = result.toString()
        }
        setFragmentResultListener("requestKey2"){requestKey, bundle ->
            val result2 = bundle.getString("bundleKey2")
            aluno.anoEscolar = result2.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
