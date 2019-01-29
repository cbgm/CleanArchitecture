package com.distribution.christian.cleantest.profile.presentation.overview

class OverviewPresenter: OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   override fun loadProfile() {
      overviewView.showLoading()
      overviewView.showContent()
      overviewView.showProfile()
   }

   override fun setVIew(view: OverviewContract.View) {
      this.overviewView = view
   }

   override fun onBind() {
   }

   override fun onUnbind() {

   }
}