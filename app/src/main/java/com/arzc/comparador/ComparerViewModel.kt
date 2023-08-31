package com.arzc.comparador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ComparerViewModel : ViewModel() {
    val comparer: LiveData<Comparer> get() = _comparer
    private var _comparer = MutableLiveData(Comparer(true))

    fun compareStrings(string1: String, string2: String) {
        viewModelScope.launch {
            _comparer.value = Comparer(string1 == string2)
        }
    }
}