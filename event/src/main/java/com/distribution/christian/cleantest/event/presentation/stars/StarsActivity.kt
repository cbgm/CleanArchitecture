package com.distribution.christian.cleantest.event.presentation.stars

import android.widget.SimpleAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.distribution.christian.cleantest.core.core.ui.BaseActivity
import com.distribution.christian.cleantest.event.R
import kotlinx.android.synthetic.main.activity_stars.button2
import kotlinx.android.synthetic.main.activity_stars.recyle

class StarsActivity: BaseActivity(R.layout.activity_stars) {

   override fun onResume() {
      super.onResume()
      button2.setOnClickListener {
         this.finish()
      }

      val swipeHandler = object : SwipeCallback(this) {
         override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapter = recyle.adapter as SimpleAdapter
           // adapter.removeAt(viewHolder.adapterPosition)
         }
      }

      val itemTouchHelper = ItemTouchHelper(swipeHandler)
      itemTouchHelper.attachToRecyclerView(recyle)
   }


}

/*fun SimpleAdapter.removeAt(pos: Int){
   this.removeAt(position)
   notifyItemRemoved(position)

}*/