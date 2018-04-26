package com.example.christian.cleantest.domain.usecases

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T> {

    private var compositeDisposable: CompositeDisposable? = null

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    fun execute(observer: DisposableObserver<T>? = null): Observable<T> {
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