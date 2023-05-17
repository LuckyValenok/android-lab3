package net.luckyvalenok.myapplication.usecase

import net.luckyvalenok.myapplication.CardInfo
import net.luckyvalenok.myapplication.network.IRepository
import retrofit2.Response
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val iRepository: IRepository) : IGetCardsUseCase {
    override suspend fun invoke(): Response<List<CardInfo>> = iRepository.getCards()
}