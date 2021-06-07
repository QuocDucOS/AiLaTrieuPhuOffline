package com.example.altp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.altp.BaseFragment
import com.example.altp.MainActivity
import com.example.altp.MusicManager
import com.example.altp.adapter.AdapterMoney
import com.example.altp.data.DataMoney15Qs
import com.example.altp.databinding.FragmentTwoBinding

class FragmentTwo : BaseFragment(), AdapterMoney.ITwo {
    private var binding: FragmentTwoBinding? = null
    private var inter: AdapterMoney? = null
    private var mediaclass: MusicManager? = null
    private var arr = mutableListOf<DataMoney15Qs>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        mediaclass = MusicManager(this!!.context!!)
        getQs()
        mediaclass!!.startFragment2()
        binding!!.txtrd.setOnClickListener {
            (activity as MainActivity).openThree()
            mediaclass!!.stopfr2()

        }
        binding!!.rc.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rc.adapter = AdapterMoney(this)
        return binding!!.root
    }

    override fun getSize(): Int = arr.size

    override fun getData(position: Int): DataMoney15Qs = arr[position]

    override fun getClick(position: Int) {

    }

    private fun getQs() {
        arr.add(DataMoney15Qs("15  $150000"))
        arr.add(DataMoney15Qs("14  $85000"))
        arr.add(DataMoney15Qs("13  $60000"))
        arr.add(DataMoney15Qs("12  $40000"))
        arr.add(DataMoney15Qs("11  $30000"))
        arr.add(DataMoney15Qs("10  $22000"))
        arr.add(DataMoney15Qs("9  $14000"))
        arr.add(DataMoney15Qs("8  $10000"))
        arr.add(DataMoney15Qs("7  $6000"))
        arr.add(DataMoney15Qs("6  $3000"))
        arr.add(DataMoney15Qs("5  $2000"))
        arr.add(DataMoney15Qs("4  $1000"))
        arr.add(DataMoney15Qs("3  $600"))
        arr.add(DataMoney15Qs("2  $400"))
        arr.add(DataMoney15Qs("1  $200"))
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun BackFr() {
        mediaclass!!.stopfr2()

        super.BackFr()
    }

    override fun onDestroy() {
        Toast.makeText(context, "destroy2", Toast.LENGTH_SHORT).show()
        //  mediaclass = null
        super.onDestroy()
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Toast.makeText(context, "create2", Toast.LENGTH_SHORT).show()
    }
}