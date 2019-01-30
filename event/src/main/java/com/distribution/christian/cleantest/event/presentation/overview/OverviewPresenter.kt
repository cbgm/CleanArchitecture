package com.distribution.christian.cleantest.event.presentation.overview

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsInPool
import com.distribution.christian.cleantest.event.presentation.overview.mapper.EventOverviewDomainMapper
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.distribution.christian.cleantest.event.domain.usecase.UpdateEvent
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity

class OverviewPresenter constructor(
      private val getEventsInPool: GetEventsInPool,
      private val updateEvent: UpdateEvent
) : OverviewContract.Presenter {

   private lateinit var overviewView: OverviewContract.View

   private inner class GetEventsObserver : SingleLCEObserver<EventOverview>(overviewView) {
      override fun onSuccess(value: EventOverview) {
         super.onSuccess(value)
         overviewView.showEvents(EventOverviewDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         super.onError(throwable)
         overviewView.showError()
      }
   }

   private inner class GetMoreEventsObserver : DefaultSingleObserver<EventOverview>() {
      override fun onSuccess(value: EventOverview) {
         overviewView.showListLoading(false)
         overviewView.showMoreEvents(EventOverviewDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         overviewView.showError()
      }
   }

   private inner class UpateEventObserver : DefaultSingleObserver<Event>() {
      override fun onSuccess(value: Event) {
         overviewView.showUpdatedEventState(EventDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         overviewView.showError()
      }
   }

   override fun loadEvents() {
      getEventsInPool.execute(GetEventsObserver(), Unit)
   }

   override fun updateEvent(event: EventEntity) {
      updateEvent.execute(UpateEventObserver(), EventDomainMapper.transform(event))
   }

   override fun loadMoreEvents() {
      overviewView.showListLoading(true)
      getEventsInPool.execute(GetMoreEventsObserver(), Unit)
   }

   override fun setVIew(view: OverviewContract.View) {
      overviewView = view
   }

   override fun onBind() {
      overviewView.showLoading()
      loadEvents()
   }

   override fun onUnbind() {
      getEventsInPool.dispose()
   }

}