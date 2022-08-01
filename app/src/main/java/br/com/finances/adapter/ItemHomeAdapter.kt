package br.com.finances.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.finances.R
import br.com.finances.model.Home

class ItemHomeAdapter(private val dataSet: ArrayList<Home>, private val context: Context) :

    RecyclerView.Adapter<ItemHomeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.tvText)
            imageView = view.findViewById(R.id.icon)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_home, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].text
        viewHolder.imageView.setImageDrawable(context.getDrawable(dataSet[position].image))
    }

    override fun getItemCount() = dataSet.size
}