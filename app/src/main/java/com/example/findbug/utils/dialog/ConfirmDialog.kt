package com.example.findbug.utils.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.findbug.databinding.DialogStyleCancelBinding

class ConfirmDialog(
    title: String, content: String
) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.7).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    // 뷰 바인딩
    private var _binding: DialogStyleCancelBinding? = null
    private val binding get() = _binding!!

    private var confirmDialogInterface: ConfirmDialogInterface? = null

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
        _binding = DialogStyleCancelBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.dialogStyleCancelTitleTv.text = title
        binding.dialogStyleCancelContentTv.text = content

        // 취소 버튼 클릭
        binding.dialogStyleCancelBtn.setOnClickListener {
            dismiss()
        }

        // 확인 버튼 클릭
        binding.dialogStyleYesBtn.setOnClickListener {
            confirmDialogInterface?.onClickYesBtn()
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}