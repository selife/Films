package com.example.artgallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artgallery.data.models.Movies
import com.example.artgallery.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    private val _allArts = MutableLiveData<List<Movies>>()
    val allArts: LiveData<List<Movies>>
        get() = _allArts

    fun getAllArts() {
        viewModelScope.launch {
            repository.getAllArts().let {
                if (it.isSuccessful) {
                    _allArts.postValue(it.body())
                }
                else {
                    Log.d("checkData", "Failed to load arts: ${it.errorBody()} ${it.code()}")
                }
            }
        }
    }

}