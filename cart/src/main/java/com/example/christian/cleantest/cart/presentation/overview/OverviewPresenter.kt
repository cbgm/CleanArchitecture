package com.example.christian.cleantest.cart.presentation.overview

import com.example.christian.cleantest.cart.domain.model.UserOverview
import com.example.christian.cleantest.cart.domain.usecases.GetUsersInPool
import com.example.christian.cleantest.cart.presentation.overview.mapper.UserDomainMapper
import com.example.christian.cleantest.core.domain.single.SingleLCEObserver
import com.example.christian.cleantest.core.domain.single.DefaultSingleObserver

class OverviewPresenter constructor(
      private val getUsersInPool: GetUsersInPool
) : OverviewContract.Presenter {

   lateinit var overviewView: OverviewContract.View

   inner class GetUsersObserver : SingleLCEObserver<UserOverview>(overviewView) {
      override fun onSuccess(value: UserOverview) {
         super.onSuccess(value)
         overviewView.showUsers(UserDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
         super.onError(throwable)
         overviewView.showError()
      }
   }

   inner class GetMoreUsersObserver : DefaultSingleObserver<UserOverview>() {
      override fun onSuccess(value: UserOverview) {
         overviewView.showListLoading(false)
         overviewView.showUsers(UserDomainMapper.transform(value))
      }

      override fun onError(throwable: Throwable) {
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