package com.example.christian.cleantest.cart.presentation.cartview

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.presentation.cartview.model.CartEntity
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.android.ext.android.inject

class CartFragment : Fragment(), CartContract.View {

    private lateinit var paramId: String
    private val cartPresenter: CartPresenter by inject()
    lateinit var rootView: View
    private val priceTxt: TextView by lazy {
        rootView.findViewById<TextView>(R.id.price)
    }

    val itemsTxt: TextView by lazy {
        rootView.findViewById<TextView>(R.id.items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getString("User")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        cartPresenter.setVIew(this)
        rootView = inflater.inflate(R.layout.fragment_cart, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        cartPresenter.onBind()
        cartPresenter.loadCart(paramId)
    }

    override fun onPause() {
        super.onPause()
        cartPresenter.onUnbind()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun initCart(cartEntity: CartEntity) {
        priceTxt.text = cartEntity.price.toString()
        items.text = cartEntity.items.toString()
    }

    override fun showError(visible: Boolean) {
    }

    override fun showLoading(visible: Boolean) {

    }

    override fun showContent(visible: Boolean) {
        if (visible) price.visibility = View.VISIBLE else price.visibility = View.GONE
        if (visible) items.visibility = View.VISIBLE else items.visibility = View.GONE
    }


    companion object {

        @JvmStatic
        fun newInstance(paramId: String) =
                CartFragment().apply {
                    arguments = Bundle().apply {
                        putString("User", paramId)
                    }
                }
    }
}
