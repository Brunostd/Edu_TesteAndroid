package com.deny.eduedu.ui.escolherImagem

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deny.eduedu.R
import com.deny.eduedu.adapter.ImagemAdapter
import com.deny.eduedu.databinding.EscolherImagemFragmentBinding
import com.deny.eduedu.helper.RecyclerItemClickListener
import com.deny.eduedu.model.Imagem
import com.deny.eduedu.ui.notifications.NotificationsViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class EscolherImagemFragment : Fragment() {

    private lateinit var escolherImagemViewModel: EscolherImagemViewModel
    private var _binding: EscolherImagemFragmentBinding? = null
    private var listaImagem: MutableList<Imagem> = ArrayList<Imagem>()
    lateinit var imagem: Imagem

    val storage = Firebase.storage

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as AppCompatActivity).supportActionBar!!.title = "Escolher avatar"
    }

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

        binding.recyclerViewImagem.adapter = ImagemAdapter(listaImagem)
        binding.recyclerViewImagem.layoutManager = GridLayoutManager(context, 2)

        var recyclerView: RecyclerView = binding.recyclerViewImagem

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(context, recyclerView, object :
                RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {

                    val result5 = listaImagem[position].escolherImagem
                    var bundle: Bundle = Bundle()
                    // Use the Kotlin extension in the fragment-ktx artifact
                    setFragmentResult("requestKey5", bundleOf("bundleKey5" to result5))

                    Navigation.findNavController(root).navigate(R.id.action_escolherImagemFragment_to_addAlunoFragment2)

                }

                override fun onLongItemClick(view: View, position: Int) {

                }
            })
        )
        return root
    }

    fun consultarImagems(){

        imagem = Imagem(R.drawable.avatar1)
        listaImagem.add(imagem)

        imagem = Imagem(R.drawable.avatar2)
        listaImagem.add(imagem)

        imagem = Imagem(R.drawable.avatar3)
        listaImagem.add(imagem)

        imagem = Imagem(R.drawable.avatar4)
        listaImagem.add(imagem)

        imagem = Imagem(R.drawable.avatar5)
        listaImagem.add(imagem)

        imagem = Imagem(R.drawable.avatar6)
        listaImagem.add(imagem)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}