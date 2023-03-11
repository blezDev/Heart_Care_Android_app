package com.blez.heart_care.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blez.heart_care.data.model.HeartInput
import com.blez.heart_care.data.model.Status
import com.blez.heart_care.repository.HeartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: HeartRepository)  : ViewModel() {
    sealed class SetupEvent{
        object NoEventState : SetupEvent()
        object LoadingState : SetupEvent()
        data class HeartData(val output : Status) : SetupEvent()
        object FailState : SetupEvent()
    }

    private val _heartStatus = MutableStateFlow<SetupEvent>(SetupEvent.NoEventState)
    val heartStatus : StateFlow<SetupEvent>
    get() = _heartStatus


    fun getPrediction(data : HeartInput) = viewModelScope.launch(Dispatchers.Main) {
        _heartStatus.value = SetupEvent.LoadingState
        val result = repository.getPrediction(data)

        if(result.isSuccessful){
            _heartStatus.value = SetupEvent.HeartData(result.body()?: Status("Something went wrong!! Please Try Later"))
        }
        if (!result.isSuccessful){
            _heartStatus.value = SetupEvent.FailState
        }

    }
}