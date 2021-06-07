package com.example.altp

import androidx.fragment.app.Fragment

open class BaseFragment:Fragment() {
   open fun BackFr(){
       if(activity!=null){
           (activity as BaseActivity).Back()
       }

    }
}