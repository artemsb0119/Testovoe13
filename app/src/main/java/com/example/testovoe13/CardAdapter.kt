package com.example.testovoe13

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CardAdapter (val cards: List<Cards>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(cards : Cards)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.liga.text = card.liga
        holder.time.text = card.time
        holder.first_team.text = card.team1
        holder.second_team.text = card.team2
        holder.stadion.text = card.stadium
        holder.koef1.text = card.koef1
        holder.koef2.text = card.koef2
        holder.koef3.text = card.koef3
        Glide.with(holder.itemView.context).load(card.image1).into(holder.imageFirst)
        Glide.with(holder.itemView.context).load(card.image2).into(holder.imageSecond)
        holder.ball.setImageResource(R.drawable.small_ball)
        holder.play.setImageResource(R.drawable.play)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ball: ImageView = itemView.findViewById(R.id.ball)
        val liga: TextView = itemView.findViewById(R.id.liga)
        val play: ImageView = itemView.findViewById(R.id.play)
        val time: TextView = itemView.findViewById(R.id.time)
        val imageFirst: ImageView = itemView.findViewById(R.id.imageFirst)
        val imageSecond: ImageView = itemView.findViewById(R.id.imageSecond)
        val first_team: TextView = itemView.findViewById(R.id.first_team)
        val second_team: TextView = itemView.findViewById(R.id.second_team)
        val stadion: TextView = itemView.findViewById(R.id.stadion)
        val koef1: TextView = itemView.findViewById(R.id.koef1)
        val koef2: TextView = itemView.findViewById(R.id.koef2)
        val koef3: TextView = itemView.findViewById(R.id.koef3)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val card = cards[position]
                    onItemClickListener?.onItemClick(card)
                }
            }
        }
    }
}