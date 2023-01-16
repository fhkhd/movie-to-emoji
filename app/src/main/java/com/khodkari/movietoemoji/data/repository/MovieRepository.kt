package com.khodkari.movietoemoji.data.repository
import com.khodkari.movietoemoji.data.source.MovieDataSource
import com.khodkari.movietoemoji.domain.model.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val dataSource: MovieDataSource
) {
    fun getMovieEmoji(movieName: String): Flow<DataState<String>> {
        return dataSource.searchForEmoji(movieName)
    }
}

