package com.distribution.christian.cleantest.app.core.navigation

import androidx.fragment.app.FragmentActivity
import com.distribution.christian.cleantest.R
import com.distribution.christian.cleantest.core.core.navigation.BaseCoordinatorImpl
import com.distribution.christian.cleantest.core.core.navigation.deeplink.DeepLinkIdentifier
import com.distribution.christian.cleantest.core.core.ui.BaseFeatureFragment
import com.distribution.christian.cleantest.core.core.ui.BaseNavigationActivity
import com.distribution.christian.cleantest.core.core.util.extension.replaceFragment
import com.distribution.christian.cleantest.core.core.util.extension.showFragment
import com.distribution.christian.cleantest.event.core.ui.EventFeatureFragment
import com.distribution.christian.cleantest.event.presentation.overview.OverviewFragment
import com.distribution.christian.cleantest.profile.core.ui.ProfileFeatureFragment


class MainCoordinatorImpl : BaseCoordinatorImpl() {

   /*private val eventFeature: BaseFeatureFragment<BaseNavigationActivity> = EventFeatureFragment.newInstance()
   private val shopFeature: BaseFeatureFragment<BaseNavigationActivity> by lazy {
      Class.forName("com.distribution.christian.cleantest.shop.core.ui.ShopFeatureFragment").newInstance() as BaseFeatureFragment<BaseNavigationActivity>
   }
   private val profileFeature: BaseFeatureFragment<BaseNavigationActivity> = ProfileFeatureFragment.newInstance()
   private var active: BaseFeatureFragment<BaseNavigationActivity>? = null*/
   override var replaceableFragmentId: Int = R.id.feature_container

   override fun navigateDeepLink() {
      deepLinkHandler.getDeepLink()
            ?.let {
               when (it.action) {
                  DeepLinkIdentifier.EVENTS-> showEvents()
                  DeepLinkIdentifier.SHOP -> showShop()
                  else -> showEvents()
               }
            }
   }

   fun showEvents() {
      /*activity?.showFragment(active, eventFeature)
      active = eventFeature*/
      activity?.replaceFragment(
            EventFeatureFragment.newInstance(),
            replaceableFragmentId,
            EventFeatureFragment.TAG
      )
   }

   fun showProfile(){
      /*activity?.showFragment(active, profileFeature)
      active = profileFeature*/
      activity?.replaceFragment(
            ProfileFeatureFragment.newInstance(),
            replaceableFragmentId,
            ProfileFeatureFragment.TAG
      )
   }

   fun showShop(){
      /*activity?.showFragment(active, shopFeature)
      active = shopFeature*/
      activity?.replaceFragment(
            Class.forName("com.distribution.christian.cleantest.shop.core.ui.ShopFeatureFragment").newInstance() as BaseFeatureFragment<BaseNavigationActivity>,
            replaceableFragmentId,
            "dyn"
      )
   }

   /*override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      super.start(fragmentActivity, withInitialNavigation)
      initFeatures()
   }*/

   override fun navigateLink() {
      showEvents()
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   /*private fun initFeatures(){
      activity?.let {
         it.supportFragmentManager.run {
            beginTransaction()
                  .add(R.id.feature_container, shopFeature, shopFeature::class.java.name)
                  .hide(shopFeature)
                  .commitNow()
            beginTransaction()
                  .add(R.id.feature_container, profileFeature, ProfileFeatureFragment.TAG)
                  .hide(profileFeature)
                  .commitNow()
            beginTransaction()
                  .add(R.id.feature_container, eventFeature, EventFeatureFragment.TAG)
                  .commitNow()
         }
      }
   }*/
}
