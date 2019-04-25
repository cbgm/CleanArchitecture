package com.distribution.christian.cleantest.event.presentation.overview

import com.distribution.christian.cleantest.event.domain.model.Event
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.presentation.overview.mapper.EventOverviewDomainMapper
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.core.domain.single.DefaultSingleObserver
import com.distribution.christian.cleantest.core.presentation.mapper.SearchDomainMapper
import com.distribution.christian.cleantest.core.presentation.model.SearchEntity
import com.distribution.christian.cleantest.event.domain.usecase.GetCitysByQuery
import com.distribution.christian.cleantest.event.domain.usecase.GetEventById
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsByCriteria
import com.distribution.christian.cleantest.event.domain.usecase.UpdateEvent
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity

class OverviewPresenter constructor(
      private val getEventsByCriteria: GetEventsByCriteria,
      private val updateEvent: UpdateEvent,
      private val getEventById: GetEventById,
      private val getCitysByQuery: GetCitysByQuery
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

   private inner class GetEventByIdObserver : SingleLCEObserver<Event>(overviewView) {
      override fun onSuccess(value: Event) {
         overviewView.showUpdatedEventState(EventDomainMapper.transform(value))
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

   private inner class GetCitysObserver : DefaultSingleObserver<Array<String>>() {
      override fun onSuccess(value: Array<String>) {
         overviewView.showPossibleCitys(value)
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

   override fun loadUpdatedEventById(eventId: String) {
      getEventById.execute(GetEventByIdObserver(), eventId)
   }

   override fun updateEvent(event: EventEntity) {
      //because room persistence is also called its necessary to execute with long
      updateEvent.executeLong(UpateEventObserver(), EventDomainMapper.transform(event))
   }

   override fun loadCitySuggestions(query: String) {
      getCitysByQuery.execute(GetCitysObserver(), query)
   }

   override fun loadMoreEvents(searchEntity: SearchEntity?) {
      overviewView.showListLoading(true)
      getEventsByCriteria.execute(
            GetMoreEventsObserver(),
            if (searchEntity != null) SearchDomainMapper.transform(
                  searchEntity
            ) else null
      )
   }

   override fun setVIew(view: OverviewContract.View) {
      overviewView = view
   }

   override fun onBind() {
      overviewView.showLoading()
      loadEvents(null)
   }

   override fun loadEvents(searchEntity: SearchEntity?) {
      overviewView.showLoading()
      getEventsByCriteria.execute(
            GetEventsObserver(),
            if (searchEntity != null) SearchDomainMapper.transform(
                  searchEntity
            ) else null
      )
   }

   override fun onUnbind() {
      getCitysByQuery.dispose()
      getEventsByCriteria.dispose()
      getEventById.dispose()
      updateEvent.dispose()
   }
}