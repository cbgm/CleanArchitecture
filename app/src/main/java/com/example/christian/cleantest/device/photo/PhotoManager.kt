package com.example.christian.cleantest.device.photo

import android.app.AlertDialog
import android.content.Context
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
import android.content.Intent
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotoManager @Inject constructor(val context: Context) {

    private val compositeSubscription = CompositeDisposable()
    private lateinit var callback: PhotoManagerCallback
    private val pickerItems =  ArrayList<PickerItem>()

    companion object {
        val CAMERA_RESULT_CODE: Int = 1
        val GALLERY_RELUT_CODE: Int = 2
    }

    init {
        pickerItems.add(PickerItem("Gallery", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null), 1))
        pickerItems.add(PickerItem("Camera", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null), 2))
        pickerItems.add(PickerItem("Delete", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_delete, null), 3))
    }

    fun initPicking(){
        val adapter = PickerAdapter(pickerItems)
        val pickerBuilder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = pickerItems[which].value

                    when(selected){
                        1 -> forwardToGallery()
                        2 -> forwardToCamera()
                        3 -> ""
                    }
                    dialog.dismiss()
                })
        val dialog = pickerBuilder.create()
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window.setGravity(Gravity.BOTTOM)
        dialog.show()
    }

    data class PickerItem(val desc: String, val drawable: Drawable?, val value: Int)

    private fun subscribe(){
        compositeSubscription.add( RxPhotoBus.observables
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { cropImage(it)})
    }

    private fun unsubscribe(){
        compositeSubscription.takeIf { it.isDisposed }?.apply{ dispose() }
    }

    private fun subscribeCamera(){

    }

    private fun unsubscribeCamera(){

    }

    private fun cropImage(o: Any?){
        unsubscribe()
        o?.let {
            val tempData = o as PhotoCallbackObject

            when(tempData.resultCode){
                CAMERA_RESULT_CODE -> {

                }
                GALLERY_RELUT_CODE -> {

                }
            }

            //val cropIntent = Intent("com.android.camera.action.CROP");

            //cropIntent.setDataAndType(uri, "image/*")

            //cropIntent.putExtra("crop", "true")
            //cropIntent.putExtra("outputX", 180)
            //cropIntent.putExtra("outputY", 180)
            //cropIntent.putExtra("aspectX", 3)
            //cropIntent.putExtra("aspectY", 4)
            //cropIntent.putExtra("scaleUpIfNeeded", true)
            //cropIntent.putExtra("return-data", true)

            //(context as AppCompatActivity).startActivityForResult(cropIntent, 1);
        }
    }

    private fun forwardToGallery() {
        val takeGalleryPictureIntent = Intent(Intent.ACTION_PICK)
        takeGalleryPictureIntent.type = "image/*"
        (context as AppCompatActivity).startActivityForResult(takeGalleryPictureIntent, GALLERY_RELUT_CODE)
    }

    private fun forwardToCamera() {
        subscribe()
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            (context as AppCompatActivity).startActivityForResult(takePictureIntent, CAMERA_RESULT_CODE)
        }
    }

    fun setPhotoCallback(callback: PhotoManagerCallback){
        this.callback = callback
    }

    inner class PickerAdapter(val data: ArrayList<PickerItem>): BaseAdapter() {

        private val inflater: LayoutInflater by lazy {
            LayoutInflater.from(context)
        }

        override fun getItem(position: Int) = data[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = data.size

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val holder: ItemHolder
            val view: View?

            if(convertView == null){
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

        private inner class ItemHolder(view: View?){

            val icon: ImageView? = view?.findViewById(R.id.icon)
            val desc: TextView? = view?.findViewById(R.id.desc)

            fun bind(pickerItem: PickerItem){
                icon?.setImageDrawable(pickerItem.drawable)
                desc?.text = pickerItem.desc
            }
        }
    }

    interface PhotoManagerCallback{

        fun croppedResult(image: Bitmap)
    }

}