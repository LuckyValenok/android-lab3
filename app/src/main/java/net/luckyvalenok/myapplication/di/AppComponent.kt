package net.luckyvalenok.myapplication.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import net.luckyvalenok.myapplication.FirstFragment
import net.luckyvalenok.myapplication.MainViewModel
import net.luckyvalenok.myapplication.network.CardApi
import net.luckyvalenok.myapplication.network.IRepository
import net.luckyvalenok.myapplication.network.Repository
import net.luckyvalenok.myapplication.usecase.GetCardsUseCase
import net.luckyvalenok.myapplication.usecase.IGetCardsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun injectFirstFragment(firstFragment: FirstFragment)
}

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}

@Module
abstract class NetworkModule {

    companion object {
        @Provides
        fun provideApi(): CardApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://develtop.ru/study/")
                .build()
            return retrofit.create(CardApi::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun repository(repository: Repository): IRepository

    @Binds
    @Singleton
    abstract fun getCardsUseCase(useCase: GetCardsUseCase): IGetCardsUseCase
}
