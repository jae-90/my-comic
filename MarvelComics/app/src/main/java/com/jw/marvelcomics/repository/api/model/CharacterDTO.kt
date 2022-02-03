package com.jw.marvelcomics.repository.api.model

data class CharactersCollectionUri(
    val collectionURI: String
)

data class CharactersCollectionData(
    val data: Characters
)

data class Characters(
    val results: List<Character>
)

data class Character(
    val name: String,
    val thumbnail: Thumbnail
)