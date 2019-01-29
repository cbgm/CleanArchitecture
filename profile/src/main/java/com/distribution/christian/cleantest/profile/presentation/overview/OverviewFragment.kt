package com.distribution.christian.cleantest.profile.presentation.overview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.distribution.christian.cleantest.core.device.ToolbarLoader
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import com.distribution.christian.cleantest.core.core.util.extension.loadResource
import com.distribution.christian.cleantest.profile.R
import com.distribution.christian.cleantest.profile.core.ui.ProfileBaseFragment
import org.koin.android.ext.android.inject


class OverviewFragment : ProfileBaseFragment(), OverviewContract.View {

   companion object {

      const val TAG = "Overview"
      fun newInstance() = OverviewFragment()
   }

   private val presenter: OverviewPresenter by inject()
   private lateinit var content: View
   private lateinit var loading: View
   private lateinit var error: View
   private lateinit var profileImageView: ImageView

   override fun getLayoutResId(): Int {
      return R.layout.fragment_profile_overview
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
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
      profileImageView = view.findViewById(R.id.profile_image)

      ToolbarLoader(
            activity as AppCompatActivity?,
            R.string.title_profile,
            false
      )
   }

   override fun showProfile() {
      profileImageView.loadResource(android.R.drawable.sym_def_app_icon)
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) content.visibility = View.VISIBLE else content.visibility = View.GONE
   }

   override fun showError(isVisible: Boolean) {
      //not used
   }

   override fun showLoading(isVisible: Boolean) {
      if (isVisible) loading.visibility = View.VISIBLE else loading.visibility = View.GONE
   }

   override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
      //inflater!!.inflate(R.menu.profile_toolbar_menu, menu)
      super.onCreateOptionsMenu(menu, inflater)
   }

   override fun onOptionsItemSelected(item: MenuItem?): Boolean {

      when(item!!.itemId) {
         //R.id.edit -> Toast.makeText(activity, "test", Toast.LENGTH_SHORT).show()
      }
      return super.onOptionsItemSelected(item)
   }

   override fun onPause() {
      super.onPause()
      presenter.onUnbind()
   }
}