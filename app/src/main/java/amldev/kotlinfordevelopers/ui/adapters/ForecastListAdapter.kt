package amldev.kotlinfordevelopers.ui.adapters

import amldev.kotlinfordevelopers.R
import amldev.kotlinfordevelopers.domain.model.ForecastList
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import amldev.kotlinfordevelopers.domain.model.Forecast
import amldev.kotlinfordevelopers.ui.utils.ctx
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by anartzmugika on 15/7/17.
 */


class ForecastListAdapter(
        val weekForecast: ForecastList, val itemClick: ForecastListAdapter.OnItemClickListener)
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Bind custom item (define in item_forecast.xml)
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    class ViewHolder(view: View,
                     val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "${high}º"
                minTemperatureView.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}