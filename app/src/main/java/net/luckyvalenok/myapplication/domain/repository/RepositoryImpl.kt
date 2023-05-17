package net.luckyvalenok.myapplication.domain.repository

import net.luckyvalenok.myapplication.domain.data.CardInfo
import net.luckyvalenok.myapplication.domain.api.CardApi
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val cardApi: CardApi) : Repository {
    override suspend fun getCards(): Response<List<CardInfo>> = cardApi.getCards()
}