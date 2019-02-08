package com.distribution.christian.cleantest.core.core.ui

import androidx.fragment.app.Fragment
import android.view.View
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<T, C: Any> : Fragment() {

   val activity: T by lazy { getActivity() as T }

   protected lateinit var consistency: C

   data class TransitionInformation(val sharedElement: View, val transitionName: String) : Serializable
}