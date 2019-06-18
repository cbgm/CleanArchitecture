package com.distribution.christian.cleantest.core.core.util.listener

import io.reactivex.subjects.BehaviorSubject


class ObserableVariable<T>(defaultValue: T) {
   var value: T = defaultValue
      set(value) {
         field = value
         observable.onNext(value)
      }
   val observable = BehaviorSubject.createDefault(value)

}