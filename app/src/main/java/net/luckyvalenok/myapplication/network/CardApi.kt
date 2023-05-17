package net.luckyvalenok.myapplication.network

import net.luckyvalenok.myapplication.CardInfo
import retrofit2.Response
import retrofit2.http.GET

interface CardApi {
    @GET("new_text.json")
    suspend fun getCards(): Response<List<CardInfo>>
}