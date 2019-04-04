package com.distribution.christian.cleantest.event.presentation.stars

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.model.EventOverviewEntity


interface StarsContract {

   interface View : BaseView {

      fun showStars(eventOverviewEntity: EventOverviewEntity)

      fun showDeletedStars()
   }

   interface Presenter : BasePresenter<View> {

      fun loadEvents()

      fun triggerEmptyEvents(starsSize: Int)

      fun deleteEvent(eventEntity: EventEntity)
   }
}