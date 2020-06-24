package com.kanaydo.androidbase.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.kanaydo.androidbase.R

class LoadingDialog(
    context: Context
) {

    private var dialog : Dialog = Dialog(context)

    init {
        with(dialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(R.layout.loading_dialog)
        }
    }

    fun show(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            dialog.hide()
        }
    }
}