package com.example.testovoe13

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MatchDetailAdapter(val koefs: List<Koef>) :
    RecyclerView.Adapter<MatchDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.match_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val koef = koefs[position]
        holder.goals.text = koef.total
        holder.bolshe.text = koef.bolshe
        holder.menshe.text = koef.menshe
    }

    override fun getItemCount(): Int {
        return koefs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val goals: TextView = itemView.findViewById(R.id.goals)
        val bolshe: TextView = itemView.findViewById(R.id.bolshe)
        val menshe: TextView = itemView.findViewById(R.id.menshe)
    }
}