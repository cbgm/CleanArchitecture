package com.example.christian.cleantest.presentation.licenseview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.example.christian.cleantest.R
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity

class LicenseAdapter(var list: List<LicenseEntity>) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        return LicenseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.license_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class LicenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val editText: EditText = itemView.findViewById(R.id.license_desc)!!
        val licenseCloseIcon: ImageView = itemView.findViewById(R.id.license_close_icon)
        val licenseEditIcon: ImageView = itemView.findViewById(R.id.license_edit_icon)
        fun bind(license: LicenseEntity) {
            license.name
        }
    }
}