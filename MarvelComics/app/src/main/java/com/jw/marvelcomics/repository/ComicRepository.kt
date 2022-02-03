package com.jw.marvelcomics.repository

import com.jw.marvelcomics.repository.api.MarvelAPI
import com.jw.marvelcomics.repository.api.model.ComicResponse
import com.jw.marvelcomics.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias ComicDataState = DataState<ComicResponse>

interface ComicRepository {
    fun getComic(id: Int): Flow<ComicDataState>
}

class ComicRepositoryImpl @Inject constructor(
    private val api: MarvelAPI
) : ComicRepository {

    override fun getComic(id: Int): Flow<ComicDataState> {
        return flow {
            emit(DataState.Loading)

            // need caching functionality
            emit(DataState.Success(api.getComic(id)))
        }.catch { cause: Throwable ->
            emit(DataState.Error(Exception(cause.message)))
        }
    }
}