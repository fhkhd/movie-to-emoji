package com.khodkari.movietoemoji.presentation.model

sealed class MovieViewEffect {
    object Idle : MovieViewEffect()
    data class ShowTitleEmptyError(var error: String) : MovieViewEffect()
    data class ShowTitleConnectionError(var error: String) : MovieViewEffect()
}