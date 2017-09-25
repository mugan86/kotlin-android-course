package amldev.kotlinfordevelopers.data.server

/**
 * Created by anartzmugika on 15/7/17.
 */

data class ForecastNextDays(val dt: Long, val temp: Temperature, val pressure: Float, val humidity: Int,
                            val weather: List<Weather>, val speed: Float, val deg: Int, val clouds: Int,
                            val rain: Float)
data class ForecastNextHours(val dt: Long, val main: Main, val weather: List<Weather>, val clouds: Clouds,
                             val wind: Wind, val sys: Sys,
                             val dt_txt: String)

//Obtener los resultados en la petición de los próximos días
data class ForecastNextDaysResult(val city: City, val list: List<ForecastNextDays>)

//Petición para las próximas horas. Será diferente los objetos de la lista ya que el resultado es diferente
// Y hay que tratarlo de diferente manera
data class ForecastNextHoursResult(val list: List<ForecastNextHours>)

data class City(val id: Long, val name: String, val coord: Coordinates, val country: String,
                val population: Int)

data class Coordinates(val lon: Float, val lat: Float)


data class Temperature(val day: Float, val min: Float, val max: Float, val night: Float,
                       val eve: Float, val morn: Float)

data class Weather(val id: Long, val main: String, val description: String, val icon: String)

data class Main(val temp: Double, val temp_min: Double, val temp_max: Double , val pressure: Double,
                val sea_level: Double, val grnd_level: Double, val humidity: Double, val temp_kf: Double)

data class Clouds(val all: Int);

data class Sys(val pod: String)

data class Wind(val speed: Double, val deg: Double)