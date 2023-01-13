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
    override suspend fun getEmojiForMovieTitle(prompt: String): String {

        val completionRequest = CompletionRequest(
            model = ModelId("text-davinci-003"),
            prompt = prompt,
            echo = true
        )

        return openAI.completion(completionRequest).choices.first().text.split(prompt)[1]
    }
}