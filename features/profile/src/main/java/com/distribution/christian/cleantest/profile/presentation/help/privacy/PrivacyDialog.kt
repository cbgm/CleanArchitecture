package com.distribution.christian.cleantest.profile.presentation.help.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.core.ui.BaseWindowFragment
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.profile.R

class PrivacyDialog : BaseWindowFragment() {
   override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
   ): View? {
      val view = super.onCreateView(inflater, container, savedInstanceState)
      toolbar.title = getString(R.string.title_privacy)
      return view
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      replaceFragment(PrivacyFragment.newInstance(), R.id.fragment_container, "")
   }
}