package com.example.christian.cleantest.presentation.overview

import android.util.Log
import com.example.christian.cleantest.domain.model.UserOverview
import com.example.christian.cleantest.domain.usecases.GetUsersInPool
import com.example.christian.cleantest.presentation.overview.mapper.UserDomainMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class OverviewPresenter @Inject constructor(
        private val getUsersInPool: GetUsersInPool
): OverviewContract.Presenter{

    lateinit var overviewView: OverviewContract.View

    inner class GetUsersObserver: DisposableSingleObserver<UserOverview>() {
        override fun onSuccess(t: UserOverview) {
            overviewView.showError(false)
            overviewView.showLoading(false)
            overviewView.showContent(true)
            overviewView.updateUsers(UserDomainMapper.transform(t))
        }

        override fun onError(e: Throwable) {
            overviewView.showError(true)
            overviewView.showLoading(false)
            Log.e("MYER", "data not loadable")
        }
    }


    override fun loadUsers() {
        getUsersInPool.execute(GetUsersObserver(), Unit)
    }

    override fun setView(v: OverviewContract.View) {
        overviewView = v
    }

    override fun onBind() {
        overviewView.showLoading(true)
        overviewView.showContent(false)
        overviewView.showError(false)
        loadUsers()
    }

    override fun onUnbind() {
        getUsersInPool.dispose()
    }

}