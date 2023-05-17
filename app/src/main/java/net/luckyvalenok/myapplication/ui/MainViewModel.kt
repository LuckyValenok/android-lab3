package net.luckyvalenok.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.luckyvalenok.myapplication.domain.data.CardInfo
import net.luckyvalenok.myapplication.domain.usecases.getCards.GetCardsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(getCardsUseCase: GetCardsUseCase): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val cards = MutableLiveData<List<CardInfo>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCardsUseCase()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    cards.postValue(response.body())
                } else {
                    errorMessage.postValue("Error : ${response.message()} ")
                }
            }
        }
    }
}