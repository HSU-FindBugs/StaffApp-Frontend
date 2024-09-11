package com.example.findbug.ui.customer_manage.pest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding

class PestLogFragment : BaseFragment<FragmentPestLogBinding>(R.layout.fragment_pest_log) {

    override fun setLayout() {
        Log.d("로그","로그")
    }

    private fun initSettings() {

    }

}