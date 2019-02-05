package com.distribution.christian.cleantest.core.core.ui

import android.widget.LinearLayout
import android.content.Context
import android.widget.TextView
import android.support.annotation.StyleableRes
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.distribution.christian.cleantest.core.R


class LoadingTextButton(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

   @StyleableRes
   internal var index0 = 0

   @StyleableRes
   internal var index1 = 0

   lateinit var loading: ProgressBar
   var textColor: Int = android.R.color.black
   lateinit var text: TextView

   init {
      init(context, attrs)
   }

   private fun init(context: Context, attrs: AttributeSet) {
      View.inflate(context, R.layout.loading_button, this)
      isClickable = true
      val sets = intArrayOf(R.attr.text, R.attr.textColor)
      val typedArray = context.obtainStyledAttributes(attrs, sets)
      val text = typedArray.getText(index0)
      val textColor = typedArray.getResourceId(index1, textColor)
      typedArray.recycle()

      initComponents()

      this.text.text = text.toString()
      this.text.setTextColor(context.getColor(textColor))
   }

   private fun initComponents() {
      text = findViewById(R.id.button_text)
      loading = findViewById(R.id.button_loading)
   }

   fun startLoading() {
      loading.visibility = View.VISIBLE
      text.visibility = View.GONE
   }

   fun stopLoading() {
      loading.visibility = View.GONE
      text.visibility = View.VISIBLE
   }
}