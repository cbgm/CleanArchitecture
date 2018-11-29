package com.example.christian.cleantest.cart.presentation.overview

import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.usecases.GetUsersInPool
import com.example.christian.cleantest.cart.presentation.overview.mapper.UserDomainMapper
import io.reactivex.observers.DisposableSingleObserver

class OverviewPresenter constructor(
      private val getUsersInPool: GetUsersInPool
) : OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   inner class GetUsersObserver : DisposableSingleObserver<UserOverview>() {
      override fun onSuccess(t: UserOverview) {
         overviewView.showContent()
         overviewView.showUsers(UserDomainMapper.transform(t))
      }

      override fun onError(e: Throwable) {
         overviewView.showError()
      }
   }

   inner class GetMoreUsersObserver : DisposableSingleObserver<UserOverview>() {
      override fun onSuccess(t: UserOverview) {
         overviewView.showListLoading(false)
         overviewView.showUsers(UserDomainMapper.transform(t))
      }

      override fun onError(e: Throwable) {
         overviewView.showError()
      }
   }

   override fun loadUsers() {
      getUsersInPool.execute(GetUsersObserver(), Unit)
   }

   override fun loadMoreUsers() {
      overviewView.showListLoading(true)
      getUsersInPool.execute(GetMoreUsersObserver(), Unit)
   }

   override fun setVIew(v: OverviewContract.View) {
      overviewView = v
   }

   override fun onBind() {
      overviewView.showLoading()
      loadUsers()
   }

   override fun onUnbind() {
      getUsersInPool.dispose()
   }

}