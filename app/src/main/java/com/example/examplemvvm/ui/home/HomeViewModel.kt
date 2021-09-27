package com.example.examplemvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.database.repository.GuestRepository
import com.example.examplemvvm.model.Guest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GuestRepository) : ViewModel()
{
    private val _viewState: MutableLiveData<HomeViewState> = MutableLiveData(HomeViewState.ListNotLoaded)
    val viewState: LiveData<HomeViewState> = _viewState

    fun loadGuests()
    {
        _viewState.value = HomeViewState.ListLoading

        viewModelScope.launch {
            repository.getAll.collect { list ->
                _viewState.value = HomeViewState.ListLoaded(list)
            }
        }
    }
}

sealed class HomeViewState
{
    object ListNotLoaded : HomeViewState()
    object ListLoading : HomeViewState()
    object ListLoadError : HomeViewState()
    data class ListLoaded(val list: List<Guest>) : HomeViewState()
}