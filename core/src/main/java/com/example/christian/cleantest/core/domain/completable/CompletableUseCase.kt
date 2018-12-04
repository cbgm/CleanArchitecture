package com.example.christian.cleantest.core.domain.completable

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<T, in Params> {

   private var compositeDisposable: CompositeDisposable? = null

   abstract fun buildUseCaseObservable(param: Params): Completable

   fun execute(observer: DisposableCompletableObserver? = null, param: Params): Completable {
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