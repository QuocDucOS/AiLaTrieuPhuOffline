package com.example.altp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.altp.data.DataQuestion
import com.example.altp.fragment.FragmentActivity
import com.example.altp.fragment.FragmentPerson
import com.example.altp.fragment.FragmentThree
import com.example.altp.fragment.FragmentTwo

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openAct()
    }

    fun openAct() {

        supportFragmentManager.beginTransaction()
            .add(R.id.fr,  FragmentActivity(), FragmentActivity::javaClass.name)
            .commit()
    }

    fun openTwo() {

        supportFragmentManager.beginTransaction()
            .add(R.id.fr,FragmentTwo(),FragmentTwo::javaClass.name)

            .hide(visible()!!)
            .addToBackStack(null)
            .commit()
    }

    fun openThree() {

        supportFragmentManager.beginTransaction()
            .add(R.id.fr, FragmentThree(),FragmentThree::javaClass.name)
            .hide(visible()!!)
            .addToBackStack(FragmentActivity::javaClass.name)
            .commit()
    }

    fun openPerson() {

        supportFragmentManager.beginTransaction()
            .add(R.id.fr, FragmentPerson(), FragmentPerson::javaClass.name)
            .hide(visible()!!)
            .addToBackStack(null)
            .commit()
    }

    fun visible(): Fragment? {
        for (fr in supportFragmentManager.fragments) {
            if (fr.isVisible && fr != null) {
                return fr
            }
        }
        return null
    }


    override fun onBackPressed() {
        var fr = visible()
        if (fr != null) {
            (fr as BaseFragment).BackFr()
        }
        else {
            Back()
        }
    }

}