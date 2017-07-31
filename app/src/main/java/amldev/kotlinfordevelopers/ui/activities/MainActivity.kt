package amldev.kotlinfordevelopers.ui

import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.commands.RequestForecastCommand
import amldev.kotlinfordevelopers.ui.adapters.ForecastListAdapter
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
//Use android-extensions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
        optionForecastButton.text = resources.getText(R.string.show_next_hours_forecast)
        readNextDaysForecast(forecastList)
        optionForecastButton.setOnClickListener {

            if (optionForecastButton.text == (resources.getText(R.string.show_next_hours_forecast))) {
                optionForecastButton.text = resources.getText(R.string.show_next_days_forecast)
                readNextHoursForecast(forecastList)

            } else {
                optionForecastButton.text = resources.getText(R.string.show_next_hours_forecast)
                readNextDaysForecast (forecastList)
            }
        }
    }

    private fun readNextHoursForecast(forecastList : RecyclerView) {
        doAsync {
            val result = RequestForecastCommand("43.1754,-2.41249", 2).execute()
            uiThread {

                val adapter = ForecastListAdapter(result) { toast("${it.date} / ${it.description}") }
                forecastList.adapter = adapter
            }
        }
    }

    private fun readNextDaysForecast (forecastList : RecyclerView) {
        doAsync {
            //ZIP 20590
            val result = RequestForecastCommand("43.1754,-2.41249").execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast("${it.date} / ${it.description}") }
                forecastList.adapter = adapter
            }
        }
    }


    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}