package net.luckyvalenok.myapplication.domain.usecases.getCards

import net.luckyvalenok.myapplication.domain.data.CardInfo
import net.luckyvalenok.myapplication.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class GetCardsUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetCardsUseCase {
    override suspend fun invoke(): Response<List<CardInfo>> = repository.getCards()
}