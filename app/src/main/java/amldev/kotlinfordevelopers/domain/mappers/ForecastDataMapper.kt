package amldev.kotlinfordevelopers.domain.mappers


import amldev.kotlinfordevelopers.data.ForecastNextDays
import amldev.kotlinfordevelopers.data.ForecastNextDaysResult
import amldev.kotlinfordevelopers.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import amldev.kotlinfordevelopers.domain.model.Forecast as ModelForecast


/**
 * Created by anartzmugika on 15/7/17.
 */
class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastNextDaysResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ForecastNextDays>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertForecastItemToDomain(forecast: ForecastNextDays): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}