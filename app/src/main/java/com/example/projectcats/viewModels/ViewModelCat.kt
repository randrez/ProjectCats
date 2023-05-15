package com.example.projectcats.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projectcats.data.resource.Resource
import com.example.projectcats.domain.models.Cat
import com.example.projectcats.domain.useCase.CatsUseCase
import com.example.projectcats.viewModels.states.CatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelCat @Inject constructor(
    private val catsUseCase: CatsUseCase
) : ViewModel() {

    private val _state: MutableState<CatState> = mutableStateOf(CatState())
    val state: State<CatState> = _state

    val cats: MutableList<Cat> = mutableStateListOf()

    init {
        val state = state.value
        catsUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = state.copy(loading = true)
                }

                is Resource.Error -> {
                    _state.value = state.copy(loading = false)
                }

                is Resource.Success -> {
                    _state.value = state.copy(loading = false)
                    result.data?.let { dataCats ->
                        cats.addAll(dataCats)
                    }
                }
            }
        }
    }
}