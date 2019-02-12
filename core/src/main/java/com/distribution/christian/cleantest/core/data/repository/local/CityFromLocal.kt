package com.distribution.christian.cleantest.core.data.repository.local

import android.content.Context
import com.distribution.christian.cleantest.core.R
import com.distribution.christian.cleantest.core.domain.model.Result

class CityFromLocal constructor(context: Context) {

   private val suggestions = context.resources.getStringArray(R.array.city_array)

   fun getFilteredCitys(query: String): Result<Array<String>>{
      return Result.Success(suggestions.filter { it.contains(query) }.toTypedArray())
   }
}