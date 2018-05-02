package com.example.christian.cleantest.presentation.overview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.christian.cleantest.R
import com.example.christian.cleantest.presentation.overview.model.UserEntity

class OverviewAdapter(
        var data: ArrayList<UserEntity>,
        private var listener: OnItemClickListener
): RecyclerView.Adapter<OverviewAdapter.UserViewHolder>() {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int)  {
        holder.lastnameTxt.text = data[position].lastname
        holder.nameTxt.text = data[position].name
        holder.itemView.setOnClickListener{listener.onItemClick(data[position].name)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = data.size


    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTxt: TextView = view.findViewById(R.id.user_name)
        val lastnameTxt: TextView = view.findViewById(R.id.user_lastname)
    }

    fun replaceData(data: ArrayList<UserEntity>) {
        this.data = data
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(userId: String)
    }

}