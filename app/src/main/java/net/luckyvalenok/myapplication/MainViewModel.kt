package net.luckyvalenok.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.luckyvalenok.myapplication.network.CardApi
import net.luckyvalenok.myapplication.usecase.IGetCardsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(iGetCardsUseCase: IGetCardsUseCase): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val cards = MutableLiveData<List<CardInfo>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = iGetCardsUseCase()
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