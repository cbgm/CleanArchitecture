package com.distribution.christian.cleantest.core.core.util.extension

import android.os.Bundle
import androidx.fragment.app.Fragment


inline fun <FRAGMENT : Fragment> FRAGMENT.args(argsBuilder: Bundle.() -> Unit): FRAGMENT = this.apply {
   arguments = Bundle().apply(
         argsBuilder
   )
}

inline fun Fragment.argsUpdate(args: Bundle.() -> Unit) {
   this.arguments?.args()
}