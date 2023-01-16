package com.khodkari.movietoemoji.data.source

import com.khodkari.movietoemoji.data.api.OpenAIApi
import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val openAIApi: OpenAIApi
) {
    fun searchForEmoji(movieName: String): Flow<DataState<String>> = flow {
        try {
            emitAll(openAIApi.getEmojiForMovieTitle(movieName))
        } catch (exception: Exception) {
            emit(DataState.Failure("network connection"))
        }
    }
}









