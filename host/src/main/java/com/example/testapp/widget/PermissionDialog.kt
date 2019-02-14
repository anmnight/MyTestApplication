package com.example.testapp.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.testapp.R

class PermissionDialog(context: Context,
                       private val okListener: OkListener) : Dialog(context) {

    val tag = "PermissionDialog"

    private var ok: Button
    private var cancel: Button
    private var contentText: TextView

    init {

        setCancelable(false)

        val contentView = layoutInflater.inflate(R.layout.dialog_permission, null)

        ok = contentView.findViewById(R.id.permission_ok)
        cancel = contentView.findViewById(R.id.permission_cancel)
        contentText = contentView.findViewById(R.id.permission_content)


        setContentView(contentView)

        val layoutParams = window?.attributes

        val width = context.applicationContext.resources.displayMetrics.widthPixels

        layoutParams?.width = width
        layoutParams?.height = width

        window?.attributes = layoutParams

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ok.setOnClickListener {
            okListener.onOk()
            dismiss()
        }

        cancel.setOnClickListener {
            dismiss()
        }

    }

    public fun setContentText(content: String) {
        contentText.text = content
    }


    interface OkListener {
        fun onOk()
    }

}
