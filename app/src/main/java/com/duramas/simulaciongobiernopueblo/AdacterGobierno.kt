package com.duramas.simulaciongobiernopueblo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duramas.simulaciongobiernopueblo.databinding.RowGobiernoBinding


class AdacterGobierno(): RecyclerView.Adapter<AdacterGobierno.RowGobiernoViewHolder>()  {

    private var gobiernoList = emptyList<Gobierno>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowGobiernoViewHolder {
        val binding = RowGobiernoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return RowGobiernoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RowGobiernoViewHolder, position: Int) {
        holder.bind(gobiernoList[position])
    }

    override fun getItemCount(): Int {
        return gobiernoList.size
    }

    fun submitList(list :List<Gobierno>){
        gobiernoList = list
        notifyDataSetChanged()
    }

    inner class RowGobiernoViewHolder(private val binding: RowGobiernoBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item:Gobierno){
            binding.textViewNombreGobirno.text = item.nombre
            binding.textViewIdeologiaGobierno.text = item.ideologia
            binding.textViewTipoGobierno.text = item.tipoGobierno
        }
    }
}
