package com.khodkari.movietoemoji.presentation.view

import android.content.Context
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.khodkari.movietoemoji.R
import com.khodkari.movietoemoji.presentation.model.MovieViewEffect
import com.khodkari.movietoemoji.presentation.model.MovieViewEvent
import com.khodkari.movietoemoji.presentation.viewmodel.MovieViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun MovieView(
    viewModel: MovieViewModel,
    context: Context,
) {
    var title by remember {
        mutableStateOf("")
    }
    val state by remember { mutableStateOf(viewModel.state.value) }
    var loading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    LoadingIndicator(visible = loading)


    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Surface(color = Color.Black.copy(alpha = 0.85f)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .padding(16.dp)
                    .width(220.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primaryVariant,
                    unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
                    disabledLabelColor = MaterialTheme.colors.secondary,
                    focusedLabelColor = MaterialTheme.colors.primaryVariant,
                    unfocusedLabelColor = MaterialTheme.colors.primaryVariant,
                    disabledBorderColor = MaterialTheme.colors.secondary,
                    cursorColor = MaterialTheme.colors.primaryVariant,
                    textColor = MaterialTheme.colors.primaryVariant,
                    trailingIconColor = MaterialTheme.colors.primaryVariant,
                ),
                label = {
                    Text(
                        text = "Movie Title",
                        modifier = Modifier
                            .background(Color(parseColor("#1C1B1F")))
                            .padding(horizontal = 2.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { title = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "clear title"
                        )
                    }
                }
            )
            Button(
                modifier = Modifier.padding(top = 50.dp),
                onClick = {
                    viewModel.processEvent(MovieViewEvent.SubmitMovieTitle(title))
                    coroutineScope.launch {
                        viewModel.effect.collect { effect ->
                            when (effect) {
                                is MovieViewEffect.ShowTitleConnectionError -> {
                                    makeToast(context, "Network error: ${effect.error}")
                                }
                                is MovieViewEffect.ShowTitleEmptyError -> {
                                    makeToast(context, effect.error)
                                }
                                is MovieViewEffect.ShowLoading -> loading = true
                                is MovieViewEffect.HideLoading -> loading = false

                                is MovieViewEffect.ShowMovieEmoji -> {
                                    state.movieEmoji = effect.emoji
                                }
                                is MovieViewEffect.Idle -> {
                                    // Handle idle state
                                }
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.secondary,
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = "Get emoji",
                    modifier = Modifier.padding(5.dp)
                )
            }
            if (state.isLoading) {
                LoadingIndicator(visible = loading)
            } else if (state.movieEmoji.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = state.movieEmoji ,
                    style = TextStyle(
                        fontSize = 30.sp ,
                        color = MaterialTheme.colors.secondary
                    )
                )
            }
        }
    }
}

private fun makeToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
fun LoadingIndicator(visible: Boolean) {
    if (visible) {
        Box(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}
