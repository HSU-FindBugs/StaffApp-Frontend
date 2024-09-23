package com.example.findbug.utils.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.findbug.databinding.DialogStyleNoticeBinding
import com.example.findbug.databinding.DialogStyleSearchBinding

class NoticeDialog(title: String, content: String) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.7).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    // 뷰 바인딩
    private var _binding: DialogStyleNoticeBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var content: String? = null

    init {
        this.title = title
        this.content = content
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogStyleNoticeBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogStyleNoticeSubtitleTv.text = title
        binding.dialogStyleNoticeContentTv.text = content

        // 확인 버튼 클릭
        binding.dialogStyleNoticeYesBtn.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}