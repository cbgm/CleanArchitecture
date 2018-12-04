package com.example.christian.cleantest.core.domain.single

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T, in Params> {

    private var compositeDisposable: CompositeDisposable? = null

    abstract fun buildUseCaseObservable(param: Params): Single<T>

    fun execute(observer: DisposableSingleObserver<T>? = null, param: Params): Single<T> {
        dispose()
        val observable = buildUseCaseObservable(param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
        if (observer != null)
            addDisposable(observable.subscribeWith(observer))
        return observable
    }

    fun dispose() = compositeDisposable?.takeIf { !it.isDisposed }?.dispose()

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable = CompositeDisposable().apply { add(disposable) }
    }

}