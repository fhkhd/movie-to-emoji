package com.khodkari.movietoemoji.domain.usecase

import com.khodkari.movietoemoji.data.repository.MovieRepository
import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetEmojiUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movie: String): Flow<DataState<String>> = channelFlow {
        repository.getMovieEmoji(movie).collectLatest {
            if (it is DataState.Failure) {
                trySend(it)
            } else if (it is DataState.Success) {
                trySend(DataState.Success(it.data))
            }
        }
        awaitClose()
    }
}