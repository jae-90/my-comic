package com.jw.marvelcomics.repository.api

import com.jw.marvelcomics.repository.api.model.CharactersCollectionData
import com.jw.marvelcomics.repository.api.model.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelAPI {

    @GET(COMICS_URL)
    suspend fun getComic(
        @Query("id") comicId: Int
    ): ComicResponse

    @GET
    suspend fun getCharacters(
        @Url url: String)
    : CharactersCollectionData

    companion object{
        const val BASE_URL = "https://gateway.marvel.com/"
        const val COMICS_URL = "/v1/public/comics"
    }
}
