package com.deny.eduedu

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.deny.eduedu.databinding.ActivityTelaDoisBinding
import com.deny.eduedu.model.Aluno
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.firestore.FirebaseFirestore

class TelaDoisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTelaDoisBinding
    lateinit var firestoreDB : FirebaseFirestore
    private var listAlunos: MutableList<Aluno> = ArrayList<Aluno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaDoisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_tela_dois)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_config
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}