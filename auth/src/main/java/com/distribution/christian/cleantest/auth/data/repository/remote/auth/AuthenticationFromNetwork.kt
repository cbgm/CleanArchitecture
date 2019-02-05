package com.distribution.christian.cleantest.auth.data.repository.remote.auth

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AuthenticationFromNetwork(
      private val auth: FirebaseAuth
) {

   suspend fun getAuthenticatedUser(): Result<User> {
      val user = auth.currentUser
      return Result.Success(
            User(
                  "",
                  "",
                  "",
                  user!!.email!!,
                  "",
                  ""
            )
      )
   }

   suspend fun registerNewUser(email: String, password: String): Result<Nothing> {
      return auth.createUserWithEmailAndPassword(email, password)
            .awaitComplete()

   }

   suspend fun loginUser(email: String, password: String): Result<User> {
      return auth.signInWithEmailAndPassword(email, password).awaitSuccess()

   }

   suspend fun resetUser(email: String): Result<Nothing> {

      val result: Result<Nothing> = auth.sendPasswordResetEmail(email).awaitComplete()

      return if (result is Result.Success) {
         Result.Complete()
      } else {
         result
      }
   }

   private suspend fun <T : Any, R : Any> Task<T>.awaitSuccess(): Result<R> = suspendCoroutine { continuation ->
      addOnCompleteListener { task ->
         when {
            task.isSuccessful -> continuation.resume(Result.Success(task.result as R))
            else -> continuation.resume(Result.Error(task.exception))
         }
      }
   }

   private suspend fun <T : Any, R : Any> Task<T>.awaitComplete(): Result<R> = suspendCoroutine { continuation ->
      addOnCompleteListener { task ->
         when {
            task.isSuccessful -> continuation.resume(Result.Complete())
            else -> continuation.resume(Result.Error(task.exception))
         }
      }
   }
}