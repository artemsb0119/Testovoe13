package com.example.testovoe13

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CuponAdapter(var matchs: List<Match>) : RecyclerView.Adapter<CuponAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(match: Match)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cupon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matchs[position]
        holder.result.text = "Match Result"
        holder.match.text = match.team1+" - "+match.team2
        holder.niz.text = "Possiveis gahnos -_-- BRL"
        holder.krest.setImageResource(R.drawable.krest_gray)
        if (match.current==1) {
            holder.koef.text = match.koef1
            holder.choose.text = match.team1
        }
        if (match.current==2) {
            holder.koef.text = match.koef2
            holder.choose.text = "X"
        }
        if (match.current==3) {
            holder.koef.text = match.koef3
            holder.choose.text = match.team2
        }

        holder.krest.setOnClickListener {
            itemClickListener?.onItemClick(match)
        }
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val result: TextView = itemView.findViewById(R.id.result)
        val match: TextView = itemView.findViewById(R.id.match)
        val krest: ImageView = itemView.findViewById(R.id.krest)
        val niz: TextView = itemView.findViewById(R.id.niz)
        val koef: TextView = itemView.findViewById(R.id.koef)
        val choose: TextView = itemView.findViewById(R.id.choose)
    }

    fun removeItem(position: Int) {
        matchs.toMutableList().apply {
            removeAt(position)
            matchs = this
        }
        notifyItemRemoved(position)
    }
}