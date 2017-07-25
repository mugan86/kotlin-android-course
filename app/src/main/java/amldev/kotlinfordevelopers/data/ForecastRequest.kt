package amldev.kotlinfordevelopers.data

import com.google.gson.Gson
import java.net.URL

/***********************************************************************************************************************
 * Created by Anartz Mugika on 15/7/17.
 * ---------------------------------------------------------------------------------------------------------------------
 * Estas clases servirán para obtener la información del servidor y añadirla directamente mediante la librería de GSON.
 * Después dentro de ForecastDataMapper se gestionará para modelarlo a nuestro gusto con las funciones correspondientes
 * y la definición que hemos realizado dentro del paquete "model" donde encontramos el fichero "DomainClasses" que es
 * donde se define la forma que tendrá la información al final del proceso de la petición
 ***********************************************************************************************************************/

class ForecastRequest(val location: String, val type: Int = 1, val days: Int = 7) {

    companion object {

        @JvmField
        val OPEN_WEATHER_MAP_API_IMG = "http://openweathermap.org/img/w/";
        private val URL_LOCALHOST = "http://api.openweathermap.org/data/2.5/forecast";
        val OPEN_WEATHERMAP_API_KEY = "&APPID=58dda22dc16b4ec458a95a0a7f2e921d";

        fun getNextDaysWithZipCodeForecastURL(zipCode: String, days: Int = 7, language: String = "en"): String {
            return "$URL_LOCALHOST/daily?&mode=json&units=metric&lang=$language&cnt=$days$OPEN_WEATHERMAP_API_KEY&q=$zipCode"
        }

        fun getNextDaysForecastURL(lat: String, lng: String, days: Int = 7, language: String = "en"): String {
            return "$URL_LOCALHOST/daily?lat=$lat&lon=$lng&mode=json&units=metric&lang=$language&cnt=$days$OPEN_WEATHERMAP_API_KEY"
        }

        fun getNextHoursForecastURL(lat: String, lng: String, language: String = "en"): String {
            return "$URL_LOCALHOST?lat=$lat&lon=$lng&mode=json&units=metric&lang=$language$OPEN_WEATHERMAP_API_KEY"
        }
    }

    fun executeNextDays(): ForecastNextDaysResult {
        //Obtain forecastJSONStr and pass to convert in ForecastResult
        return Gson().fromJson(URL(this.getUseUrl()).readText(), ForecastNextDaysResult::class.java)
    }

    fun executeNextHours(): ForecastNextHoursResult {
        //Obtain forecastJSONStr and pass to convert in ForecastResult
        return Gson().fromJson(URL(this.getUseUrl()).readText(), ForecastNextHoursResult::class.java)
    }

    // Create Use URL in request depending type, zipcode and other properties
    private fun getUseUrl() : String {

        // TODO Manage location
        var locationFindCoordinates : Boolean = false
        var list : List<String> = listOf()
        if (location.indexOf(',') != -1) { // Location coordinates
            list = location.split(",".toRegex()).filter { true }
            locationFindCoordinates = true
        }
        println(list)
        if (1 == type) { //Next days
            if (!locationFindCoordinates) return getNextDaysWithZipCodeForecastURL(location, days, "en") else return getNextDaysForecastURL("33", "33", days, "en")
        }
        return getNextHoursForecastURL("43.174778", "-2.411722", "en")
    }

}