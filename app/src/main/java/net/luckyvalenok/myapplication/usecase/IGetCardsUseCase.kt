package net.luckyvalenok.myapplication.usecase

import net.luckyvalenok.myapplication.CardInfo
import retrofit2.Response

interface IGetCardsUseCase {
    suspend operator fun invoke(): Response<List<CardInfo>>
}