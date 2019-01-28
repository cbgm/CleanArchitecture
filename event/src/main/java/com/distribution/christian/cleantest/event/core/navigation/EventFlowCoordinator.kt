package com.distribution.christian.cleantest.event.core.navigation

import com.distribution.christian.cleantest.core.core.ui.BaseFragment

interface EventFlowCoordinator {
   fun showDetail(userId: String, transitionInformation: BaseFragment.TransitionInformation? = null)
   fun showOverview()
}