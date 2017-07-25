package amldev.kotlinfordevelopers.ui

import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.commands.RequestForecastCommand
import amldev.kotlinfordevelopers.ui.adapters.ForecastListAdapter
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = find(R.id.optionForecastButton)
        val forecastList : RecyclerView = find(R.id.forecast_list) //Using Anko

        forecastList.layoutManager = LinearLayoutManager(this)
        button.text = "Show next hours forecast"
        readNextDaysForecast(forecastList)
        button.setOnClickListener {

            if (button.text.equals("Show next hours forecast")) {
                button.text = "Show next days forecast"
                readNextHoursForecast(forecastList)

            } else {
                button.text = "Show next hours forecast"
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