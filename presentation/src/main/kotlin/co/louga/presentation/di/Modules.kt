package co.louga.presentation.di

import co.louga.data.entity.mapper.WordEntityDataMapper
import co.louga.data.net.LougaApi
import co.louga.data.net.RemoteLougaApi
import co.louga.data.repository.WordDataRepository
import co.louga.data.repository.datasource.WordDataStoreFactory
import co.louga.domain.interactor.WordsRequestUseCase
import co.louga.domain.repository.WordsRepository
import co.louga.domain.rx.ExecutionScheduler
import co.louga.presentation.mapper.WordViewModelDataMapper
import co.louga.presentation.rx.AppSchedulerProvider
import co.louga.presentation.ui.words.IWordsAdapter
import co.louga.presentation.ui.words.WordsAdapter
import co.louga.presentation.ui.words.WordsContract
import co.louga.presentation.ui.words.WordsPresenter
import org.koin.dsl.module.module

val wordsActivityModule = module {
    single { AppSchedulerProvider() }
    single { WordDataStoreFactory() }
    single { WordViewModelDataMapper() }
    single { WordEntityDataMapper() }
    single<WordsRepository> { WordDataRepository(get(), get()) }
    single<LougaApi> { RemoteLougaApi() }
    factory<WordsContract.Presenter> { WordsPresenter(get(), get()) }
    factory {
        val appSchedulerProvider = get() as AppSchedulerProvider
        val executionScheduler = ExecutionScheduler(appSchedulerProvider.io(), appSchedulerProvider.main())
        WordsRequestUseCase(get(), executionScheduler)
    }
    factory<IWordsAdapter> { WordsAdapter(get()) }
}

val allModules = listOf(wordsActivityModule)