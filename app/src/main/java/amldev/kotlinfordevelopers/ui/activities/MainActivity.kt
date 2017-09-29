package amldev.kotlinfordevelopers.ui.activities

import amldev.i18n.LocaleHelper
import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.commands.RequestForecastCommand
import amldev.kotlinfordevelopers.ui.adapters.ForecastListAdapter
import android.content.Context
import android.os.Bundle
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

        val data = getIntentExtras()

        forecastList.layoutManager = LinearLayoutManager(this)
        optionForecastButton.text = resources.getText(R.string.show_next_hours_forecast)
        selectLanguageFab.visibility = View.GONE
        readNextDaysForecast(forecastList, data)
        optionForecastButton.setOnClickListener {

            if (optionForecastButton.text == (resources.getText(R.string.show_next_hours_forecast))) {
                optionForecastButton.text = resources.getText(R.string.show_next_days_forecast)
                readNextHoursForecast(forecastList, data)
            } else {
                optionForecastButton.text = resources.getText(R.string.show_next_hours_forecast)
                readNextDaysForecast (forecastList, data)
            }
        }

        selectLanguageFab.setOnClickListener {
            // toast("Comming soon to select different languages")
            LocaleHelper.languageOptionsDialog(this@MainActivity)
        }
    }

    private fun readNextHoursForecast(forecastList : RecyclerView, data: Array<String>) {
        doAsync {
            val result = RequestForecastCommand("${data[0]},${data[1]}", 2, -1, this@MainActivity).execute()
            uiThread {

                val adapter = ForecastListAdapter(result) { toast("${it.date} / ${it.description}") }
                forecastList.adapter = adapter
                selectLanguageFab.visibility = View.VISIBLE
            }
        }
    }

    private fun readNextDaysForecast (forecastList : RecyclerView, data: Array<String>) {
        doAsync {
            //ZIP 20590
            val result = RequestForecastCommand("${data[0]},${data[1]}", 1, 7, this@MainActivity).execute()
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

    private fun getIntentExtras(): Array<String> {
        val data: Array<String> = arrayOf("", "", "")
        data.set( 0,intent.getStringExtra("lat") ?: "43.1754" )
        data.set( 1, intent.getStringExtra("lng")?: "-2.41249" )
        return data
    }
}