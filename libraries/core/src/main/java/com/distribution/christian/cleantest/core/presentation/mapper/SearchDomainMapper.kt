package com.distribution.christian.cleantest.core.presentation.mapper

import com.distribution.christian.cleantest.core.domain.model.Search
import com.distribution.christian.cleantest.core.presentation.model.SearchEntity


class SearchDomainMapper {

   companion object {
      fun transform(searchEntity: SearchEntity): Search {
         return Search(
               city = searchEntity.city,
               maxPrice = searchEntity.maxPrice,
               distance = searchEntity.distance,
               type = searchEntity.type,
               userId = searchEntity.userId
         )
      }
   }
}