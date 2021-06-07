package com.example.altp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.altp.R
import com.example.altp.data.DataMoney15Qs
import com.example.altp.databinding.ItemQsBinding

class AdapterMoney : RecyclerView.Adapter<AdapterMoney.HolderTwo> {
    private var inter: ITwo? = null


    constructor(inter: ITwo?) : super() {
        this.inter = inter

    }

    class HolderTwo(var binding: ItemQsBinding, inter: ITwo?) :
        RecyclerView.ViewHolder(binding.root) {
      init {
            binding.root.setOnClickListener {
                inter!!.getClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTwo {
        return HolderTwo(
            ItemQsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            inter
        )
    }

    override fun getItemCount(): Int = inter!!.getSize()

    override fun onBindViewHolder(holder: HolderTwo, position: Int) {
        holder.binding.data = inter!!.getData(position)
        anim(holder, position)
        anim2(holder, position)
    }

    interface ITwo {
        fun getSize(): Int
        fun getData(position: Int): DataMoney15Qs
        fun getClick(position: Int)
    }
    @SuppressLint("UseRequireInsteadOfGet")
    private fun anim2(holder: HolderTwo, position: Int) {
        var cr = 15
        var hand = android.os.Handler()
        hand.postDelayed(object : Runnable {
            override fun run() {
                cr--
                hand.postDelayed(Runnable {
                    if (position == cr && cr == 10) {
                        holder.binding.rl.setBackgroundResource(R.drawable.bg_paly_non_press)
                    }
                }, 0)
                hand.postDelayed(Runnable {
                    if (position == cr && cr == 5) {
                        holder.binding.rl.setBackgroundResource(R.drawable.bg_paly_non_press)
                    }
                }, 0)
                hand.postDelayed(Runnable {
                    if (position == cr && cr == 0) {
                        holder.binding.rl.setBackgroundResource(R.drawable.bg_paly_non_press)
                    }
                }, 0)
                hand.postDelayed(this, 100)
            }

        }, 4500)

    }

    private fun anim(holder: HolderTwo, position: Int) {
        var cr = 15
        var hand = android.os.Handler()
        hand.postDelayed(object : Runnable {
            override fun run() {
                cr--
                if (cr > -2) {
                    if (position == cr) {
                        holder.binding.rl.setBackgroundResource(R.drawable.bg_paly_non_press)
                    } else {
                        holder.binding.rl.setBackgroundResource(R.drawable.bg_hight_score_press)
                    }
                    hand.postDelayed(this, 300)
                }
            }
        }, 0)
    }
}