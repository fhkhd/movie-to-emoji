package com.khodkari.movietoemoji.domain.usecase

import com.khodkari.movietoemoji.data.repository.MovieRepository
import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmojiUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieName: String): Flow<DataState<String>> =
        repository.getMovieEmoji(movieName)
}