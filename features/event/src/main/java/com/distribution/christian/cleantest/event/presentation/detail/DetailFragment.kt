package com.distribution.christian.cleantest.event.presentation.detail

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.FragmentConsistency
import com.distribution.christian.cleantest.core.core.util.extension.args
import com.distribution.christian.cleantest.core.core.util.extension.argsUpdate

import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.event.presentation.detail.model.EventDetailFragmentConsistency
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject


class DetailFragment : EventBaseFragment<EventDetailFragmentConsistency>(), DetailContract.View {

   companion object {

      const val TAG = "Detail"
      fun newInstance(
            paramId: String? = null,
            transitionInformation: TransitionInformation? = null,
            event: EventEntity? = null
      ) =
            DetailFragment().args {
               putString(EventDetailFragmentConsistency.Event_ID_KEY, paramId)
               putSerializable(
                     FragmentConsistency.TRANSITION_KEY,
                     transitionInformation
               )
               putSerializable(EventDetailFragmentConsistency.EVENT_KEY, event)
            }
   }

   private val detailPresenter: DetailPresenter by inject()
   private lateinit var loading: ShimmerFrameLayout
   private lateinit var content: LinearLayout
   private lateinit var timeText: TextView
   private lateinit var cityText: TextView
   private lateinit var dateText: TextView
   private lateinit var nameText: TextView
   private lateinit var descriptionText: TextView
   private lateinit var locationText: TextView
   private lateinit var flyerImg: ImageView
   private lateinit var starBtn: FloatingActionButton

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      configureTransition()
      activity.updateScope(DiScope.EVENT_DETAIL)
      detailPresenter.setVIew(this)
   }

   override fun onAttach(context: Context?) {
      super.onAttach(context)
      var test = arguments?.get(EventDetailFragmentConsistency.Event_ID_KEY)
      consistency = EventDetailFragmentConsistency.deserializeFrom(this)
   }

   override fun onResume() {
      super.onResume()
      if (consistency.event == null) {
         detailPresenter.onBind()
         detailPresenter.loadEvent(consistency.eventId!!)
      } else {
         showEvent(consistency.event!!)
      }

   }

   override fun onPause() {
      super.onPause()
      detailPresenter.onUnbind()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_event_detail
   }

   override fun showEvent(eventEntity: EventEntity) {
      consistency.event = eventEntity
      consistency.event?.let {
         locationText.text = it.location
         timeText.text = it.time
         dateText.text = it.date
         cityText.text = it.city
         nameText.text = it.name
         descriptionText.text = it.description
         starBtn.setImageResource(
               if (eventEntity.isStarred) {
                  R.drawable.ic_star
               } else {
                  R.drawable.ic_empty_star
               }
         )
         starBtn.setOnClickListener {
            detailPresenter.updateEvent(eventEntity)
         }
      }
   }

   override fun showUpdatedEventState(event: EventEntity) {
      starBtn.setImageResource(
            if (event.isStarred) {
               R.drawable.ic_star
            } else {
               R.drawable.ic_empty_star
            }
      )
   }

   override fun showError(isVisible: Boolean) {
      //not needed
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun initViews(view: View) {
      loading = view.findViewById(R.id.loading)
      content = view.findViewById(R.id.content)
      locationText = view.findViewById(R.id.time_text)
      timeText = view.findViewById(R.id.location_text)
      cityText = view.findViewById(R.id.city_text)
      dateText = view.findViewById(R.id.date_text)
      descriptionText = view.findViewById(R.id.description_text)
      nameText = view.findViewById(R.id.name_text)
      starBtn = view.findViewById(R.id.star_btn)
      flyerImg = view.findViewById(R.id.flyer_img)
      flyerImg.transitionName = consistency.transitionInformation?.transitionName

      ToolbarLoader(activity as AppCompatActivity?, R.string.title_details, true)
   }

   private fun configureTransition() {
      sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
      enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
      exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
   }

   override fun onSaveInstanceState(outState: Bundle) {
      super.onSaveInstanceState(outState)
      argsUpdate {
         putSerializable(EventDetailFragmentConsistency.EVENT_KEY, consistency.event)
         putSerializable(FragmentConsistency.TRANSITION_KEY, consistency.transitionInformation?.transitionName)
      }
   }

   override fun onBackPressed() {
      findNavController().navigate(R.id.overviewFragment2)
   }
}
