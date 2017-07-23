package amldev.kotlinfordevelopers.domain.commands

import amldev.kotlinfordevelopers.data.ForecastRequest
import amldev.kotlinfordevelopers.domain.mappers.ForecastDataMapper
import amldev.kotlinfordevelopers.domain.model.ForecastDailyList

/**
 * Created by anartzmugika on 15/7/17.
 */
class RequestDailyForecastCommand(val zipCode: String, val type: Int = 1, val days: Int = 7) : Command<ForecastDailyList> {
    //Sobreescribimos el comando execute de la interfaz Command
    override fun execute(): ForecastDailyList {
        val forecastRequest = ForecastRequest(zipCode, type, days)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.executeNextDays())
    }
}

/*class RequestNextHoursForecastCommand(val zipCode: String, val type: Int = 1, val days: Int = 7) : Command<ForecastHoursList> {
    //Sobreescribimos el comando execute de la interfaz Command
    override fun execute(): ForecastHoursList {
        val forecastRequest = ForecastRequest(zipCode, type, days)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.executeNextDays())
    }
}*/