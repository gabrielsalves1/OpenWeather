package com.gawit.openweather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gawit.openweather.R
import com.gawit.openweather.model.City

class CityAdapter(private val context: Context, private val cities: List<City>):
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    var onItemClick: ((City) -> Unit)? = null

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val cityItem = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false)

        return CityViewHolder(cityItem)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.name.text = cities[position].name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(cities[position])
        }
    }

    override fun getItemCount(): Int = cities.size
}