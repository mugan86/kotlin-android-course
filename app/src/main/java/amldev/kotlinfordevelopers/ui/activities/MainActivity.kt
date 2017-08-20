package amldev.kotlinfordevelopers.ui

import amldev.i18n.LocaleHelper
import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.commands.RequestForecastCommand
import amldev.kotlinfordevelopers.ui.adapters.ForecastListAdapter
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
//Use android-extensions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //To use LocaleHelper select language
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)
        optionForecastButton.text = resources.getText(R.string.show_next_hours_forecast)
        selectLanguageFab.visibility = View.GONE
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

        selectLanguageFab.setOnClickListener {
            // toast("Comming soon to select different languages")
            LocaleHelper.languageOptionsDialog(this@MainActivity)
        }
    }



    private fun readNextHoursForecast(forecastList : RecyclerView) {
        doAsync {
            val result = RequestForecastCommand("43.1754,-2.41249", 2, -1, this@MainActivity).execute()
            uiThread {

                val adapter = ForecastListAdapter(result) { toast("${it.date} / ${it.description}") }
                forecastList.adapter = adapter
                selectLanguageFab.visibility = View.VISIBLE
            }
        }
    }

    private fun readNextDaysForecast (forecastList : RecyclerView) {
        doAsync {
            //ZIP 20590
            val result = RequestForecastCommand("43.1754,-2.41249", 1, 7, this@MainActivity).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast("${it.date} / ${it.description}") }
                forecastList.adapter = adapter
                selectLanguageFab.visibility = View.VISIBLE
            }
        }
    }


    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}