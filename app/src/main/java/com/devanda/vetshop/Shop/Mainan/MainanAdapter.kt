package com.devanda.vetshop.Shop.Mainan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devanda.vetshop.R

class MainanAdapter(private var data: List<Mainan>,
                     private val listener: (Mainan) -> Unit)
    : RecyclerView.Adapter<MainanAdapter.LeagueViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.shop_list, parent, false)

        return LeagueViewHolder(
            inflatedView
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.item_name)
        private val tvPrice: TextView = view.findViewById(R.id.item_price)
        private val tvImage: ImageView = view.findViewById(R.id.item_image)

        fun bindItem(data: Mainan, listener: (Mainan) -> Unit, context : Context, position : Int) {

            tvTitle.text = data.nama
            tvPrice.text = "Rp. " + data.harga
            Glide.with(context)
                .load(data.images)
                .into(tvImage);

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

}