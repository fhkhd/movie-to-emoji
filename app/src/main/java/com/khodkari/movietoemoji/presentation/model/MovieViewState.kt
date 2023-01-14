
package com.khodkari.movietoemoji.presentation.model

import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.model.Movie

data class MovieViewState(
    val movieDataState: DataState<Movie>? = null ,
    val movieTitle: String = "",
    val movieEmoji: String = "",
    val isLoading: Boolean = false
)