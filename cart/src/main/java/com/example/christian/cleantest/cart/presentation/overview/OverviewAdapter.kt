package com.example.christian.cleantest.cart.presentation.overview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.christian.cleantest.cart.R
import com.example.christian.cleantest.cart.presentation.overview.model.UserEntity
import com.example.christian.cleantest.core.core.ui.recycler.InfiniteAdapter
import java.util.ArrayList

class OverviewAdapter(
      override var data: ArrayList<UserEntity>,
      private var listener: OnItemClickListener
) : InfiniteAdapter<UserEntity>(data, R.layout.shimmer_overview_item) {

   override fun onBindCustomViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

      if (holder is UserViewHolder) {
         holder.lastnameTxt.text = data[position].lastname
         holder.nameTxt.text = data[position].name
         holder.itemView.setOnClickListener { listener.onItemClick(data[position].name) }
      }
   }

   override fun onCreateCustomViewHolder(
         parent: ViewGroup,
         viewType: Int
   ): RecyclerView.ViewHolder {
      val view = layoutInflater.inflate(R.layout.list_item, parent, false)
      return UserViewHolder(view)
   }

   override fun getCustomItemId(position: Int): Long {return 1}

   interface OnItemClickListener {
      fun onItemClick(userId: String)
   }

   inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      val nameTxt: TextView = view.findViewById(R.id.user_name)
      val lastnameTxt: TextView = view.findViewById(R.id.user_lastname)
   }
}