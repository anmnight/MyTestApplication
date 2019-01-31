package com.anmnight.commlibrary.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.anmnight.commlibrary.R

class LoadingTextDialog(context: Context) : Dialog(context) {

    private val mProgress: ProgressBar
    private val mButton: Button
    private val mTextView: TextView

    init {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.dialog_loading_text_layout, null)

        mProgress = view.findViewById(R.id.progress_bar)
        mButton = view.findViewById(R.id.button)
        mTextView = view.findViewById(R.id.text)
        mTextView.gravity = Gravity.CENTER

        setCancelable(false)

        setContentView(view)

        val width = context.applicationContext.resources.displayMetrics.widthPixels

        val lp = window?.attributes
        lp?.width = width
        lp?.height = width

        window?.attributes = lp
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mButton.setOnClickListener {
            dismiss()
        }
    }

    public fun showLoading() {

        mTextView.visibility = View.GONE
        mProgress.visibility = View.VISIBLE
        mButton.visibility = View.GONE

        if (!isShowing) {
            show()
        }
    }

    public fun showText(message: String) {

        mTextView.visibility = View.VISIBLE
        mProgress.visibility = View.GONE
        mButton.visibility = View.VISIBLE

        mTextView.text = message

        if (!isShowing) {
            show()
        }
    }

}
