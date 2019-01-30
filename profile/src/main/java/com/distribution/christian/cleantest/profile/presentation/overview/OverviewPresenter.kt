package com.distribution.christian.cleantest.profile.presentation.overview

import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.domain.usecase.GetProfile
import com.distribution.christian.cleantest.profile.presentation.overview.mapper.ProfileOverviewDomainMapper

class OverviewPresenter(
      private val getProfile: GetProfile
) : OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   private inner class GetProfileObserver() : SingleLCEObserver<ProfileOverview>(overviewView) {
      override fun onSuccess(value: ProfileOverview) {
         super.onSuccess(value)
         overviewView.showProfile(ProfileOverviewDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         super.onError(throwable)
      }
   }

   override fun loadProfile() {
      overviewView.showLoading()
      getProfile.execute(GetProfileObserver(), Unit)
   }

   override fun setVIew(view: OverviewContract.View) {
      this.overviewView = view
   }

   override fun onBind() {
   }

   override fun onUnbind() {

   }
}