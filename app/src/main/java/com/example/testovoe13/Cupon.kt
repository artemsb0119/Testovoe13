package com.example.testovoe13

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
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Cupon : Fragment(), CuponAdapter.OnItemClickListener {

    private lateinit var krest: ImageView

    private var param1: String? = null
    private var param2: String? = null

    private var buttonClickListener: OnButtonClickListener? = null

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: CuponAdapter

    private lateinit var match: List<Match>

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onItemClick(match: Match) {
        sharedViewModel.removeMatch(match)
        val position = adapter.matchs.indexOf(match)
        if (position != -1) {
            adapter.removeItem(position)
        }
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("updateAdapter", true)
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
        val view = inflater.inflate(R.layout.fragment_cupon, container, false)
        krest = view.findViewById(R.id.krest)
        recycler = view.findViewById(R.id.recycler)
        val verticalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = verticalLayoutManager

        val selectedMatches = sharedViewModel.selectedMatches.value

        adapter = if (selectedMatches != null && selectedMatches.isNotEmpty()) {
            CuponAdapter(selectedMatches)
        } else {
            CuponAdapter(emptyList())
        }
        recycler.adapter = adapter
        adapter.setOnItemClickListener(this)
        krest.setOnClickListener {
            requireFragmentManager().popBackStack()
            val firstFragment = parentFragmentManager.findFragmentByTag("HomeFragment")

            if (firstFragment != null && firstFragment.isHidden) {
                parentFragmentManager.beginTransaction()
                    .show(firstFragment)
                    .commit()
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cupon().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    interface OnButtonClickListener {
        fun onButtonClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            buttonClickListener = context as OnButtonClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnButtonClickListener")
        }
    }
}