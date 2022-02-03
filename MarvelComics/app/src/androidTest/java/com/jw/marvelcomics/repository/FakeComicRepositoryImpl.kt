package com.jw.marvelcomics.repository

import com.jw.marvelcomics.repository.api.model.*
import com.jw.marvelcomics.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeComicRepositoryImpl @Inject constructor(): ComicRepository {

    override fun getComic(id: Int): Flow<ComicDataState> {
        return flow {
            emit(DataState.Success(ComicResponse(ComicData(
                results = listOf(
                    ComicResults(
                        title = "title1",
                        textObjects = listOf(TextObject("type1", "language1", "text1")),
                        thumbnail = Thumbnail("path1", "extension1"),
                        characters = CharactersCollectionUri("collectionURI1")
                    )
                )
            ))))
        }
    }
}