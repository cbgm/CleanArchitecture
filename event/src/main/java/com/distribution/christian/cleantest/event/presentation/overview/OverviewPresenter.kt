package com.distribution.christian.cleantest.event.presentation.overview

import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.usecases.GetEventsInPool
import com.distribution.christian.cleantest.event.presentation.overview.mapper.EventOverviewDomainMapper
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver

class OverviewPresenter constructor(
      private val getEventsInPool: GetEventsInPool
) : OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   inner class GetEventsObserver : SingleLCEObserver<EventOverview>(overviewView) {
      override fun onSuccess(value: EventOverview) {
         super.onSuccess(value)
         overviewView.showEvents(EventOverviewDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         super.onError(throwable)
         overviewView.showError()
      }
   }

   inner class GetMoreUsersObserver : DefaultSingleObserver<EventOverview>() {
      override fun onSuccess(value: EventOverview) {
         overviewView.showListLoading(false)
         overviewView.showEvents(EventOverviewDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         overviewView.showError()
      }
   }

   override fun loadEvents() {
      getEventsInPool.execute(GetEventsObserver(), Unit)
   }

   override fun loadMoreEvents() {
      overviewView.showListLoading(true)
      getEventsInPool.execute(GetMoreUsersObserver(), Unit)
   }

   override fun setVIew(v: OverviewContract.View) {
      overviewView = v
   }

   override fun onBind() {
      overviewView.showLoading()
      loadEvents()
   }

   override fun onUnbind() {
      getEventsInPool.dispose()
   }

}