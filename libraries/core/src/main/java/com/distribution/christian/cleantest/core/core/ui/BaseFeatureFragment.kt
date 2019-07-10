package com.distribution.christian.cleantest.core.core.ui

import androidx.fragment.app.Fragment
import android.view.View
import com.distribution.christian.cleantest.core.core.navigation.coordinator.CoordinatorManager
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFeatureFragment<T> : Fragment() {

   companion object {
      const val TAG = ""
   }

   open val coordinatorManager: CoordinatorManager? = null

   val activity: T by lazy { getActivity() as T }

   data class TransitionInformation(val sharedElement: View, val transitionName: String) : Serializable
}