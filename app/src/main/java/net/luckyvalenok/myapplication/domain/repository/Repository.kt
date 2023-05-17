package net.luckyvalenok.myapplication.domain.repository

import net.luckyvalenok.myapplication.domain.data.CardInfo
import retrofit2.Response

interface Repository {
    suspend fun getCards(): Response<List<CardInfo>>
}