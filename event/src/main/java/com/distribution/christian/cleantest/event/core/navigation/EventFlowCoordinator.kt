package com.distribution.christian.cleantest.event.core.navigation

import com.distribution.christian.cleantest.core.core.ui.BaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity


interface EventFlowCoordinator {

   fun showDetail(userId: String? = null, transitionInformation: BaseFragment.TransitionInformation? = null, event: EventEntity? = null)

   fun showOverview()
}