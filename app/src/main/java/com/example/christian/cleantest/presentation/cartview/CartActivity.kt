package com.example.christian.cleantest.presentation.cartview

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.ui.BaseActivity
import com.example.christian.cleantest.device.FragmentToolbar
import com.example.christian.cleantest.device.ToolbarLoader
import kotlinx.android.synthetic.main.activity_main.*


class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToolbarLoader(this, R.string.title_details, true)
        intent.getStringExtra("User")?.let { inlflateFragment(it) }
        loading.visibility = View.GONE

    }

    private fun inlflateFragment(userId: String){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val firstTag = "MyTag"
        fragmentTransaction.addToBackStack(firstTag)
        val myFragment = CartFragment.newInstance(userId)
        fragmentTransaction.replace(R.id.content, myFragment , firstTag)
        fragmentTransaction.commit()
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_cart
    }

    override fun toolbarBuilder(): FragmentToolbar {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
