package com.example.altp.data

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingData {
    @JvmStatic
    @BindingAdapter("setTextt")
    fun setTextt(txt:TextView,name:String?){
        txt.setText(name)
    }
    @JvmStatic
    @BindingAdapter("setText2")
    fun setText2(tv: TextView, value: String?) {
        tv.setText(value)
    }
}