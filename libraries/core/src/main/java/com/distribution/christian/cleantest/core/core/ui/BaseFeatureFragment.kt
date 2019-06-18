package com.distribution.christian.cleantest.core.core.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinator
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFeatureFragment<T> : Fragment() {

   companion object {

      const val TAG = ""
   }

   open val coordinator: BaseCoordinator? = null

   val activity: T by lazy { getActivity() as T }

   data class TransitionInformation(val sharedElement: View, val transitionName: String) : Serializable

   override fun onHiddenChanged(hidden: Boolean) {
      super.onHiddenChanged(hidden)

      if (!hidden){
         (activity as AppCompatActivity).invalidateOptionsMenu()
         childFragmentManager.fragments[0].onHiddenChanged(true)
      }
   }
}