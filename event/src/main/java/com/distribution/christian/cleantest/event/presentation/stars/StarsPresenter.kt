package com.distribution.christian.cleantest.event.presentation.stars

import com.distribution.christian.cleantest.core.domain.completable.CompletableLCEObserver
import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.event.domain.model.EventOverview
import com.distribution.christian.cleantest.event.domain.usecase.DeleteEventFromCache
import com.distribution.christian.cleantest.event.domain.usecase.GetEventsFromCache
import com.distribution.christian.cleantest.event.presentation.detail.mapper.EventDomainMapper
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.event.presentation.overview.mapper.EventOverviewDomainMapper
import javax.inject.Inject


class StarsPresenter @Inject constructor(
      private val getEventsFromCache: GetEventsFromCache,
      private val deleteEventFromCache: DeleteEventFromCache
): StarsContract.Presenter {
   private lateinit var starsView: StarsContract.View

   inner class LoadStarredEventsObserver: SingleLCEObserver<EventOverview>(starsView) {
      override fun onSuccess(value: EventOverview) {
         if (value.count > 0) {
            starsView.showContent()
            starsView.showStars(EventOverviewDomainMapper.transform(value))
         } else {
            starsView.showError()
         }
      }
   }

   inner class DeleteStarredEventObserver: CompletableLCEObserver(starsView) {
      override fun onComplete() {
         starsView.showDeletedStars()
      }
   }

   override fun loadEvents() {
      getEventsFromCache.executeLong(LoadStarredEventsObserver(), Unit)
   }

   override fun deleteEvent(eventEntity: EventEntity) {
      deleteEventFromCache.execute(DeleteStarredEventObserver(), EventDomainMapper.transform(eventEntity))
   }

   override fun setVIew(view: StarsContract.View) {
      this.starsView = view
   }

   override fun onBind() {
      starsView.showLoading()
      getEventsFromCache.execute(LoadStarredEventsObserver(), Unit)
   }

   override fun onUnbind() {
      getEventsFromCache.dispose()
      deleteEventFromCache.dispose()
   }

   override fun triggerEmptyEvents(starsSize: Int) {
      if (starsSize == 0) {
         starsView.showError()
      }
   }
}