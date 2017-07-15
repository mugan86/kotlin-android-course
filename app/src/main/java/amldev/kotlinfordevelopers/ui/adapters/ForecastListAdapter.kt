package amldev.kotlinfordevelopers.ui.adapters

/**
 * Created by anartzmugika on 15/7/17.
 */


class ForecastListAdapter(val items: List<String>) :
        android.support.v7.widget.RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        return ForecastListAdapter.ViewHolder(android.widget.TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount() = items.size

    class ViewHolder(val textView: android.widget.TextView) : android.support.v7.widget.RecyclerView.ViewHolder(textView)
}