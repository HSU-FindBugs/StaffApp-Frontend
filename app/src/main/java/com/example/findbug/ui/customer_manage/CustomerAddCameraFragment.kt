package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerAddCameraBinding

class CustomerAddCameraFragment : BaseFragment<FragmentCustomerAddCameraBinding>(R.layout.fragment_customer_add_camera) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerAddCameraToolbar.toolbarPreviousIb)
        activateBtn()
    }

    private fun activateBtn() {

        val saveBtn = binding.fragmentCustomerConfirmSaveBtn
        val buttonBackground = R.drawable.shape_rounded_rect_50dp
        val enableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val disableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.Blue500)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val cameraNameEt = binding.fragmentCustomerAddCameraNameEt.toString()
                val cameraNumberEt = binding.fragmentCustomerAddCameraNumberEt.toString()

                if (cameraNameEt.isNotEmpty() && cameraNumberEt.isNotEmpty()) {
                    saveBtn.setBackgroundResource(buttonBackground)
                    saveBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue700))
                    saveBtn.setTextColor(enableButtonTextColor)
                    saveBtn.isEnabled = true
                } else {
                    saveBtn.setBackgroundResource(buttonBackground)
                    saveBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue300))
                    saveBtn.setTextColor(disableButtonTextColor)
                    saveBtn.isEnabled = false
                }
            }
        }
        binding.fragmentCustomerAddCameraNameEt.addTextChangedListener(textWatcher)
        binding.fragmentCustomerAddCameraNumberEt.addTextChangedListener(textWatcher)
    }

}