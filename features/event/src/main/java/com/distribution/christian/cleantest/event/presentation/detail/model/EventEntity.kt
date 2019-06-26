package com.distribution.christian.cleantest.event.presentation.detail.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class EventEntity(
      val id: Int,
      val name: String,
      val city: String,
      val location: String,
      val date: String,
      val time: String,
      val price: String,
      val description: String,
      val isStarred: Boolean = false
): Serializable, Parcelable {
   constructor(parcel: Parcel) : this(
         parcel.readInt(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readString(),
         parcel.readByte() != 0.toByte()
   ) {
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeInt(id)
      parcel.writeString(name)
      parcel.writeString(city)
      parcel.writeString(location)
      parcel.writeString(date)
      parcel.writeString(time)
      parcel.writeString(price)
      parcel.writeString(description)
      parcel.writeByte(if (isStarred) 1 else 0)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<EventEntity> {
      override fun createFromParcel(parcel: Parcel): EventEntity {
         return EventEntity(parcel)
      }

      override fun newArray(size: Int): Array<EventEntity?> {
         return arrayOfNulls(size)
      }
   }
}