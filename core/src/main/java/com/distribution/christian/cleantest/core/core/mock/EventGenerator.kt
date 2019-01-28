package com.distribution.christian.cleantest.core.core.mock

import com.distribution.christian.cleantest.core.data.model.EventDto
import kotlin.random.Random

class EventGenerator {

   companion object {

      fun generate(numberToGenerate: Int): List<EventDto> {
         val events = ArrayList<EventDto>()

         for (i in 1..numberToGenerate) {
            events.add(
                  EventDto(
                        id = getRandomId(),
                        name = getRandomName(),
                        location = getRandomLocation(),
                        city = getRandomCity(),
                        date = getRandomDate(),
                        time = getRandomTime(),
                        price = getRandomPrice(),
                        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."

                  )
            )
         }
         return events
      }

      private fun getRandomId() = Random.nextInt()

      private fun getRandomName() = getRandomString(
            Random.nextInt(12, 20)
      )

      private fun getRandomCity() = getRandomString(
            Random.nextInt(12, 20)
      )

      private fun getRandomLocation() =
            "${getRandomString(Random.nextInt(8, 15))}, ${getRandomString(
                  Random.nextInt(
                        8,
                        15
                  )
            )} ${getRandomNumber(Random.nextInt(2, 3))}"

      private fun getRandomDate(): String {
         val weekdays = listOf(
               "Monday",
               "Tuesday",
               "Wednesday",
               "Thursday",
               "Friday",
               "Saturday",
               "Sunday"
         )
         val day: String = Random.nextInt(1, 31)
               .toString()
         val month: String = Random.nextInt(1, 12)
               .toString()
         val year: String = "2019"
         val weekday: String = weekdays[Random.nextInt(0, weekdays.size - 1)]

         return "$weekday $day.$month.$year"
      }

      private fun getRandomTime(): String {
         val from: String = Random.nextInt(1, 12)
               .toString()
         val to: String = Random.nextInt(1, 12)
               .toString()

         return "$from to $to pm"
      }

      private fun getRandomPrice(): String {
         return "${Random.nextInt(5, 10)} Euro"
      }

      private fun getRandomString(size: Int): String {
         val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
         return (1..size)
               .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
               .map(charPool::get)
               .joinToString("")
      }

      private fun getRandomNumber(size: Int): String {
         return Random.nextInt(1, size)
               .toString()
      }
   }
}