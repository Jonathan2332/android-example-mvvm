package com.example.examplemvvm.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplemvvm.database.repository.GuestRepository
import com.example.examplemvvm.model.Guest
import kotlinx.coroutines.launch

class FormViewModel(private val repository: GuestRepository) : ViewModel() {

    private val _viewState: MutableLiveData<FormViewState> = MutableLiveData()
    val viewState: LiveData<FormViewState> = _viewState

    fun resetViewState() {
        _viewState.value = null
    }

    fun saveGuest(guest: Guest) {
        _viewState.value = FormViewState.SavingGuest

        viewModelScope.launch {
            val result = repository.insert(guest)
            if (result > -1) {
                _viewState.value = FormViewState.SavedGuest
            }
            else
            {
                _viewState.value = FormViewState.SaveGuestError
            }
        }
    }
}

sealed class FormViewState {
    object SavingGuest : FormViewState()
    object SavedGuest : FormViewState()
    object SaveGuestError : FormViewState()
}