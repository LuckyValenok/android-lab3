package net.luckyvalenok.myapplication.domain.usecases.getCards

import net.luckyvalenok.myapplication.domain.data.CardInfo
import retrofit2.Response

interface GetCardsUseCase {
    suspend operator fun invoke(): Response<List<CardInfo>>
}