package com.khodkari.movietoemoji.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.model.Movie
import com.khodkari.movietoemoji.domain.usecase.GetEmojiUseCase
import com.khodkari.movietoemoji.presentation.model.MovieViewEffect
import com.khodkari.movietoemoji.presentation.model.MovieViewEvent
import com.khodkari.movietoemoji.presentation.model.MovieViewState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getEmojiUseCase: GetEmojiUseCase
) : ViewModel() {

    var state: MutableStateFlow<MovieViewState> = MutableStateFlow(MovieViewState())
    var effect: MutableStateFlow<MovieViewEffect> = MutableStateFlow(MovieViewEffect.Idle)

    fun processEvent(event: MovieViewEvent) {
        when (event) {
            is MovieViewEvent.SubmitMovieTitle -> {
                viewModelScope.launch {
                    effect.value = MovieViewEffect.ShowLoading
                    state.value = state.value.copy(movieTitle = event.title)
                    val title = state.value.movieTitle
                    if (title.isNotEmpty()) {
                        val movie = Movie(title = event.title)
                        getEmojiUseCase(movie).collectLatest {
                            when (it) {
                                is DataState.Success -> {
                                    state.value = MovieViewState(it)
                                    effect.value =
                                        MovieViewEffect.ShowMovieEmoji(state.value.movieEmoji)
                                }
                                is DataState.Failure -> {
                                    effect.value =
                                        MovieViewEffect
                                            .ShowTitleConnectionError("connection failed!")
                                }
                            }
                        }
                    } else {
                        effect.value = MovieViewEffect.ShowTitleEmptyError("give me a title!")
                        state.value = MovieViewState(DataState.Failure("give me a title!"))
                    }
                }
            }
            is MovieViewEvent.UpdateMovieTitle -> {
                TODO()
            }
        }
    }
}