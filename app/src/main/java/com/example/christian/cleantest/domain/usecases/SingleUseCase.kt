package com.example.christian.cleantest.domain.usecases

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T> {

    private var compositeDisposable: CompositeDisposable? = null

    abstract fun buildUseCaseObservable(params: Params? = null): Single<T>

    fun execute(observer: DisposableSingleObserver<T>? = null): Single<T> {
        dispose()
        val observable = buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        if (observer != null)
            addDisposable(observable.subscribeWith(observer))
        return observable
    }


    class Params private constructor(private val userId: Int) {
        companion object {

            fun forUser(userId: Int): Params {
                return Params(userId)
            }
        }
    }

    fun dispose() = compositeDisposable?.takeIf { !it.isDisposed }?.dispose()

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable = CompositeDisposable().apply { add(disposable) }
    }

}