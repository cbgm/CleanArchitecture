package com.distribution.christian.cleantest.profile.presentation.feedback

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment
import com.distribution.christian.cleantest.profile.presentation.feedback.model.FeedbackFragmentConsistency


class FeedbackFragment : ProfileBaseFragment<FeedbackFragmentConsistency>() {

   private lateinit var ratingBar: RatingBar
   private lateinit var resultText: TextView

   companion object {
      fun newInstance() = FeedbackFragment()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_feedback
   }

   override fun initViews(view: View) {
      ratingBar = view.findViewById(R.id.rating_bar)
      resultText = view.findViewById(R.id.result_text)

      ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
         when (rating) {
            in 0..3 -> {
               resultText.text = "Would you give us some feedback to improve the app?"
               resultText.setCompoundDrawablesRelativeWithIntrinsicBounds( null, null, null, null)
            }
            else -> {
               resultText.text = "Thank you"
               val drawable = VectorDrawableCompat.create(resources, R.drawable.ic_heart, null)
               resultText.setCompoundDrawablesRelativeWithIntrinsicBounds( null, null, drawable, null)
               resultText.compoundDrawablePadding = 5
            }
         }

      }
   }
}