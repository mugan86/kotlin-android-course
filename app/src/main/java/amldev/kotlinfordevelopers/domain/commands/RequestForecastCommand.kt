package amldev.kotlinfordevelopers.domain.commands

import amldev.kotlinfordevelopers.data.ForecastRequest
import amldev.kotlinfordevelopers.domain.mappers.ForecastDataMapper
import amldev.kotlinfordevelopers.domain.model.ForecastList

/**
 * Created by anartzmugika on 15/7/17.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}