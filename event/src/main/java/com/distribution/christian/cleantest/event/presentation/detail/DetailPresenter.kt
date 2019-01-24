package com.distribution.christian.cleantest.event.presentation.detail

import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.event.domain.usecases.GetEventById
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver

class DetailPresenter constructor(
      private val getEventById: GetEventById
) : DetailContract.Presenter {

   lateinit var eventView: DetailContract.View

   inner class GetCartObserver : SingleLCEObserver<Event>(eventView) {
      override fun onSuccess(value: Event) {
         super.onSuccess(value)
         eventView.showEvent(EventDomainMapper.transform(value))
      }
   }

   override fun loadEvent(eventId: String) {
      getEventById.execute(GetCartObserver(), eventId)
   }

   override fun setVIew(v: DetailContract.View) {
      eventView = v
   }

   override fun onBind() {
      eventView.showLoading()
   }

   override fun onUnbind() {
      getEventById.dispose()
   }

}