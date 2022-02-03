package com.jw.marvelcomics.repository

import com.jw.marvelcomics.repository.api.MarvelAPI
import com.jw.marvelcomics.repository.api.model.CharactersCollectionData
import com.jw.marvelcomics.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias CharactersDataState = DataState<CharactersCollectionData>

interface CharacterListRepository {
    fun getCharacters(url: String): Flow<CharactersDataState>
}

class CharacterListRepositoryImpl @Inject constructor(
    private val api: MarvelAPI
) : CharacterListRepository {

    override fun getCharacters(url: String): Flow<CharactersDataState> {
        return flow {
            emit(DataState.Loading)

            // need caching functionality
            emit(DataState.Success(api.getCharacters(url)))
        }.catch { cause: Throwable ->
            emit(DataState.Error(Exception(cause.message)))
        }
    }
}
