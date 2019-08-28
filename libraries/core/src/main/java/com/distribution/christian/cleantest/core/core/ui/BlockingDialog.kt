package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.distribution.christian.cleantest.core.R


open class BlockingDialog(
      private val innerLayoutId: Int,
      private val title: String
) : DialogFragment() {

   companion object {
      fun newInstance(innerLayoutId: Int, title: String): BlockingDialog {
         val frag = BlockingDialog(innerLayoutId, title)
         val args = Bundle()
         args.putString("title", title)
         frag.arguments = args
         return frag
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setStyle(STYLE_NO_FRAME, R.style.AppTheme)
   }

   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      val rootView = inflater.inflate(R.layout.fragment_dialog_blocking, container, false)
      val innerLayout = rootView.findViewById(R.id.inner_layout) as FrameLayout
      inflater.inflate(innerLayoutId, innerLayout, true)
      val toolbar = rootView.findViewById(R.id.toolbar) as Toolbar
      toolbar.title = title
      return rootView
   }
}