package com.distribution.christian.cleantest.profile.presentation.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.distribution.christian.cleantest.core.core.ui.BaseWindowFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.profile.R

class HelpDialog : BaseWindowFragment() {
   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      val view = super.onCreateView(inflater, container, savedInstanceState)
      toolbar.title = getString(R.string.title_help)
      toolbar.inflateMenu(R.menu.help_toolbar_menu)
      toolbar.setOnMenuItemClickListener {
         when(it.itemId) {
            R.id.privacy -> {}
            R.id.terms -> {}
         }
         true
      }
      return view
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      replaceFragment(HelpFragment.newInstance(), R.id.fragment_container, "")
   }
}