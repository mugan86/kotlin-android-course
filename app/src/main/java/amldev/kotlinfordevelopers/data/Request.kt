package amldev.kotlinfordevelopers.data

import android.util.Log
import java.net.URL

/**************************************************************************************************
 * Created by Anartz Mugika on 15/7/17.
 **************************************************************************************************/

class Request() {
    fun run(url: String) {
        val forecastJsonStr = URL(url).readText()
        Log.d (javaClass.simpleName, forecastJsonStr)
    }
}