package com.jw.marvelcomics.viewmodel

import androidx.lifecycle.ViewModel
import com.jw.marvelcomics.repository.api.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    var charactersUrl: String = ""
    var character: Character? = null
}