package com.example.zootestapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zootestapp.data.AnimalModel
import com.example.zootestapp.network.AnimalService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel
@Inject constructor(
    private val service : AnimalService
    ) : ViewModel(){

    private val _viewModelModeFlow = MutableStateFlow(Mode.Idle)
    private val _lastErrorFlow = MutableStateFlow("")
    private val _animalFlow = MutableStateFlow<AnimalModel?>(null)
    private val _animalListFlow = MutableStateFlow<MutableList<AnimalModel>?>(null)

    val viewModelMode: StateFlow<Mode> = _viewModelModeFlow
    val animal: StateFlow<AnimalModel?> = _animalFlow
    val animalList: StateFlow<List<AnimalModel>?> = _animalListFlow

    init {
        getAnimal()
        getAnimalList()
    }

    fun getAnimal() {
        viewModelScope.launch {
            runCatching {
                _viewModelModeFlow.value = Mode.Loading
                service.getRandomAnimal()
            }.onFailure { err ->
                _lastErrorFlow.value = err.message ?: "Unknown Error"
                _viewModelModeFlow.value = Mode.Error
            }.onSuccess { animal ->
                _animalFlow.value = animal
                _viewModelModeFlow.value = Mode.Idle
            }
        }
    }

    fun getAnimalList() {
        viewModelScope.launch {
            runCatching {
                _viewModelModeFlow.value = Mode.Loading
                service.getRandomListAnimal(10)
            }.onFailure { err ->
                _lastErrorFlow.value = err.message ?: "Unknown Error"
                _viewModelModeFlow.value = Mode.Error
            }.onSuccess { animalList ->
                if (_animalListFlow.value == null) _animalListFlow.value = animalList.toMutableList()
                else _animalListFlow.value?.addAll(animalList)
                _viewModelModeFlow.value = Mode.Idle
            }
        }
    }

    enum class Mode {
        Idle, Loading, Error
    }
}