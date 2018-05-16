package com.example.christian.cleantest.device.photo

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.christian.cleantest.R
import com.example.christian.cleantest.core.util.ImageUtil
import javax.inject.Inject

class PhotoManager @Inject constructor(
        private val context: Activity,
        private val imageUtil: ImageUtil,
        private val photoCommandResolver: PhotoCommandResolver
) {

    private val photoOptionItems = ArrayList<PhotoOptionItem>()


    companion object {
        const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE: Int = 0
        const val CAMERA_RESULT_CODE: Int = 1
        const val GALLERY_RESULT_CODE: Int = 2
        const val DELETE_RESULT_CODE: Int = 3
        const val CROP_RESULT_CODE: Int = 4
    }

    fun showPhotoOptions() {
        initPhotoOptions()
        val adapter = PhotoOptionAdapter(photoOptionItems)
        val pickerBuilder: AlertDialog.Builder = createPhotoOptionBuilder(adapter)
        createPhotoOptionDialog(pickerBuilder).show()
    }

    fun setPhotoManagerCallback(photoManagerCallback: PhotoManagerCallback) {
        this.photoCommandResolver.photoManagerCallback = photoManagerCallback
    }

    private fun createPhotoOptionBuilder(adapter: PhotoOptionAdapter): AlertDialog.Builder {
        return AlertDialog.Builder(context, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = photoOptionItems[which].value
                    photoCommandResolver.executeCommand(selected)
                    dialog.dismiss()
                })
    }

    private fun initPhotoOptions() {
        photoOptionItems.clear()
        photoOptionItems.add(PhotoOptionItem("Foto aufnehmen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_photo_camera_black_24dp, null), CAMERA_RESULT_CODE))
        photoOptionItems.add(PhotoOptionItem("Foto hochladen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_photo_library_black_24dp, null), GALLERY_RESULT_CODE))

        if (imageUtil.isImagePresent()) {
            photoOptionItems.add(PhotoOptionItem("Foto löschen", ResourcesCompat.getDrawable(context.resources, R.drawable.ic_delete_black_24dp, null), DELETE_RESULT_CODE))
        }
    }

    private fun createPhotoOptionDialog(pickerBuilder: AlertDialog.Builder): AlertDialog {
        return pickerBuilder.create().apply {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            window.setGravity(Gravity.BOTTOM)
        }
    }

    data class PhotoOptionItem(val desc: String, val drawable: Drawable?, val value: Int)


    fun loadPhoto() = imageUtil.loadImage()

    fun savePhoto(bitmap: Bitmap) = imageUtil.saveBitmapAsImage(bitmap)

    inner class PhotoOptionAdapter(val data: ArrayList<PhotoOptionItem>) : BaseAdapter() {

        private val inflater: LayoutInflater by lazy {
            LayoutInflater.from(context)
        }

        override fun getItem(position: Int) = data[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = data.size

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val holder: ItemHolder
            val view: View?

            if (convertView == null) {
                view = inflater.inflate(R.layout.photopicker_item, parent, false)
                holder = ItemHolder(view)
                view.tag = holder
            } else {
                view = convertView
                holder = convertView.tag as ItemHolder
            }
            holder.bind(data[position])

            return view
        }

        private inner class ItemHolder(view: View?) {

            val icon: ImageView? = view?.findViewById(R.id.icon)
            val desc: TextView? = view?.findViewById(R.id.desc)

            fun bind(photoOptionItem: PhotoOptionItem) {
                icon?.setImageDrawable(photoOptionItem.drawable)
                desc?.text = photoOptionItem.desc
            }
        }
    }

    interface PhotoManagerCallback {
        fun imageReady()
    }

    fun cameraPermissionsGranted() {
        photoCommandResolver.executeCommand(CAMERA_RESULT_CODE)
    }
}
