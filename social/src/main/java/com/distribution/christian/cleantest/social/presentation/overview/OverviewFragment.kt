package com.distribution.christian.cleantest.social.presentation.overview

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.social.R
import com.distribution.christian.cleantest.social.core.ui.SocialBaseFragment
import com.distribution.christian.cleantest.social.presentation.overview.model.SocialOverviewFragmentConsistency


class OverviewFragment : SocialBaseFragment<SocialOverviewFragmentConsistency>() {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }



   override fun getLayoutResId(): Int {
      return R.layout.fragment_social_overview
   }

   override fun initViews(view: View) {
      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_social,
            false
      )
   }
}