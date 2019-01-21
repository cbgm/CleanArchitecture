package com.example.christian.cleantest.cart.presentation.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.core.ui.CartBaseFragment
import com.example.christian.cleantest.cart.presentation.detail.model.CartEntity
import com.example.christian.cleantest.core.core.util.extension.updateScope
import com.example.christian.cleantest.core.device.ToolbarLoader
import kotlinx.android.synthetic.main.fragment_cart_detail.*
import org.koin.android.ext.android.inject

class DetailFragment : CartBaseFragment(), DetailContract.View {

   companion object {

      const val TAG = "Detail"
      fun newInstance(paramId: String) =
            DetailFragment().apply {
               arguments = Bundle().apply {
                  putString("User", paramId)
               }
            }
   }

   private lateinit var paramId: String
   private val detailPresenter: DetailPresenter by inject()
   private lateinit var loading: LinearLayout
   private lateinit var content: LinearLayout
   private lateinit var priceTxt: TextView
   private lateinit var itemsTxt: TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      activity?.updateScope("detail")
      detailPresenter.setVIew(this)
      paramId = arguments?.getString("User") ?: ""
   }

   override fun onResume() {
      super.onResume()
      detailPresenter.onBind()
      detailPresenter.loadCart(paramId)
   }

   override fun onPause() {
      super.onPause()
      detailPresenter.onUnbind()
   }

   override fun getLayoutResId(): Int {
      return R.layout.fragment_cart_detail
   }

   override fun initCart(cartEntity: CartEntity) {
      priceTxt.text = cartEntity.price.toString()
      items.text = cartEntity.items.toString()
   }

   override fun showError(isVisible: Boolean) {
      //not needed
   }

   override fun showLoading(isVisible: Boolean) {
      //not needed
   }

   override fun showContent(isVisible: Boolean) {
      if (isVisible) price.visibility = View.VISIBLE else price.visibility = View.GONE
      if (isVisible) items.visibility = View.VISIBLE else items.visibility = View.GONE
   }

   override fun initViews(view: View) {
      loading = view.findViewById(R.id.loading)
      content = view.findViewById(R.id.content)
      priceTxt = view.findViewById(R.id.price)
      itemsTxt = view.findViewById(R.id.items)

      ToolbarLoader(activity as AppCompatActivity?, R.string.title_details, true)
   }
}
