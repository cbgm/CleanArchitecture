package com.distribution.christian.cleantest.profile.presentation.overview

import android.animation.Animator
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.distribution.christian.cleantest.core.core.di.DiScope
import com.distribution.christian.cleantest.core.core.ui.AutoCompleteAdapter
import com.distribution.christian.cleantest.core.core.util.extension.loadResource
import com.distribution.christian.cleantest.core.core.util.extension.updateScope
import com.distribution.christian.cleantest.core.core.util.listener.AnimationEndListener
import com.distribution.christian.cleantest.core.core.util.listener.OnSeekbarChangedListener
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewEntity
import com.distribution.christian.cleantest.profile.presentation.overview.model.ProfileOverviewFragmentConsistency
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.android.ext.android.inject


class OverviewFragment : ProfileBaseFragment<ProfileOverviewFragmentConsistency>(), OverviewContract.View {

   companion object {

      const val TAG = "Overview"
   }

   private val presenter: OverviewPresenter by inject()
   private lateinit var content: View
   private lateinit var loading: ShimmerFrameLayout
   private lateinit var error: View
   private lateinit var nameText: TextView
   private lateinit var priceText: EditText
   private lateinit var cityText: AutoCompleteTextView
   private lateinit var distanceText: TextView
   private lateinit var distanceSeekbar: SeekBar
   private lateinit var passwordText: EditText
   private lateinit var emailText: EditText
   private lateinit var aliasText: TextView
   private lateinit var editBtn: ImageView
   private lateinit var profileImageView: ImageView
   private var isEditMode: Boolean = false
   private lateinit var logoutBtn: LinearLayout
   private lateinit var logoutBtnProgress: ProgressBar
   private lateinit var logoutBtnText: TextView
   private lateinit var validImage: ImageView

   override fun getLayoutResId(): Int {
      return R.layout.fragment_profile_overview
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity.updateScope(DiScope.PROFILE_OVERVIEW)
      activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
      setHasOptionsMenu(true)
      presenter.setVIew(this)
   }

   override fun onResume() {
      super.onResume()
      presenter.onBind()
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      presenter.loadProfile()
   }

   override fun initViews(view: View) {
      content = view.findViewById(R.id.content)
      loading = view.findViewById(R.id.loading)
      error = view.findViewById(R.id.error)
      priceText = view.findViewById(R.id.price_text)
      cityText = view.findViewById(R.id.city_text)
      distanceText = view.findViewById(R.id.distance_text)
      distanceSeekbar = view.findViewById(R.id.distance_seekbar)
      passwordText = view.findViewById(R.id.password_text)
      emailText = view.findViewById(R.id.email_text)
      aliasText = view.findViewById(R.id.alias_text)
      profileImageView = view.findViewById(R.id.profile_image)
      nameText = view.findViewById(R.id.name_text)
      editBtn = view.findViewById(R.id.edit_btn)
      logoutBtn = view.findViewById(R.id.logout_btn)
      logoutBtnProgress = view.findViewById(R.id.logout_loading)
      logoutBtnText = view.findViewById(R.id.logout_text)
      validImage = view.findViewById(R.id.valid_img)

      distanceSeekbar.isEnabled = false

      logoutBtn.setOnClickListener {
         presenter.logout()
      }

      val adapter = AutoCompleteAdapter(
            this.context!!,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.city_array).toCollection(
                  ArrayList()
            )
      )
      cityText.setAdapter(adapter)
      adapter.setNotifyOnChange(true)

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_profile,
            false
      )

      setListeners()
   }

   override fun showProfile(profileOverviewEntity: ProfileOverviewEntity) {
      profileImageView.loadResource(android.R.drawable.sym_def_app_icon)
      nameText.text = profileOverviewEntity.name
      aliasText.text = profileOverviewEntity.alias
      passwordText.setText(profileOverviewEntity.password)
      emailText.setText(profileOverviewEntity.email)
      cityText.setText(profileOverviewEntity.city)
      priceText.setText(profileOverviewEntity.maxPrice.toString())
      distanceText.text = profileOverviewEntity.distance.toString()
      distanceSeekbar.progress = profileOverviewEntity.distance
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun showError(isVisible: Boolean) {
      if (isVisible) error.visibility = View.VISIBLE else error.visibility = View.GONE
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
   }

   override fun showLogoutLoading(isVisible: Boolean) {
      if (isVisible) {
         logoutBtnText.visibility = View.GONE
         logoutBtnProgress.visibility = View.VISIBLE
      } else {
         logoutBtnText.visibility = View.VISIBLE
         logoutBtnProgress.visibility = View.GONE
      }
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {

      when (item.itemId) {
         //R.id.edit -> Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
      }
      return super.onOptionsItemSelected(item)
   }

   override fun showLogoutSuccess() {
      logoutBtnText.visibility = View.GONE
      validImage.apply {
         alpha = 0f
         visibility = View.VISIBLE
         animate()
               .alpha(1f)
               .setDuration(500)
               .setListener(object : AnimationEndListener() {
                  override fun onAnimationEnd(p0: Animator?) {
                     //auth listener in BaseCoordinator is doing this job
                     //activity.navigateToAuth(activity)
                  }
               })
      }

   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }

   private fun setListeners() {

      distanceSeekbar.setOnSeekBarChangeListener(object : OnSeekbarChangedListener() {

         override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            distanceText.text = String.format("%d Km", p1)
         }
      })

      editBtn.setOnClickListener {
         switchEditMode()

         if (!this.isEditMode) {
            presenter.updateProfile(
                  ProfileOverviewEntity(
                        name = nameText.text.toString(),
                        email = emailText.text.toString(),
                        password = passwordText.text.toString(),
                        birthDate = "",
                        alias = aliasText.text.toString(),
                        city = cityText.text.toString(),
                        distance = distanceSeekbar.progress,
                        type = "",
                        maxPrice = priceText.text.toString().split(" ")[0].toInt()
                  )
            )
         }
      }

      cityText.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
         //not used
      }
   }

   private fun switchEditMode() {
      this.isEditMode = !this.isEditMode
      this.isEditMode.apply {
         passwordText.isEnabled = this
         priceText.isEnabled = this
         cityText.isEnabled = this
         emailText.isEnabled = this
         distanceSeekbar.isEnabled = this
      }
   }
}