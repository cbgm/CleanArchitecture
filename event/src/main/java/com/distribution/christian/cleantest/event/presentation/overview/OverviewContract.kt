package com.distribution.christian.cleantest.event.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity

interface OverviewContract {

   interface View : BaseView {
      fun showEvents(eventOverviewEntity: EventOverviewEntity)
      fun showListLoading(isVisible: Boolean)
   }

   interface Presenter : BasePresenter<View> {
      fun loadEvents()
      fun loadMoreEvents()
   }
}