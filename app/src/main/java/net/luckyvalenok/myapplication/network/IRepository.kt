package net.luckyvalenok.myapplication.network

import net.luckyvalenok.myapplication.CardInfo
import retrofit2.Response

interface IRepository {
    suspend fun getCards(): Response<List<CardInfo>>
}