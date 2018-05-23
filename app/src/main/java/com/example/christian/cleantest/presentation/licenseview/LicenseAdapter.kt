package com.example.christian.cleantest.presentation.licenseview

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject


class LicenseAdapter @Inject constructor(
        private val context: Context,
        private val imageUtil: ImageUtil
) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {
    lateinit var list: List<LicenseEntity>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        return LicenseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.license_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun replaceData(data: List<LicenseEntity>) {
        list = data
        notifyDataSetChanged()
    }

    inner class LicenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val editText: EditText = itemView.findViewById(R.id.license_desc)
        private val licenseCloseIcon: ImageView = itemView.findViewById(R.id.license_close_icon)
        private val licenseEditIcon: ImageView = itemView.findViewById(R.id.license_edit_icon)
        val name: ImageView = itemView.findViewById(R.id.license_image)
        private var currentFileName: String = editText.text.toString()
        private lateinit var license: LicenseEntity
        fun bind(license: LicenseEntity) {
            editText.setText(license.name.replace(".jpg", ""))
            this.license = license
            //TODO Refactor
            name.setImageBitmap(BitmapFactory.decodeStream(FileInputStream(imageUtil.apply { fileName = license.name }.getLicensesPath(license.carId) + File.separator + imageUtil.fileName)))
        }

        init {

            licenseCloseIcon.setOnClickListener {
                licenseEditIcon.visibility = View.VISIBLE
                licenseCloseIcon.visibility = View.INVISIBLE
                editText.isEnabled = false
                if (editText.text.toString() != currentFileName && !imageUtil.licenseFileExists(license.carId, editText.text.toString())) {
                    println(imageUtil.fileName)
                    takeIf { imageUtil.renameLicenseFile(license.carId, editText.text.toString()) }
                            .apply { imageUtil.fileName = editText.text.toString() }
                }

            }
            licenseEditIcon.setOnClickListener {
                licenseEditIcon.visibility = View.INVISIBLE
                licenseCloseIcon.visibility = View.VISIBLE
                editText.isEnabled = true
                editText.isFocusableInTouchMode = true
                editText.requestFocus().takeIf { it }.apply {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
                }
                editText.setSelection(editText.text.length)
                currentFileName = editText.text.toString()
            }
        }
    }
}
