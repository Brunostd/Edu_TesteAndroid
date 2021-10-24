package com.deny.eduedu.ui.editarImagem

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deny.eduedu.R
import com.deny.eduedu.adapter.ImagemAdapter
import com.deny.eduedu.databinding.EditarImagemFragmentBinding
import com.deny.eduedu.databinding.EscolherImagemFragmentBinding
import com.deny.eduedu.helper.RecyclerItemClickListener
import com.deny.eduedu.model.Imagem
import com.deny.eduedu.ui.editarAluno.EditarAlunoFragment
import com.deny.eduedu.ui.escolherImagem.EscolherImagemViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class EditarImagemFragment : Fragment() {

    private lateinit var editarImagemViewModel: EditarImagemViewModel
    private var _binding: EditarImagemFragmentBinding? = null
    private var listaImagem: MutableList<Imagem> = ArrayList<Imagem>()
    lateinit var imagem: Imagem
    var enviarImagem: Int = 0

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
        editarImagemViewModel =
            ViewModelProvider(this).get(EditarImagemViewModel::class.java)

        _binding = EditarImagemFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        consultarImagems()

        binding.recyclerViewEditarImagem.adapter = ImagemAdapter(listaImagem)
        binding.recyclerViewEditarImagem.layoutManager = GridLayoutManager(context, 2)

        var recyclerView: RecyclerView = binding.recyclerViewEditarImagem


        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(context, recyclerView, object :
                RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {

                    val result6 = listaImagem[position].escolherImagem
                    enviarImagem = result6
                    var bundle2: Bundle = Bundle()
                    // Use the Kotlin extension in the fragment-ktx artifact
                    setFragmentResult("requestKey6", bundleOf("bundleKey6" to result6))

                    val action = EditarImagemFragmentDirections.actionEditarImagemFragmentToEditarAlunoFragment(enviarImagem)
                    findNavController().navigate(action)

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