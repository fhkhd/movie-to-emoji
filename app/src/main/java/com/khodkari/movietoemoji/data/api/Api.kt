package com.khodkari.movietoemoji.data.api

interface Api {
    suspend fun getEmojiForMovieTitle(text: String): String
}