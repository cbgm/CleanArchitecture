package com.example.christian.cleantest.device

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
import javax.inject.Inject
import org.reactivestreams.Subscription



class PhotoManager @Inject constructor(val context: Context) {

    private val cameraSubscription: Subscription? = null
    private val gallerySubscription: Subscription? = null
    private lateinit var callback: PhotoManagerCallback
    private val pickerItems =  ArrayList<PickerItem>()

    init {
        pickerItems.add(PickerItem("Gallery", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null),1))
        pickerItems.add(PickerItem("Camera", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_menu_camera, null),2))
        pickerItems.add(PickerItem("Delete", ResourcesCompat.getDrawable(context.resources, android.R.drawable.ic_delete, null),3))

    }

    companion object {
        //const für callbacks

    }

    fun setPhotoCallback(callback: PhotoManagerCallback){
        this.callback = callback
    }

    fun initPicking(){
        val adapter = PickerAdapter(pickerItems)
        val pickerBuilder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.PhotopickerTheme)
                .setSingleChoiceItems(adapter, -1, { dialog, which ->
                    val selected = pickerItems[which].value
                    when(selected){
                        1 -> ""
                        2 -> ""
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


    inner class PickerAdapter(val data: ArrayList<PickerItem>): BaseAdapter() {

        private val inflater: LayoutInflater by lazy {
            LayoutInflater.from(context)
        }

        override fun getItem(position: Int) = data[position]

        override fun getItemId(position: Int) = position.toLong()

        override fun getCount() = data.size


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val holder:ItemHolder
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