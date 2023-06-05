package com.example.testovoe13

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var banner: ImageView
    private lateinit var recyclerViewCards: RecyclerView
    private lateinit var adapter: CardAdapter

    private lateinit var card: List<Cards>

    private lateinit var recyclerViewMain: RecyclerView
    private lateinit var adapter2: MatchAdapter

    private lateinit var match: List<Match>

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val first = sharedPreferences.getInt("first", 0)
        val editor = sharedPreferences.edit()
        editor.putInt("first", 1)
        editor.apply()
        val updateAdapter = sharedPreferences.getBoolean("updateAdapter", false)
        if (first>0) {
            if (updateAdapter) {
                adapter2.updateData()
                val editor = sharedPreferences.edit()
                editor.putBoolean("updateAdapter", false)
                editor.apply()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("first", 0)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        banner = view.findViewById(R.id.banner)
        Glide.with(this).load("http://135.181.248.237/13/banner.png").into(banner)
        recyclerViewCards = view.findViewById(R.id.recyclerViewCards)
        recyclerViewMain = view.findViewById(R.id.recyclerViewMain)
        val horizontalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCards.layoutManager = horizontalLayoutManager
        val verticalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewMain.layoutManager = verticalLayoutManager
        loadData()
        parentFragmentManager.addOnBackStackChangedListener {
            val currentFragment = parentFragmentManager.findFragmentById(R.id.frame_layout)
            if (currentFragment is Home) {
                currentFragment.onResume()
            }
        }
        return view
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/13/cards.json").readText()
                val gson = Gson()
                card = gson.fromJson(data, Array<Cards>::class.java).toList()
                val data2 = URL("http://135.181.248.237/13/matchs.json").readText()
                val gson2 = Gson()
                match = gson2.fromJson(data2, Array<Match>::class.java).toList()

                requireActivity().runOnUiThread {
                    adapter = CardAdapter(card)
                    adapter.setOnItemClickListener(object : CardAdapter.OnItemClickListener{

                        override fun onItemClick(cards: Cards) {
                            val intent = Intent(requireContext(), MatchDetailActivity::class.java)
                            intent.putExtra("cards", cards)
                            startActivity(intent)
                        }
                    })
                    recyclerViewCards.adapter = adapter

                    adapter2 = MatchAdapter(match,sharedViewModel)
                    recyclerViewMain.adapter = adapter2
                    adapter2.onItemSelectListener = object : MatchAdapter.OnItemSelectListener {
                        override fun onItemSelected(match: Match) {

                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}