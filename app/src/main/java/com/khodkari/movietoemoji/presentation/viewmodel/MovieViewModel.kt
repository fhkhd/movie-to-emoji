package com.khodkari.movietoemoji.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khodkari.movietoemoji.domain.model.DataState
import com.khodkari.movietoemoji.domain.usecase.GetEmojiUseCase
import com.khodkari.movietoemoji.presentation.model.MovieViewEffect
import com.khodkari.movietoemoji.presentation.model.MovieViewEvent
import com.khodkari.movietoemoji.presentation.model.MovieViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
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
                    val title = event.title
                    if (title.isEmpty()) {
                        effect.value = MovieViewEffect.ShowTitleEmptyError("Enter a movie name!")
                        state.value = MovieViewState(DataState.Failure("Something was wrong!"))
                        return@launch
                    }
                    effect.value = MovieViewEffect.Idle
                    state.update {
                        it.copy(
                            movieTitle = title, movieEmoji = "", isLoading = true
                        )
                    }
                    getEmojiUseCase(title).collectLatest { result ->
                        when (result) {
                            is DataState.Success -> {
                                state.update {
                                    it.copy(
                                        movieEmoji = result.data, isLoading = false
                                    )
                                }
                            }
                            is DataState.Failure -> {
                                state.update {
                                    it.copy(
                                        movieEmoji = "", isLoading = false
                                    )
                                }
                                effect.value =
                                    MovieViewEffect.ShowTitleConnectionError("connection failed!")
                            }
                        }
                    }
                }
            }
            is MovieViewEvent.UpdateMovieTitle -> {
                TODO()
            }
        }
    }
}