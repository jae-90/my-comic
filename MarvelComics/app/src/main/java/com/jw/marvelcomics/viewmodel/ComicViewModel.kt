package com.jw.marvelcomics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jw.marvelcomics.di.IoDispatcher
import com.jw.marvelcomics.repository.ComicDataState
import com.jw.marvelcomics.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val repository: ComicRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _comicResponse: MutableLiveData<ComicDataState> = MutableLiveData()
    val comicResponse: LiveData<ComicDataState>
        get() = _comicResponse

    fun getComicResponse(id: Int) {
        viewModelScope.launch(dispatcher) {
            repository.getComic(id)
                .onEach { dataState ->
                    _comicResponse.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}