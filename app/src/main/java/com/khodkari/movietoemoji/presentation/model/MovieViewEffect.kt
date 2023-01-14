package com.khodkari.movietoemoji.presentation.model

sealed class MovieViewEffect {
    object Idle : MovieViewEffect()
    data class ShowMovieEmoji(val emoji: String) : MovieViewEffect()
    object ShowLoading : MovieViewEffect()
    object HideLoading : MovieViewEffect()
    data class ShowTitleEmptyError(var error: String) : MovieViewEffect()
    data class ShowTitleConnectionError(var error: String) : MovieViewEffect()

}