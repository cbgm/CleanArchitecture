package com.distribution.christian.cleantest.event.presentation.detail

import com.distribution.christian.cleantest.core.domain.model.Event
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.distribution.christian.cleantest.event.domain.usecases.GetEventById
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.event.domain.usecases.UpdateEvent
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity

class DetailPresenter constructor(
      private val getEventById: GetEventById,
      private val updateEvent: UpdateEvent
) : DetailContract.Presenter {

   private lateinit var detailView: DetailContract.View

   private inner class GetCartObserver : SingleLCEObserver<Event>(detailView) {
      override fun onSuccess(value: Event) {
         super.onSuccess(value)
         detailView.showEvent(EventDomainMapper.transform(value))
      }
   }

   private inner class UpateEventObserver : DefaultSingleObserver<Event>() {
      override fun onSuccess(value: Event) {
         detailView.showUpdatedEventState(EventDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         detailView.showError()
      }
   }

   override fun loadEvent(eventId: String) {
      getEventById.execute(GetCartObserver(), eventId)
   }

   override fun updateEvent(event: EventEntity) {
      updateEvent.execute(UpateEventObserver(), EventDomainMapper.transform(event))
   }

   override fun setVIew(v: DetailContract.View) {
      detailView = v
   }

   override fun onBind() {
      detailView.showLoading()
   }

   override fun onUnbind() {
      getEventById.dispose()
   }

}