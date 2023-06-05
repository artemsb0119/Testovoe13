package com.example.testovoe13

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MatchDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MatchDetailAdapter

    private lateinit var koef: List<Koef>
    private lateinit var activity: Activity

    private lateinit var back: ImageView
    private lateinit var forma1: ImageView
    private lateinit var forma2: ImageView
    private lateinit var team1: TextView
    private lateinit var team2: TextView
    private lateinit var hoje: TextView
    private lateinit var time: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        forma1 = findViewById(R.id.forma1)
        forma2 = findViewById(R.id.forma2)
        team1 = findViewById(R.id.choose)
        team2 = findViewById(R.id.result)
        hoje = findViewById(R.id.hoje)
        time = findViewById(R.id.time)
        back = findViewById(R.id.back)
        imageView = findViewById(R.id.imageView)
        back.setOnClickListener {
            finish()
        }
        recyclerView = findViewById(R.id.recyclerView)
        activity = this
        Glide.with(this).load("http://135.181.248.237/13/main_pole.png").into(imageView)
        val receivedCard = intent.getSerializableExtra("cards") as? Cards
        if (receivedCard != null) {
            Glide.with(this).load(receivedCard.image1).into(forma1)
            Glide.with(this).load(receivedCard.image2).into(forma2)
            team1.text = receivedCard.team1
            team2.text = receivedCard.team2
            val parts = receivedCard.time.split("/")
            if (parts.size == 2) {
                val beforeSlash = parts[0].trim()
                val afterSlash = parts[1].trim()
                hoje.text = beforeSlash
                time.text = afterSlash
            }
        }
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = verticalLayoutManager
        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/13/koef.json").readText()
                val gson = Gson()
                koef = gson.fromJson(data, Array<Koef>::class.java).toList()

                activity.runOnUiThread {
                    adapter = MatchDetailAdapter(koef)
                    recyclerView.adapter = adapter

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}