package com.distribution.christian.cleantest.profile.presentation.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewFragmentConsistency


class HelpFragment : ProfileBaseFragment<ProfileOverviewFragmentConsistency>() {

   companion object {

      const val TAG = "Help"
      fun newInstance() = HelpFragment()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_help
   }

   override fun initViews(view: View) {

   }
}