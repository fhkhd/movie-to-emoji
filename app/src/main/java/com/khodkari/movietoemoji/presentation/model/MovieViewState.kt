
package com.khodkari.movietoemoji.presentation.model

import com.khodkari.movietoemoji.domain.model.DataState

data class MovieViewState(
    var movieDataState: DataState<String>? = null,
    var movieTitle: String = "",
    var movieEmoji: String = "",
    var isLoading: Boolean = false
)