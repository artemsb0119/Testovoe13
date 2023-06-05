package com.example.testovoe13

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(var matchs: List<Match>, val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    var onItemSelectListener: OnItemSelectListener? = null

    interface OnItemSelectListener {
        fun onItemSelected(match: Match)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matchs[position]
        holder.stavka.text = "CRIAR APOSTA"
        holder.time.text = match.time
        holder.team1.text = match.team1
        holder.team2.text = match.team2
        holder.koef1.text = match.koef1
        holder.koef2.text = match.koef2
        holder.koef3.text = match.koef3
        holder.play.setImageResource(R.drawable.play)
        holder.cons1.setBackgroundResource(R.drawable.constraint_white)
        holder.cons2.setBackgroundResource(R.drawable.constraint_white)
        holder.cons3.setBackgroundResource(R.drawable.constraint_white)

        onItemSelectListener?.let { holder.bind(it) }

        val selectedMatches = sharedViewModel.selectedMatches.value

        if (selectedMatches != null) {
            if (selectedMatches.isEmpty()) {
                holder.cons1.setBackgroundResource(R.drawable.constraint_white)
                holder.koef1.setTypeface(null, Typeface.NORMAL)
                holder.cons2.setBackgroundResource(R.drawable.constraint_white)
                holder.koef2.setTypeface(null, Typeface.NORMAL)
                holder.cons3.setBackgroundResource(R.drawable.constraint_white)
                holder.koef3.setTypeface(null, Typeface.NORMAL)
            }
        }

        if (selectedMatches != null) {
            var have_1 = false
            var have_2 = false
            var have_3 = false
            for (selectedMatch in selectedMatches) {
                if (selectedMatch.id != match.id) {
                    if (!have_1) {
                        holder.cons1.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef1.setTypeface(null, Typeface.NORMAL)
                    }
                    if (!have_2) {
                        holder.cons2.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef2.setTypeface(null, Typeface.NORMAL)
                    }
                    if (!have_3) {
                        holder.cons3.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef3.setTypeface(null, Typeface.NORMAL)
                    }
                } else {
                    if (selectedMatch.current==1) {
                        holder.cons1.setBackgroundResource(R.drawable.constraint_blue)
                        holder.koef1.setTypeface(null, Typeface.BOLD)
                        have_1 = true
                    } else {
                        /*holder.cons1.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef1.setTypeface(null, Typeface.NORMAL)*/
                    }
                    if (selectedMatch.current==2) {
                        holder.cons2.setBackgroundResource(R.drawable.constraint_blue)
                        holder.koef2.setTypeface(null, Typeface.BOLD)
                        have_2 = true
                    } else {
                        /*holder.cons2.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef2.setTypeface(null, Typeface.NORMAL)*/
                    }
                    if (selectedMatch.current==3) {
                        holder.cons3.setBackgroundResource(R.drawable.constraint_blue)
                        holder.koef3.setTypeface(null, Typeface.BOLD)
                        have_3 = true
                    } else {
                        /*holder.cons3.setBackgroundResource(R.drawable.constraint_white)
                        holder.koef3.setTypeface(null, Typeface.NORMAL)*/
                    }
                }
            }
        }
        holder.cons1.setOnClickListener {
            val currentDrawable = holder.cons1.background
            val whiteDrawableResName = "constraint_white"
            val blueDrawableResName = "constraint_blue"

            val resources = holder.itemView.context.resources
            val packageName = holder.itemView.context.packageName

            val currentDrawableResId =
                resources.getIdentifier(whiteDrawableResName, "drawable", packageName)
            val blueDrawableResId =
                resources.getIdentifier(blueDrawableResName, "drawable", packageName)

            if (currentDrawable.constantState?.equals(resources.getDrawable(currentDrawableResId)?.constantState) == true) {
                holder.cons1.setBackgroundResource(blueDrawableResId)
                holder.koef1.setTypeface(null, Typeface.BOLD)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 1
                sharedViewModel.addMatch(newMatch)
            } else if (currentDrawable.constantState?.equals(
                    resources.getDrawable(
                        blueDrawableResId
                    )?.constantState
                ) == true
            ) {
                holder.cons1.setBackgroundResource(currentDrawableResId)
                holder.koef1.setTypeface(null, Typeface.NORMAL)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 1
                sharedViewModel.removeMatch(newMatch)
            }
        }
        holder.cons2.setOnClickListener {
            val currentDrawable = holder.cons2.background
            val whiteDrawableResName = "constraint_white"
            val blueDrawableResName = "constraint_blue"

            val resources = holder.itemView.context.resources
            val packageName = holder.itemView.context.packageName

            val currentDrawableResId =
                resources.getIdentifier(whiteDrawableResName, "drawable", packageName)
            val blueDrawableResId =
                resources.getIdentifier(blueDrawableResName, "drawable", packageName)

            if (currentDrawable.constantState?.equals(resources.getDrawable(currentDrawableResId)?.constantState) == true) {
                holder.cons2.setBackgroundResource(blueDrawableResId)
                holder.koef2.setTypeface(null, Typeface.BOLD)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 2
                sharedViewModel.addMatch(newMatch)
            } else if (currentDrawable.constantState?.equals(
                    resources.getDrawable(
                        blueDrawableResId
                    )?.constantState
                ) == true
            ) {
                holder.cons2.setBackgroundResource(currentDrawableResId)
                holder.koef2.setTypeface(null, Typeface.NORMAL)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 2
                sharedViewModel.removeMatch(newMatch)
            }
        }
        holder.cons3.setOnClickListener {
            val currentDrawable = holder.cons3.background
            val whiteDrawableResName = "constraint_white"
            val blueDrawableResName = "constraint_blue"

            val resources = holder.itemView.context.resources
            val packageName = holder.itemView.context.packageName

            val currentDrawableResId =
                resources.getIdentifier(whiteDrawableResName, "drawable", packageName)
            val blueDrawableResId =
                resources.getIdentifier(blueDrawableResName, "drawable", packageName)

            if (currentDrawable.constantState?.equals(resources.getDrawable(currentDrawableResId)?.constantState) == true) {
                holder.cons3.setBackgroundResource(blueDrawableResId)
                holder.koef3.setTypeface(null, Typeface.BOLD)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 3
                sharedViewModel.addMatch(newMatch)
            } else if (currentDrawable.constantState?.equals(
                    resources.getDrawable(
                        blueDrawableResId
                    )?.constantState
                ) == true
            ) {
                holder.cons3.setBackgroundResource(currentDrawableResId)
                holder.koef3.setTypeface(null, Typeface.NORMAL)
                val newMatch = match.copy() // Создать копию объекта Match
                newMatch.current = 3
                sharedViewModel.removeMatch(newMatch)
            }
        }
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val team1: TextView = itemView.findViewById(R.id.choose)
        val play: ImageView = itemView.findViewById(R.id.play)
        val team2: TextView = itemView.findViewById(R.id.result)
        val time: TextView = itemView.findViewById(R.id.time)
        val stavka: TextView = itemView.findViewById(R.id.stavka)
        val koef1: TextView = itemView.findViewById(R.id.koef1)
        val koef2: TextView = itemView.findViewById(R.id.koef2)
        val koef3: TextView = itemView.findViewById(R.id.koef3)
        val cons1: ConstraintLayout = itemView.findViewById(R.id.cons1)
        val cons2: ConstraintLayout = itemView.findViewById(R.id.cons2)
        val cons3: ConstraintLayout = itemView.findViewById(R.id.cons3)
        fun bind(listener: OnItemSelectListener) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val match = matchs[position]
                cons1.setOnClickListener {
                    listener.onItemSelected(match)
                }
                cons2.setOnClickListener {
                    listener.onItemSelected(match)
                }
                cons3.setOnClickListener {
                    listener.onItemSelected(match)
                }
            }
        }
    }
    fun updateData() {
        notifyDataSetChanged()
    }
}