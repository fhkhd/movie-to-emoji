package com.khodkari.movietoemoji.domain.usecase
import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.model.Movie
import com.khodkari.movietoemoji.data.repository.MovieRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetEmojiUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movie: Movie): Flow<DataState<Movie>> = channelFlow {
        repository.getMovieEmoji(movie.title).collectLatest {
            if (it is DataState.Failure) {
                trySend(it)
            } else if (it is DataState.Success) {
                val movieEmoji = it.data.emoji
                val updatedMovie = movie.copy(emoji = movieEmoji)
                trySend(DataState.Success(updatedMovie))
            }
        }
        awaitClose()
    }
}