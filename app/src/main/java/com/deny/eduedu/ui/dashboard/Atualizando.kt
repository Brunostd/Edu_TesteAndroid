package com.deny.eduedu.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.deny.eduedu.R
import com.deny.eduedu.databinding.FragmentAtualizandoBinding
import com.deny.eduedu.databinding.FragmentHomeBinding
import com.deny.eduedu.ui.home.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Atualizando.newInstance] factory method to
 * create an instance of this fragment.
 */
class Atualizando : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentAtualizandoBinding? = null

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

        _binding = FragmentAtualizandoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}