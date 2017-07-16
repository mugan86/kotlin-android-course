package amldev.kotlinfordevelopers.domain.commands

/**
 * Aquí se implementa una interfaz de los comandos necesarios para utilizarlos en "RequestForecastCommand"
 * Created by anartzmugika on 15/7/17.
 */
interface Command<out T> {
    fun execute(): T
}