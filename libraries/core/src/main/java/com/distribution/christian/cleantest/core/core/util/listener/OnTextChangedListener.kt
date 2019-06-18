package com.distribution.christian.cleantest.core.core.util.listener

import android.text.Editable
import android.text.TextWatcher


open class OnTextChangedListener: TextWatcher {
   override fun afterTextChanged(p0: Editable?) {

   }

   override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      //not used
   }

   override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      //not used
   }
}