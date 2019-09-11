package com.distribution.christian.cleantest.profile.presentation.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseWindowFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.core.core.util.extension.showPrivacyDialog
import com.distribution.christian.cleantest.core.core.util.extension.showTermsDialog
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
            R.id.privacy -> activity?.showPrivacyDialog(this)
            R.id.terms -> activity?.showTermsDialog(this)
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