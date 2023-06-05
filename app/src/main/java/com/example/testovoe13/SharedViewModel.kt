package com.example.testovoe13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedMatches: MutableLiveData<List<Match>> = MutableLiveData()
    val selectedMatches: LiveData<List<Match>> = _selectedMatches

    fun getMatches(): LiveData<List<Match>> {
        return _selectedMatches
    }

    fun addMatch(match: Match) {
        val currentList = _selectedMatches.value?.toMutableList() ?: mutableListOf()
        currentList.add(match)
        _selectedMatches.value = currentList
    }

    fun removeMatch(match: Match) {
        val currentList = _selectedMatches.value?.toMutableList() ?: return
        currentList.remove(match)
        _selectedMatches.value = currentList
    }
}
