package com.example.altp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.altp.data.DataPerson
import com.example.altp.databinding.ItemPersonBinding

class AdapterPerson : RecyclerView.Adapter<AdapterPerson.HolderPerson> {
    private var inter: IPerson? = null

    constructor(inter: IPerson?) : super() {
        this.inter = inter
    }

    class HolderPerson(var binding: ItemPersonBinding,inter: IPerson?) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnLongClickListener {
                inter!!.clickData(adapterPosition)
               return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPerson {
        return HolderPerson(ItemPersonBinding.inflate(LayoutInflater.from(parent.context),parent,false),inter)
    }

    override fun getItemCount(): Int = inter!!.getSize()

    override fun onBindViewHolder(holder: HolderPerson, position: Int) {
        holder.binding.datapr = inter!!.getData(position)
    }

    interface IPerson {
        fun getSize(): Int
        fun getData(position: Int): DataPerson
        fun clickData(position: Int)
    }
}