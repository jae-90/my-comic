package com.jw.marvelcomics.repository.api.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl(): String {
        return "$path.$extension"
    }
}