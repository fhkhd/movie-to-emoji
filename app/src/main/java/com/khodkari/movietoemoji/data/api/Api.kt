package com.khodkari.movietoemoji.data.api

import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.flow.Flow

interface Api {
    fun getEmojiForMovieTitle(text: String): Flow<DataState<String>>
}