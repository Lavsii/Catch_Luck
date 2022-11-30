package com.lavsii.tyl.screens.start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.lavsii.tyl.R
import com.lavsii.tyl.data.Countries
import com.lavsii.tyl.utils.APP_ACTIVITY
import kotlinx.android.synthetic.main.country_item.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class StartFragmentAdapter(
    var countries: List<Countries>

) : RecyclerView.Adapter<StartFragmentAdapter.MainHolder>() {

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onViewAttachedToWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener {
          StartFragment.click(countries[holder.bindingAdapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.country_item,
                parent,
                false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.itemView.apply {
            country_name.text = countries[position].name
            country_flag.setImageDrawable(countries[position].src?.let {
                ContextCompat.getDrawable(context, it)
            })
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}
