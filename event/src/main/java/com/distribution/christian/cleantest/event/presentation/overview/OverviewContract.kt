package com.distribution.christian.cleantest.event.presentation.overview

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity


interface OverviewContract {

   interface View : BaseView {

      fun showEvents(eventOverviewEntity: EventOverviewEntity)

      fun showMoreEvents(eventOverviewEntity: EventOverviewEntity)

      fun showListLoading(isVisible: Boolean)

      fun showUpdatedEventState(event: EventEntity)

      fun showPossibleCitys(citys: Array<String>)
   }

   interface Presenter : BasePresenter<View> {

      fun loadEvents()

      fun loadMoreEvents()

      fun updateEvent(event: EventEntity)

      fun loadUpdatedEventById(eventId: String)

      fun loadCitySuggestions(query: String)
   }
}