package com.example.christian.cleantest.device.photo

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object RxPhotoBus {
    private val subscriberSubjects = PublishSubject.create<Any>()

    //get observable and subscribe anywhere
    val observables: Observable<Any>
        get() = subscriberSubjects

    //Send any object to bus i.e. emits data to subscribers
    fun sendToBus(o: Any) {
        subscriberSubjects.onNext(o)
    }
}