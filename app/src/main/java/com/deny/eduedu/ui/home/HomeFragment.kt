package com.deny.eduedu.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deny.eduedu.R
import com.deny.eduedu.adapter.AlunosAdapter2
import com.deny.eduedu.databinding.FragmentHomeBinding
import com.deny.eduedu.model.Aluno
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Adapter as Adapter1

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonComecarAlunos.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_navigation_dashboard)
        })

        binding.buttonComecarAjuda.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_navigation_notifications)
        })

        binding.buttonComecarConfiguracao.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_navigation_config)
        })

        binding.buttonSair.setOnClickListener(View.OnClickListener {
            var autenticacao: FirebaseAuth = FirebaseAuth.getInstance()
            autenticacao.signOut()
            Toast.makeText(root.context,"Você foi deslogado com sucesso", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(root).navigate(R.id.action_navigation_home_to_mainActivity)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}