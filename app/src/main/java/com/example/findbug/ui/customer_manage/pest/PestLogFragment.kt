package com.example.findbug.ui.customer_manage.pest

import android.os.Build
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding
import com.example.findbug.ui.home.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class PestLogFragment : BaseFragment<FragmentPestLogBinding>(R.layout.fragment_pest_log) {

    private val mainViewModel : MainViewModel by activityViewModels()

    private var currentPage = 1
    private var detectionHistoryId: Long = 0

    private var solution1: String? = ""
    private var solution2: String? = ""
    private var solution3: String? = ""
    private var solution4: String? = ""

    private lateinit var pageTextViews: List<TextView>
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descTextView: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
        initSettings()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSettings() {
        setToolbarNavigation(binding.fragmentPestLogToolbar.toolbarPreviousIb)
        getDetectionHistoryId()
        initView()
        observeViewModel()
        initButtons()
        updatePageUI(currentPage)
        changePage()
    }

    private fun getDetectionHistoryId() {
        arguments.let {
            if (it != null) { detectionHistoryId = it.getLong("detectionHistoryId") }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.getBugRecord(detectionHistoryId)
                mainViewModel.bugRecordResponse.collect() { res ->
                    binding.bugRecord = res.body()
                    binding.bugDetail = res.body()?.bugDetailDto
                    binding.bugSolution = res.body()?.bugSolutionDto

                    // 시간, 날짜
                    val formatTime = res.body()?.bugFindTime
                    val formatDate = res.body()?.bugFindDate
                    binding.fragmentPestLogTopTimeTv1.text = formatTime?.let { timeFormat(it) }
                    binding.fragmentPestLogTopDateTv3.text = formatDate?.let { formatDate }

                    // 솔루션
                    solution1 = res.body()?.bugSolutionDto?.firstSolution.toString() ?: getString(R.string.solution1_desc)
                    solution2 = res.body()?.bugSolutionDto?.secondSolution.toString() ?: getString(R.string.solution2_desc)
                    solution3 = res.body()?.bugSolutionDto?.thirdSolution.toString() ?: getString(R.string.solution3_desc)
                    solution4 = res.body()?.bugSolutionDto?.fourSolution.toString() ?: getString(R.string.solution4_desc)
                    descTextView.text = solution1
                }
            }
        }
    }

    // 시간 형식 변경 함수
    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeFormat(bugFindTime: String): String {
        val inputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS")
        val time = LocalTime.parse(bugFindTime, inputTimeFormatter)
        val outputTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        return time.format(outputTimeFormatter)
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

         // 발견 영상 보기 버튼
        binding.fragmentPestLogPestShowVideoBtn.setOnClickListener {

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
                descTextView.text = solution1
            }
            2 -> {
                imageView.setImageResource(R.drawable.ic_solution2)
                titleTextView.text = getString(R.string.solution2_title)
                descTextView.text = solution2
            }
            3 -> {
                imageView.setImageResource(R.drawable.ic_solution3)
                titleTextView.text = getString(R.string.solution3_title)
                descTextView.text = solution3
            }
            4 -> {
                imageView.setImageResource(R.drawable.ic_solution4)
                titleTextView.text = getString(R.string.solution4_title)
                descTextView.text = solution4
            }
        }
    }

}