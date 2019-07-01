package com.distribution.christian.cleantest.core.core.navigation

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.core.core.ui.BaseFragment


abstract class CoordinatorManager {
   private val featureCoordinators = HashMap<State, BaseCoordinator>()
   lateinit var currentFeatureCoordinator: BaseCoordinator
   lateinit var mainCoordinator: BaseCoordinator
   lateinit var applicationPartCoordinator: BaseCoordinator

   fun switchfeatureCoordinator(featureKey: State, fragment: Fragment) {
      val tempFeatureCoordinator = featureCoordinators[featureKey]
      tempFeatureCoordinator?.let {
         this.currentFeatureCoordinator = it
         this.currentFeatureCoordinator.start(fragment)
      }
   }

   fun registerFeatureCoordinator(featureKey: State, featureCoordinator: BaseCoordinator) {
      this.featureCoordinators[featureKey] = featureCoordinator
   }

   fun unregisterFeaureCoordinator(featureKey: State) {
      this.featureCoordinators.remove(featureKey)
   }

   fun navigateInFeature(routeKey: State, navigationData: NavigationData? = null) {
      this.currentFeatureCoordinator.route(routeKey, navigationData)
   }

   fun backStackFeature(){
      this.currentFeatureCoordinator.back()
   }

   fun navigateToFeature(routeKey: State, navigationData: NavigationData? = null) {
      this.mainCoordinator.route(routeKey, navigationData)
   }

   fun navigateToApplicationPart(routeKey: State){
      this.applicationPartCoordinator.route(routeKey, null)
   }

   fun startNavigation(
         fragmentActivity: FragmentActivity,
         uri: Uri? = null,
         withInitialNavigation: Boolean? = null
   ) {
      when {
         withInitialNavigation != null -> this.mainCoordinator.start(fragmentActivity, withInitialNavigation)
         uri == null -> this.mainCoordinator.start(fragmentActivity, uri)
         else -> this.mainCoordinator.start(fragmentActivity)
      }
   }

   data class NavigationData(val params: HashMap<String, Any>? = null, val transitionInformation: BaseFragment.TransitionInformation? = null)

   interface State
}