package com.example.findbug.ui.customer_manage.pest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding

class PestLogFragment : BaseFragment<FragmentPestLogBinding>(R.layout.fragment_pest_log) {

    private var currentPage = 1
    private lateinit var pageTextViews: List<TextView>
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descTextView: TextView

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentPestLogToolbar.toolbarPreviousIb)
        initView()
        initButtons()
        updatePageUI(currentPage)
        changePage()
    }

    private fun initView() {

        with(binding) {
            pageTextViews = listOf(
                fragmentPestLogPestSolutionPage1Tv,
                fragmentPestLogPestSolutionPage2Tv,
                fragmentPestLogPestSolutionPage3Tv,
                fragmentPestLogPestSolutionPage4Tv
            )
            prevButton = fragmentPestLogPestSolutionPrevIb
            nextButton = fragmentPestLogPestSolutionNextIb
            imageView = fragmentPestLogPestSolutionIv
            titleTextView = fragmentPestLogPestSolutionTitleTv
            descTextView = fragmentPestLogPestSolutionDescTv
        }
    }

     private fun initButtons() {

        binding.fragmentPestLogPestShowVideoBtn.setOnClickListener {
            // 발견 영상 보기 버튼
        }
    }

    private fun changePage() {

        pageTextViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                currentPage = index + 1
                updatePageUI(currentPage)
            }
        }

        // 이전 버튼 클릭 처리
        prevButton.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                updatePageUI(currentPage)
            }
        }

        // 다음 버튼 클릭 처리
        nextButton.setOnClickListener {
            if (currentPage < 4) {
                currentPage++
                updatePageUI(currentPage)
            }
        }
    }


    // 페이지에 따른 UI 업데이트
    private fun updatePageUI(page: Int) {

        // TextView 색상 변경
        pageTextViews.forEachIndexed { index, textView ->
            val color = if (index + 1 == page) R.color.Blue700 else R.color.Black700
            textView.setTextColor(ContextCompat.getColor(requireContext(), color))
        }

        // 페이지별 이미지와 텍스트 변경
        when (page) {
            1 -> {
                imageView.setImageResource(R.drawable.ic_solution1)
                titleTextView.text = getString(R.string.solution1_title)
                descTextView.text = getString(R.string.solution1_desc)
            }
            2 -> {
                imageView.setImageResource(R.drawable.ic_solution2)
                titleTextView.text = getString(R.string.solution2_title)
                descTextView.text = getString(R.string.solution2_desc)
            }
            3 -> {
                imageView.setImageResource(R.drawable.ic_solution3)
                titleTextView.text = getString(R.string.solution3_title)
                descTextView.text = getString(R.string.solution3_desc)
            }
            4 -> {
                imageView.setImageResource(R.drawable.ic_solution4)
                titleTextView.text = getString(R.string.solution4_title)
                descTextView.text = getString(R.string.solution4_desc)
            }
        }
    }

}