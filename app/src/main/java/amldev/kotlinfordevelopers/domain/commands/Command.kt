package amldev.kotlinfordevelopers.domain.commands

/**
 * Created by anartzmugika on 15/7/17.
 */
interface Command<out T> {
    fun execute(): T
}