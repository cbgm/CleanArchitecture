package com.distribution.christian.cleantest.core.data.repository.remote

import com.distribution.christian.cleantest.core.domain.model.Result
import com.distribution.christian.cleantest.core.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@Suppress("UNCHECKED_CAST", "RedundantSuspendModifier")
class AuthenticationFromNetwork(
      private val auth: FirebaseAuth
) {

   suspend fun getAuthenticatedUser(): Result<User> {
      val user = auth.currentUser

      return if (user != null) {
         Result.Success(
               User(
                     "",
                     "",
                     "",
                     user.email!!,
                     "",
                     ""
               )
         )
      } else {
         Result.Error(Exception())
      }
   }

   suspend fun registerNewUser(email: String, password: String): Result<Nothing> {
      return auth.createUserWithEmailAndPassword(email, password)
            .awaitComplete()
   }

   suspend fun loginUser(email: String, password: String): Result<User> {
      return auth.signInWithEmailAndPassword(email, password)
            .awaitSuccess()
   }

   suspend fun resetUser(email: String): Result<Nothing> {

      val result: Result<Nothing> = auth.sendPasswordResetEmail(email)
            .awaitComplete()

      return if (result is Result.Success) {
         Result.Complete()
      } else {
         result
      }
   }

   suspend fun logoutUser(): Result<Nothing>{
      auth.signOut()
      return Result.Complete()
   }

   private suspend fun <T : Any, R : Any> Task<T>.awaitSuccess(): Result<R> = suspendCoroutine { continuation ->
      addOnCompleteListener { task ->
         when {
            task.isSuccessful -> continuation.resume(
                  Result.Success(User("", "", "", (task.result as AuthResult).user.email!!, "", "") as R))
                  else
            -> continuation.resume(Result.Error(task.exception))
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