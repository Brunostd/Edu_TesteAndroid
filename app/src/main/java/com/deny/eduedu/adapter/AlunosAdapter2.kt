package com.deny.eduedu.adapter

import android.content.Intent
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.deny.eduedu.MainActivity
import com.deny.eduedu.R
import com.deny.eduedu.TelaDoisActivity
import com.deny.eduedu.model.Aluno
import com.deny.eduedu.ui.editarAluno.EditarAlunoFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.coroutineContext

class AlunosAdapter2(var listaAluno: MutableList<Aluno>) :
    RecyclerView.Adapter<AlunosAdapter2.MyViewHolder>() {

    lateinit var firestoreDB : FirebaseFirestore

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(aluno: Aluno) {
            var nome: TextView = itemView.findViewById(R.id.textNomeAluno)
            var id: String = aluno.id
            var imagem: ImageView = itemView.findViewById(R.id.imageAluno)
            var anoEscolar: TextView = itemView.findViewById(R.id.textAnoAluno)

            nome.setText(aluno.nome.toString())
            anoEscolar.setText(aluno.anoEscolar.toString())
            imagem.setImageResource(aluno.avatar)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.aluno, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaAluno[position])
    }

    override fun getItemCount(): Int = listaAluno.size


}