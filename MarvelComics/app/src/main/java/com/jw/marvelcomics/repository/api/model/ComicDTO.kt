package com.jw.marvelcomics.repository.api.model

data class ComicResponse(
    val data: ComicData
)

data class ComicData(
    val results: List<ComicResults>
)

data class ComicResults(
    val title: String,
    val textObjects: List<TextObject>,
    val thumbnail: Thumbnail,
    val characters: CharactersCollectionUri
)

data class TextObject(
    val type: String,
    val language: String,
    val text: String
)