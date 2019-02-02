package com.distribution.christian.cleantest.event.presentation.detail

import com.distribution.christian.cleantest.core.core.ui.BasePresenter
import com.distribution.christian.cleantest.core.core.ui.BaseView
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity


interface DetailContract {

   interface View : BaseView {

      fun showEvent(eventEntity: EventEntity)

      fun showUpdatedEventState(event: EventEntity)
   }

   interface Presenter : BasePresenter<View> {

      fun loadEvent(eventId: String)

      fun updateEvent(event: EventEntity)
   }
}