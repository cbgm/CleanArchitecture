package com.distribution.christian.cleantest.core.core.navigation

import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.distribution.christian.cleantest.core.core.ui.BaseFragment

data class NavigationData(
      override val params: HashMap<String, Any>?,
      val transitionInformation: BaseFragment.TransitionInformation? = null
): CoordinatorManager.NavigationData(params)