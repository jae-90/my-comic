package com.jw.marvelcomics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jw.marvelcomics.di.IoDispatcher
import com.jw.marvelcomics.repository.CharacterListRepository
import com.jw.marvelcomics.repository.CharactersDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterListRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _charactersCollectionData: MutableLiveData<CharactersDataState> = MutableLiveData()
    val charactersCollectionData: LiveData<CharactersDataState>
        get() = _charactersCollectionData

    fun getCharactersCollectionResponse(url: String) {
        viewModelScope.launch(dispatcher) {
            repository.getCharacters(url)
                .onEach { dataState ->
                    _charactersCollectionData.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}