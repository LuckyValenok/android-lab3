package net.luckyvalenok.myapplication.domain.api

import net.luckyvalenok.myapplication.domain.data.CardInfo
import retrofit2.Response
import retrofit2.http.GET

interface CardApi {
    @GET("new_text.json")
    suspend fun getCards(): Response<List<CardInfo>>
}