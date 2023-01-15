package com.khodkari.movietoemoji.data.source

import com.khodkari.movietoemoji.data.api.OpenAIApi
import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val openAIApi: OpenAIApi
) {
    fun searchForEmoji(title: String): Flow<DataState<String>> = flow {
        try {
            val response = openAIApi.getEmojiForMovieTitle(title)
            emit(DataState.Success(response))
        } catch (exception: Exception) {
            emit(DataState.Failure("network connection"))
        }
    }
}









