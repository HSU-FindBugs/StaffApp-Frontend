package com.example.findbug.ui.customer_manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerHomeBinding
import com.example.findbug.ui.customer_manage.vp.CustomerHomeVPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CustomerHomeFragment : BaseFragment<FragmentCustomerHomeBinding>(R.layout.fragment_customer_home) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initTabLayout()
    }

    private fun initTabLayout() {

        val tabLayout = binding.fragmentCustomerHomeTab
        val viewPager = binding.fragmentCustomerHomeVp2

        val adapter = CustomerHomeVPAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val customView = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null)
            val tabIcon = customView.findViewById<ImageView>(R.id.tab_icon)
            val tabText = customView.findViewById<TextView>(R.id.tab_text)

            when (position) {
                0 -> {
                    tabText.text = "고객 찾기"
                    tabIcon.setImageResource(R.drawable.ic_search_white)
                    tabText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    customView?.setBackgroundResource(R.drawable.tab_selected_first)
                }
                1 -> {
                    tabText.text = "고객 등록"
                    tabIcon.setImageResource(R.drawable.ic_add_customer_black)
                }
            }
            tab.customView = customView
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            // 탭 선택 되었을 때
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tabIcon = customView?.findViewById<ImageView>(R.id.tab_icon)
                val tabText = customView?.findViewById<TextView>(R.id.tab_text)

                when (tab?.position) {
                    0 -> {
                        tabIcon?.setImageResource(R.drawable.ic_search_white)
                        tabText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        customView?.setBackgroundResource(R.drawable.tab_selected_first)
                    }
                    1 -> {
                        tabIcon?.setImageResource(R.drawable.ic_add_customer_white)
                        tabText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        customView?.setBackgroundResource(R.drawable.tab_selected_first)
                    }
                }
            }

            // 탭 선택 되지 않았을 때
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                val tabIcon = customView?.findViewById<ImageView>(R.id.tab_icon)
                val tabText = customView?.findViewById<TextView>(R.id.tab_text)

                when (tab?.position) {
                    0 -> {
                        tabIcon?.setImageResource(R.drawable.ic_search_black)
                        tabText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Black700))
                        customView?.setBackgroundResource(R.drawable.tab_un_selected_first)
                    }
                    1 -> {
                        tabIcon?.setImageResource(R.drawable.ic_add_customer_black)
                        tabText?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Black700))
                        customView?.setBackgroundResource(R.drawable.tab_un_selected_second)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // TabLayout의 레이아웃이 완전히 준비된 후에 패딩을 설정
        tabLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                for (i in 0 until tabLayout.tabCount) {
                    val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
                    if (i == 0) {
                        tab.setPadding(10, 0, 0, 0)
                    } else if (i == tabLayout.tabCount - 1) {
                        tab.setPadding(0, 0, 10, 0)
                    }
                }

                // 패딩 적용 후 리스너 제거 (한 번만 실행되도록)
                tabLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

    }

}