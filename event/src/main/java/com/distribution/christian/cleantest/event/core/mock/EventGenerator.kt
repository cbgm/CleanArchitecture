package com.distribution.christian.cleantest.event.core.mock

import com.distribution.christian.cleantest.event.data.model.EventDto
import kotlin.random.Random


class EventGenerator {

   companion object {

      fun generate(numberToGenerate: Int): List<EventDto> {
         val events = ArrayList<com.distribution.christian.cleantest.event.data.model.EventDto>()

         for (i in 1..numberToGenerate) {
            events.add(
                  com.distribution.christian.cleantest.event.data.model.EventDto(
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
         events.add(
               com.distribution.christian.cleantest.event.data.model.EventDto(
                     id = 123,
                     name = getRandomName(),
                     location = getRandomLocation(),
                     city = "Burgkunstadt",
                     date = getRandomDate(),
                     time = getRandomTime(),
                     price = getRandomPrice(),
                     description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."

               )
         )
         return events
      }

      private fun getRandomId() = Random.nextInt()

      private fun getRandomName() = getRandomString(
            Random.nextInt(12, 20)
      )

      /*private fun getRandomCity() = getRandomString(
            Random.nextInt(12, 20)
      )*/

      private fun getRandomLocation() =
            "${getRandomString(
                  Random.nextInt(8, 15)
            )}, ${getRandomString(
                  Random.nextInt(
                        8,
                        15
                  )
            )} ${getRandomNumber(
                  Random.nextInt(2, 3)
            )}"

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

      private fun getRandomCity(): String {
         val citys = listOf(
               "Abenberg",
               "Altdorf bei Nürnberg",
               "Alzenau",
               "Amorbach",
               "Ansbach",
               "Arnstein (Unterfranken)|Arnstein",
               "Arzberg(Oberfranken)|Arzberg",
               "Aschaffenburg",
               "Aub",
               "Bad Berneck im Fichtelgebirge|Bad Berneck",
               "Bad Brückenau",
               "Bad Kissingen",
               "Bad Königshofen im Grabfeld",
               "Bad Mergentheim",
               "Bad Neustadt an der Saale",
               "Bad Rodach",
               "Bad Salzungen",
               "Bad Staffelstein",
               "Bad Windsheim",
               "Baiersdorf",
               "Bamberg",
               "Baunach",
               "Bayreuth",
               "Beilngries",
               "Berching",
               "Betzenstein",
               "Bischofsheim an der Rhön",
               "Burgbernheim",
               "Burgkunstadt",
               "Coburg",
               "Crailsheim",
               "Creußen",
               "Dettelbach",
               "Dinkelsbühl",
               "Donauwörth",
               "Ebermannstadt",
               "Ebern",
               "Eibelstadt",
               "Eichstätt",
               "Eisfeld",
               "Ellingen",
               "Eltmann",
               "Erlangen",
               "Erlenbach am Main",
               "Feuchtwangen",
               "Fladungen",
               "Forchheim",
               "Fürth",
               "Gefrees",
               "Gemünden am Main",
               "Gerolzhofen",
               "Goldkronach",
               "Gräfenberg",
               "Greding",
               "Gundelfingen an der Donau",
               "Gunzenhausen",
               "Hallstadt",
               "Hammelburg",
               "Haßfurt",
               "Heideck",
               "Heilsbronn",
               "Helmbrechts",
               "Herrieden",
               "Hersbruck",
               "Herzogenaurach",
               "Hildburghausen",
               "Hilpoltstein",
               "Höchstadt an der Aisch",
               "Hof (Saale)|Hof",
               "Hofheim inUnterfranken",
               "Hohenberg an der Eger",
               "Hollfeld",
               "Iphofen",
               "Karlstadt",
               "Kirchenlamitz",
               "Kitzingen",
               "Klingenberg am Main",
               "Königsberg in Bayern",
               "Kronach",
               "Kulmbach",
               "Kupferberg",
               "Langenzenn",
               "Lauf an der Pegnitz",
               "Leutershausen",
               "Lichtenberg(Oberfranken)|Lichtenberg",
               "Lichtenfels (Oberfranken)|Lichtenfels",
               "Lohr am Main",
               "Ludwigsstadt",
               "Mainbernheim",
               "Marktbreit",
               "Marktheidenfeld",
               "Marktleuthen",
               "Marktredwitz",
               "Marktsteft",
               "Meiningen",
               "Mellrichstadt",
               "Merkendorf (Mittelfranken)|Merkendorf",
               "Miltenberg",
               "Münchberg",
               "Münnerstadt",
               "Naila",
               "Neustadt an der Aisch",
               "Neustadtbei Coburg",
               "Nürnberg",
               "Oberasbach",
               "Obernburg am Main",
               "Ochsenfurt",
               "Oettingen in Bayern",
               "Ornbau",
               "Ostheim vor der Rhön",
               "Öhringen",
               "Pappenheim",
               "Pegnitz (Stadt)|Pegnitz",
               "Pottenstein(Oberfranken)|Pottenstein",
               "Prichsenstadt",
               "Rehau",
               "Rieneck",
               "Rödental",
               "Röthenbach an der Pegnitz",
               "Rothenburg ob der Tauber",
               "Rothenfels",
               "Röttingen",
               "Schauenstein",
               "Scheinfeld",
               "Scheßlitz",
               "Schillingsfürst",
               "Schleusingen",
               "Schlüsselfeld",
               "Schmalkalden",
               "Schwabach",
               "Schwarzenbacham Wald",
               "Schwarzenbach an der Saale",
               "Schweinfurt",
               "Selb",
               "Selbitz(Oberfranken)|Selbitz",
               "Seßlach",
               "Sonneberg",
               "Spalt",
               "Stadtprozelten",
               "Stadtsteinach",
               "Stein (Mittelfranken)|Stein",
               "Suhl",
               "Schwäbisch Hall",
               "Tauberbischofsheim",
               "Teuschnitz",
               "Treuchtlingen",
               "Uffenheim",
               "Velden(Pegnitz)|Velden",
               "Volkach",
               "Waischenfeld",
               "Wallenfels",
               "Wassertrüdingen",
               "Wasungen",
               "Weismain",
               "Weißenburg in Bayern",
               "Weißenstadt",
               "Wertheim",
               "Windsbach",
               "Wolframs-Eschenbach",
               "Wörth am Main",
               "Wunsiedel",
               "Würzburg",
               "Zeil am Main",
               "Zirndorf"
         )

         return citys[Random.nextInt(0, citys.size - 1)]
      }

      private fun getRandomNumber(size: Int): String {
         return Random.nextInt(1, size)
               .toString()
      }
   }
}