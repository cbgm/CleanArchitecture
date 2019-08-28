package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.distribution.christian.cleantest.core.R


class InstallFeatureDialog(layoutId: Int) : BlockingDialog(layoutId, "Install") {

   private lateinit var messageText: TextView
   private lateinit var descriptionText: TextView
   private lateinit var progressBar: ProgressBar

   companion object {
      fun newInstance(innerLayoutId: Int, title: String, message: String, description: String): InstallFeatureDialog {
         val frag = InstallFeatureDialog(innerLayoutId)
         val args = Bundle()
         args.putInt("id", innerLayoutId)
         args.putString("title", title)
         args.putString("description", description)
         args.putString("message", message)
         frag.arguments = args
         return frag
      }
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      messageText = view.findViewById(R.id.message_text)
      messageText.text = arguments!!.getString("message", "")
      descriptionText = view.findViewById(R.id.description_text)
      descriptionText.text = arguments!!.getString("description", "")
      progressBar = view.findViewById(R.id.progress_dialog)
      progressBar.progress = 0
      progressBar.max = 100
   }

   fun setProgress(bytesToDownload: Long, bytesDownloaded: Long) {
      progressBar.visibility = View.VISIBLE
      progressBar.max = bytesToDownload.toInt()
      progressBar.progress = bytesDownloaded.toInt()
   }

   fun setText(message: String) {
      messageText.text = message
   }
}