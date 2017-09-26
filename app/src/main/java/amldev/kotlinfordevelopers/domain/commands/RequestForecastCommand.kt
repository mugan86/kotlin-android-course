package amldev.kotlinfordevelopers.domain.commands

import amldev.kotlinfordevelopers.data.server.ForecastRequest
import amldev.kotlinfordevelopers.domain.mappers.ForecastDataMapper
import amldev.kotlinfordevelopers.domain.model.ForecastList
import android.content.Context

/*******************************************************************
 * Created by anartzmugika on 15/7/17.
 */
class RequestForecastCommand(private val location: String, private val type: Int = 1, private val days: Int = 7, val context : Context) : Command<ForecastList> {
    //Sobreescribimos el comando execute de la interfaz Command
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(location, type, days, context)
        if(type == 2) return ForecastDataMapper().convertFromDataHoursModel(forecastRequest.executeNextHours())
        return ForecastDataMapper().convertFromDataDailyModel(forecastRequest.executeNextDays())
    }
}
