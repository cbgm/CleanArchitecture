package com.example.christian.cleantest.core.ui

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.christian.cleantest.R

class HintDialog(
        private val context: Context,
        private val title: String,
        private val message: String
) {
    private val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_hint, null)
    }
    private val dialog:Dialog by lazy {
        prepareDialog()
        val builder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)
        builder.create()
    }

    private fun prepareDialog() {
        val messageText: TextView = dialogView.findViewById(R.id.message_text)
        val titleText: TextView = dialogView.findViewById(R.id.title_text)
        val understoodBtn: TextView = dialogView.findViewById(R.id.understood_btn)
        messageText.text = message
        titleText.text = title
        understoodBtn.setOnClickListener {
            dialog.dismiss()
        }

    }

    fun show() {
        dialog.show()
    }
}