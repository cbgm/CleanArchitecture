package com.distribution.christian.cleantest.event.presentation.detail

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.distribution.christian.cleantest.core.core.di.DiScope

import com.distribution.christian.cleantest.event.R
import com.distribution.christian.cleantest.event.core.ui.EventBaseFragment
import com.distribution.christian.cleantest.event.presentation.detail.model.EventEntity
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import org.koin.android.ext.android.inject


class DetailFragment : EventBaseFragment(), DetailContract.View {

   companion object {

      const val TAG = "Detail"
      fun newInstance(paramId: String, transitionInformation: TransitionInformation? = null) =
            DetailFragment().apply {
               arguments = Bundle().apply {
                  putString("User", paramId)
                  putString(
                        "transitionName",
                        transitionInformation?.transitionName
                  )
               }
            }
   }

   private val detailPresenter: DetailPresenter by inject()
   private lateinit var eventId: String
   private lateinit var loading: LinearLayout
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
      postponeEnterTransition()
      sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.default_transition)
      enterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.no_transition)
      activity.updateScope(DiScope.EVENT_DETAIL)
      detailPresenter.setVIew(this)
      eventId = arguments?.getString("User") ?: ""
      transitionName = arguments?.getString("transitionName", "")!!
   }


   override fun onResume() {
      super.onResume()
      detailPresenter.onBind()
      detailPresenter.loadEvent(eventId)
   }

   override fun onPause() {
      super.onPause()
      detailPresenter.onUnbind()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_event_detail
   }

   override fun showEvent(eventEntity: EventEntity) {
      startPostponedEnterTransition()
      locationText.text = eventEntity.location
      timeText.text = eventEntity.time
      dateText.text = eventEntity.date
      cityText.text = eventEntity.city
      nameText.text = eventEntity.name
      descriptionText.text = eventEntity.description
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
      //not needed
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) locationText.visibility = View.VISIBLE else locationText.visibility = View.GONE
      if (isVisible) timeText.visibility = View.VISIBLE else timeText.visibility = View.GONE
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
      flyerImg.transitionName = transitionName

      ToolbarLoader(activity as AppCompatActivity?, R.string.title_details, true)
   }
}
