package com.example.altp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.altp.BaseFragment
import com.example.altp.MainActivity
import com.example.altp.MusicManager
import com.example.altp.R
import com.example.altp.databinding.FragmentActBinding

class FragmentActivity : BaseFragment(), View.OnClickListener {
    private var binding: FragmentActBinding? = null
    var mediaclass: MusicManager? = null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentActBinding.inflate(inflater, container, false)
        mediaclass = MusicManager(this!!.context!!)
        binding!!.txtStart.setOnClickListener(this)
        binding!!.txthigher.setOnClickListener(this)
        mediaclass!!.startMusicBackground()

        return binding!!.root
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.txtStart -> {
                (activity as MainActivity).openTwo()
                mediaclass!!.stopfr1()
            }
            R.id.txthigher -> {
                (activity as MainActivity).openPerson()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(context, "create", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)

    }


}