package com.distribution.christian.cleantest.core.core.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.distribution.christian.cleantest.core.R


open class BaseWindowFragment : DialogFragment() {

   lateinit var toolbar: Toolbar

   companion object {
      fun newInstance() = BaseWindowFragment()
   }

   override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      val view = inflater.inflate(R.layout.fragment_window, container, false)
      toolbar = view.findViewById(R.id.toolbar)
      toolbar.inflateMenu(R.menu.window_menu)
      toolbar.setOnMenuItemClickListener {
         when(it.itemId) {
            R.id.close -> {
               dismiss()
            }
         }
         true
      }
      return view
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
   }

   override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val dialog = super.onCreateDialog(savedInstanceState)
      dialog.window?.requestFeature(FEATURE_NO_TITLE)
      return dialog
   }

   override fun onStart() {
      super.onStart()
      val dialog = dialog
      if (dialog != null) {
         dialog.window?.setLayout(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.MATCH_PARENT
         )
      }
   }
}