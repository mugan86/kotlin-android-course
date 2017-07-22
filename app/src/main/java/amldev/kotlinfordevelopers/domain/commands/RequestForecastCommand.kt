package amldev.kotlinfordevelopers.domain.commands

import amldev.kotlinfordevelopers.data.ForecastRequest
import amldev.kotlinfordevelopers.domain.mappers.ForecastDataMapper
import amldev.kotlinfordevelopers.domain.model.ForecastList

/**
 * Created by anartzmugika on 15/7/17.
 */
class RequestForecastCommand(val zipCode: String, val type: Int = 1, val days: Int = 7) : Command<ForecastList> {
    override fun executeNextHours(): ForecastList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //Sobreescribimos el comando execute de la interfaz Command
    override fun executeNextDays(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode, type, days)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.executeNextDays())
    }
}