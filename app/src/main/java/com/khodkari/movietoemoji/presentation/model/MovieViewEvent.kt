package com.khodkari.movietoemoji.presentation.model

sealed class MovieViewEvent {
    data class UpdateMovieTitle(val title: String) : MovieViewEvent()
    data class SubmitMovieTitle(val title: String) : MovieViewEvent()
}