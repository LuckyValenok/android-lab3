package net.luckyvalenok.myapplication.network

import net.luckyvalenok.myapplication.CardInfo
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val cardApi: CardApi) : IRepository {
    override suspend fun getCards(): Response<List<CardInfo>> = cardApi.getCards()
}