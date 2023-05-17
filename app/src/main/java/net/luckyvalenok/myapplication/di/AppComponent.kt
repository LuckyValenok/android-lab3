package net.luckyvalenok.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import net.luckyvalenok.myapplication.domain.api.CardApi
import net.luckyvalenok.myapplication.domain.repository.Repository
import net.luckyvalenok.myapplication.domain.repository.RepositoryImpl
import net.luckyvalenok.myapplication.domain.usecases.getCards.GetCardsUseCase
import net.luckyvalenok.myapplication.domain.usecases.getCards.GetCardsUseCaseImpl
import net.luckyvalenok.myapplication.ui.FirstFragment
import net.luckyvalenok.myapplication.ui.MainViewModel
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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

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
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindGetCardsUseCase(useCase: GetCardsUseCaseImpl): GetCardsUseCase
}
