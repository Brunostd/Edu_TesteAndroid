package com.deny.eduedu.ui.config

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deny.eduedu.R

class ConfigFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigFragment()
    }

    private lateinit var viewModel: ConfigViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.config_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigViewModel::class.java)
        // TODO: Use the ViewModel
    }

}