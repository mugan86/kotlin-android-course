package amldev.kotlinfordevelopers.ui

import amldev.kotlinfordevelopers.ui.utils.DelegatesExt
import android.app.Application

/**
 * Created by anartzmugika on 2/8/17.
 */
class App: Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}