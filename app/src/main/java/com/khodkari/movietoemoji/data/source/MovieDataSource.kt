package com.khodkari.movietoemoji.data.source

import com.khodkari.movietoemoji.data.api.OpenAIApi
import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val openAIApi: OpenAIApi
) {

    fun searchForEmoji(title: String): Flow<DataState<Movie>> = flow{
        try {
            val response = openAIApi.getEmojiForMovieTitle(title)
            val movie = Movie(title,response)
            emit(DataState.Success(movie))
        } catch (exception: Exception) {
            emit(DataState.Failure("network connection"))
        }
    }
}









