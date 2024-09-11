package com.example.findbug.utils

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.findbug.R
import com.example.findbug.databinding.ItemSpinnerBinding

class OptionSpinnerAdapter(context: Context, @LayoutRes private val resId: Int, private val menuList: List<String>)
    : ArrayAdapter<String>(context, resId, menuList) {

    // 드롭다운하지 않은 상태의 Spinner 항목의 뷰
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        if (position == 0) {
            binding.itemSpinnerTv.text = menuList[position]
            binding.itemSpinnerTv.setTextColor(ContextCompat.getColor(context, R.color.Blue500))
        } else {
            binding.itemSpinnerTv.text = menuList[position]
            binding.itemSpinnerTv.setTextColor(Color.BLACK)
        }

        return binding.root
    }

    // 드롭다운된 항목들 리스트의 뷰
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // 첫 번째 항목은 안 보이게 함
        return if (position == 0) {
            val hiddenView = View(context)
            hiddenView.layoutParams = ViewGroup.LayoutParams(0, 0)
            hiddenView
        } else {
            binding.itemSpinnerTv.text = menuList[position]
            binding.root
        }
    }

    override fun getCount(): Int {
        return menuList.size
    }
}
