package com.khodkari.movietoemoji.data.api

import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.khodkari.movietoemoji.data.di.qualifier.ApiKey
import javax.inject.Inject

class OpenAIApi @Inject constructor(
    @ApiKey private val apiKey: String
) : Api {
    private val openAI = OpenAI(apiKey)
    override suspend fun getEmojiForMovieTitle(newMovieName: String): String {

        val prompt = "Convert movie titles into emoji.\n" +
                "\n" +
                "Back to the Future: \uD83D\uDC68\uD83D\uDC74\uD83D\uDE97\uD83D\uDD52 \n" +
                "Batman: \uD83E\uDD35\uD83E\uDD87 \n" +
                "Transformers: \uD83D\uDE97\uD83E\uDD16 \n" +
                "$newMovieName:"
        var emoji = ""
        val completionRequest = CompletionRequest(
            model = ModelId("text-davinci-003"),
            prompt = prompt,
            echo = true
        )

        val result = openAI.completion(completionRequest).choices

        result.forEach {
            emoji = it.text.split("$newMovieName:")[1]
        }
        return emoji
    }
}