package com.distribution.christian.cleantest.profile.presentation.overview

import com.distribution.christian.cleantest.core.domain.single.SingleLCEObserver
import com.distribution.christian.cleantest.profile.domain.model.ProfileOverview
import com.distribution.christian.cleantest.profile.domain.usecase.GetProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.domain.usecase.UpdateProfileOfAuthenticatedUser
import com.distribution.christian.cleantest.profile.presentation.overview.mapper.ProfileOverviewDomainMapper
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewEntity

class OverviewPresenter(
      private val getProfileOfAuthenticatedUser: GetProfileOfAuthenticatedUser,
      private val updateProfileOfAuthenticatedUser: UpdateProfileOfAuthenticatedUser
) : OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   private inner class GetProfileObserver() : SingleLCEObserver<ProfileOverview>(overviewView) {
      override fun onSuccess(value: ProfileOverview) {
         super.onSuccess(value)
         overviewView.showProfile(ProfileOverviewDomainMapper.transform(value))
      }
   }

   private inner class UpdateProfileObserver() : SingleLCEObserver<ProfileOverview>(overviewView) {
      override fun onSuccess(value: ProfileOverview) {
         super.onSuccess(value)
         overviewView.showProfile(ProfileOverviewDomainMapper.transform(value))
      }
   }

   override fun loadProfile() {
      overviewView.showLoading()
      getProfileOfAuthenticatedUser.execute(GetProfileObserver(), Unit)
   }

   override fun updateProfile(profileOverviewEntity: ProfileOverviewEntity) {
      overviewView.showLoading()
      updateProfileOfAuthenticatedUser.execute(
            UpdateProfileObserver(),
            ProfileOverviewDomainMapper.transform(
                  profileOverviewEntity
            )
      )
   }

   override fun setVIew(view: OverviewContract.View) {
      this.overviewView = view
   }

   override fun onBind() {
   }

   override fun onUnbind() {

   }
}