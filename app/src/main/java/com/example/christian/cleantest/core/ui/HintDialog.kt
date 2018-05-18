package com.example.christian.cleantest.core.ui

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.example.christian.cleantest.R

class HintDialog(
        private val context: Context,
        private val title: String,
        private val message: String
) {



    fun show() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_hint, null)

        val messageText: TextView = dialogView.findViewById(R.id.message_text)
        val titleText: TextView = dialogView.findViewById(R.id.title_text)
        val understoodBtn: TextView = dialogView.findViewById(R.id.understood_btn)
        messageText.text = message
        titleText.text = title

        val builder = AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(false)
        val dialog = builder.show()
        understoodBtn.setOnClickListener {
            dialog.dismiss()
        }

    }
}