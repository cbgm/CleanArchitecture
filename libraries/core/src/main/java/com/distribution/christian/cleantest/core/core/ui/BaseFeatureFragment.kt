package com.distribution.christian.cleantest.core.core.ui

import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.distribution.christian.cleantest.core.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinator
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFeatureFragment<T: AppCompatActivity> : Fragment(), OnBackPressedListener {

   companion object {
      const val TAG = ""
   }

   open val coordinator: BaseCoordinator? = null

   val activity: T by lazy { getActivity() as T }

   lateinit var  navHostFragment: NavHostFragment

   data class TransitionInformation(val sharedElement: View, val transitionName: String) : Serializable

   override fun onBackPressed() {
      navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
      //val currentFragment = navHostFragment.childFragmentManager.fragments[0]
      val controller = Navigation.findNavController(activity, R.id.fragment_container)
      if (!controller.navigateUp())
         activity.finish()
   }
}