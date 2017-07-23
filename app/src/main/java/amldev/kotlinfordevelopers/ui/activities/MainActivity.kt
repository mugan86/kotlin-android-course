package amldev.kotlinfordevelopers.ui

import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.commands.RequestDailyForecastCommand
import amldev.kotlinfordevelopers.domain.model.Forecast
import amldev.kotlinfordevelopers.ui.adapters.DailyForecastListAdapter
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList : RecyclerView = find(R.id.forecast_list) //Using Anko
        forecastList.layoutManager = LinearLayoutManager(this)
        // TODO check this warning: https://developer.android.com/training/articles/security-config.html?hl=es-419
        doAsync {
            val result = RequestDailyForecastCommand("20590").execute()
            uiThread {
                forecastList.adapter = DailyForecastListAdapter(result,
                        object : DailyForecastListAdapter.OnItemClickListener {
                            override fun invoke(forecast: Forecast) {
                                toast("${forecast.date} / ${forecast.iconUrl}")
                            }
                        })
            }
        }
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}