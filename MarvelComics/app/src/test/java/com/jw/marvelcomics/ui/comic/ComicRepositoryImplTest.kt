package com.jw.marvelcomics.ui.comic

import com.jw.marvelcomics.repository.ComicRepository
import com.jw.marvelcomics.repository.ComicRepositoryImpl
import com.jw.marvelcomics.repository.api.MarvelAPI
import com.jw.marvelcomics.repository.api.model.*
import com.jw.marvelcomics.util.Constants.SPIDER_MAN_COMIC_BOOK_ID
import com.jw.marvelcomics.util.DataState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ComicRepositoryImplTest {

    @MockK lateinit var api: MarvelAPI

    private lateinit var comicRepository: ComicRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        comicRepository = ComicRepositoryImpl(api)
    }

    @Test
    fun getComic_withValidDefaultData_returnDataSetSuccessful() = runBlocking {
        coEvery { api.getComic(any()) } returns getDefaultComicResponse()

        val comicDataState = comicRepository.getComic(SPIDER_MAN_COMIC_BOOK_ID)
            .drop(1).first()

        assertEquals((comicDataState as DataState.Success).data.data.results?.size, 3)
    }

    @Test
    fun getComic_withEmptyDefaultData_returnDataSetSuccessful() = runBlocking {
        coEvery { api.getComic(any()) } returns getDefaultEmptyResultResponse()

        val comicDataState = comicRepository.getComic(SPIDER_MAN_COMIC_BOOK_ID)
            .drop(1).first()

        assertEquals((comicDataState as DataState.Success).data.data.results.size, 0)
    }

    @Test
    fun getComic_withIOException_shouldReturnDataStateError() = runBlocking {
        coEvery { api.getComic(any()) } throws IOException("no network connection")

        val comicDataState = comicRepository.getComic(SPIDER_MAN_COMIC_BOOK_ID)
            .drop(1).first()

        Assert.assertTrue(comicDataState is DataState.Error)
    }

    private fun getDefaultEmptyResultResponse() = ComicResponse(
        ComicData(
            results = emptyList()
        )
    )

    private fun getDefaultComicResponse() = ComicResponse(
        ComicData(
            results = listOf(
                ComicResults(
                    title = "title1",
                    textObjects = listOf(),
                    thumbnail = Thumbnail("path1", "extension1"),
                    characters = CharactersCollectionUri("collectionURI1")
                ),
                ComicResults(
                    title = "title2",
                    textObjects = listOf(),
                    thumbnail = Thumbnail("path2", "extension2"),
                    characters = CharactersCollectionUri("collectionURI2")
                ),
                ComicResults(
                    title = "title3",
                    textObjects = listOf(),
                    thumbnail = Thumbnail("path3", "extension3"),
                    characters = CharactersCollectionUri("collectionURI3")
                ),
            )
        )
    )
}