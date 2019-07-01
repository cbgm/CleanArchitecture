package com.distribution.christian.cleantest.event.presentation.stars

import android.os.Bundle
import com.distribution.christian.cleantest.core.core.ui.BaseWindowActivity
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.event.R


class StarsActivity : BaseWindowActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      replaceFragment(StarsFragment.newInstance(), R.id.fragment_container, "")
   }
}
