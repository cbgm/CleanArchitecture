package com.distribution.christian.cleantest.event.presentation.stars

import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.event.R
import kotlinx.android.synthetic.main.activity_stars.button2

class StarsActivity: BaseActivity(R.layout.activity_stars) {

   override fun onResume() {
      super.onResume()
      button2.setOnClickListener {
         this.finish()
      }
   }

}