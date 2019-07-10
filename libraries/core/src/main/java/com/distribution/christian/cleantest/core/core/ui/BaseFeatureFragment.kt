package com.distribution.christian.cleantest.core.core.ui

import android.view.View
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.ui.FeatureFragment
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFeatureFragment<T>(featureState: CoordinatorManager.State) : FeatureFragment(featureState) {

   companion object {
      const val TAG = ""
   }

   val activity: T by lazy { getActivity() as T }

   data class TransitionInformation(val sharedElement: View, val transitionName: String) : Serializable
}