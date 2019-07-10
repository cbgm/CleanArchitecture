package com.distribution.christian.cleantest.core.core.ui

import androidx.fragment.app.Fragment
import android.view.View
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.ui.SubFeatureFragment
import org.koin.android.ext.android.inject
import java.io.Serializable


@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<T, C: Any> : SubFeatureFragment() {

   val activity: T by lazy { getActivity() as T }

   protected lateinit var consistency: C

   data class TransitionInformation(val sharedElement: View, val transitionName: String): Serializable
}