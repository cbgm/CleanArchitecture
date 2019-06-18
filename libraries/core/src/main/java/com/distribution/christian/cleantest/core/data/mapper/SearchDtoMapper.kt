package com.distribution.christian.cleantest.core.data.mapper

import com.distribution.christian.cleantest.core.data.model.SearchDto
import com.distribution.christian.cleantest.core.domain.model.Search


class SearchDtoMapper {

   companion object {
      fun transform(searchDto: SearchDto): Search {
         return Search(
               city = searchDto.city,
               maxPrice = searchDto.maxPrice,
               distance = searchDto.distance,
               type = searchDto.type,
               userId = searchDto.userId
         )
      }

      fun transform(search: Search): SearchDto {
         return SearchDto(
               city = search.city,
               maxPrice = search.maxPrice,
               distance = search.distance,
               type = search.type,
               userId = search.userId
         )
      }
   }
}