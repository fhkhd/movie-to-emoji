
package com.khodkari.movietoemoji.presentation.model

import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.model.Movie

data class MovieViewState(
    var movieDataState: DataState<Movie>? = null ,
    var movieTitle: String = "",
    var movieEmoji: String = "",
    var isLoading: Boolean = false
)