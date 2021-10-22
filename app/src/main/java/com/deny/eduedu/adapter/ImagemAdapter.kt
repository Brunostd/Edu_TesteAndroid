package com.deny.eduedu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.deny.eduedu.R
import com.deny.eduedu.model.Imagem

class ImagemAdapter(var listaImagem: MutableList<Imagem>):
    RecyclerView.Adapter<ImagemAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(imagem: Imagem){
            var escolherImagem: ImageView = itemView.findViewById(R.id.escolherImagem)
            escolherImagem = imagem.escolherImagem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imagem, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listaImagem[position])
    }

    override fun getItemCount(): Int = listaImagem.size

}