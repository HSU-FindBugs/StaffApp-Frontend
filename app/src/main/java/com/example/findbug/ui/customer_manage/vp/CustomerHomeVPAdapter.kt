package com.example.findbug.ui.customer_manage.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomerHomeVPAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CustomerFindFragment()
            1 -> CustomerAddCustomerFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }
    }

}