package com.khodkari.movietoemoji.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khodkari.movietoemoji.presentation.view.theme.MovieToEmojiTheme
import com.khodkari.movietoemoji.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieToEmojiTheme{
                MovieView(
                    viewModel = viewModel ,
                    context = baseContext
                )
            }

        }

    }
}
