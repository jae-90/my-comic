package com.jw.marvelcomics.repository.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CharactersCollectionUri(
    val collectionURI: String
)

data class CharactersCollectionData(
    val data: Characters
)

data class Characters(
    val results: List<Character>
)

@Parcelize
data class Character(
    val name: String,
    val thumbnail: Thumbnail
) : Parcelable