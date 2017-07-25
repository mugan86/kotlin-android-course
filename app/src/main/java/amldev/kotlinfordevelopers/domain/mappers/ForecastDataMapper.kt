package amldev.kotlinfordevelopers.domain.mappers

import amldev.kotlinfordevelopers.data.*
import amldev.kotlinfordevelopers.domain.model.ForecastList
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import amldev.kotlinfordevelopers.domain.model.Forecast as ModelForecast


/**
 * Created by anartzmugika on 15/7/17.
 */
class ForecastDataMapper {

    fun convertFromDataDailyModel(forecast: ForecastNextDaysResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    fun convertFromDataHoursModel(forecast: ForecastNextHoursResult): ForecastList {
        return ForecastList("", "", convertForecastListHoursToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ForecastNextDays>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastListHoursToDomain(list: List<ForecastNextHours>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            convertForecastHoursItemToDomain(forecast.copy())
        }
    }

    private fun generateIconUrl(iconCode: String): String
            = ForecastRequest.OPEN_WEATHER_MAP_API_IMG + "$iconCode.png"

    private fun convertForecastItemToDomain(forecast: ForecastNextDays): ModelForecast {
        return ModelForecast(convertDate(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertForecastHoursItemToDomain(forecast: ForecastNextHours): ModelForecast {
        return ModelForecast(forecast.dt_txt, forecast.weather[0].description,
                forecast.main.temp_max.toInt(), forecast.main.temp_min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {

        var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
        return  String.format("%s",sdf.format(date)) //df.format(date)
    }
}