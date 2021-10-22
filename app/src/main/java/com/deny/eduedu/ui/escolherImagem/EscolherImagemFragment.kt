package com.deny.eduedu.ui.escolherImagem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.deny.eduedu.R
import com.deny.eduedu.adapter.ImagemAdapter
import com.deny.eduedu.databinding.EscolherImagemFragmentBinding
import com.deny.eduedu.databinding.FragmentNotificationsBinding
import com.deny.eduedu.model.Imagem
import com.deny.eduedu.ui.notifications.NotificationsViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class EscolherImagemFragment : Fragment() {

    private lateinit var escolherImagemViewModel: EscolherImagemViewModel
    private var _binding: EscolherImagemFragmentBinding? = null
    private var listaImagem: MutableList<Imagem> = ArrayList<Imagem>()
    lateinit var imagem: Imagem
    var imagemAdapter: ImagemAdapter = ImagemAdapter(listaImagem)

    val storage = Firebase.storage

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        escolherImagemViewModel =
            ViewModelProvider(this).get(EscolherImagemViewModel::class.java)

        _binding = EscolherImagemFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        consultarImagems()


        return root
    }

    fun consultarImagems(){

        

        binding.recyclerViewImagem.adapter = ImagemAdapter(listaImagem)
        binding.recyclerViewImagem.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}