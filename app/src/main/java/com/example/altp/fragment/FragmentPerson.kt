package com.example.altp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.altp.BaseFragment
import com.example.altp.GetSQLData
import com.example.altp.adapter.AdapterPerson
import com.example.altp.data.DataPerson
import com.example.altp.databinding.PersonBinding

class FragmentPerson : BaseFragment(), AdapterPerson.IPerson {
    private var binding: PersonBinding? = null
    private var arr_Person = mutableListOf<DataPerson>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = PersonBinding.inflate(inflater, container, false)
        getInfo()
        binding!!.rcp.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rcp.adapter = AdapterPerson(this)
        return binding!!.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    fun getInfo() {
        var arr = GetSQLData(this!!.context!!).getAll()
        arr_Person.addAll(arr)
    }

    override fun getSize(): Int = arr_Person.size

    override fun getData(position: Int): DataPerson = arr_Person[position]

    @SuppressLint("UseRequireInsteadOfGet")
    override fun clickData(position: Int) {
        try {
            GetSQLData(this!!.context!!).deleteUser(arr_Person[position])
            arr_Person.removeAt(position)
            binding!!.rcp.adapter!!.notifyDataSetChanged()

        } catch (e: Exception) {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show()
        }

    }


}