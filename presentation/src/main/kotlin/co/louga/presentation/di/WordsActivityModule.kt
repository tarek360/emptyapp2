package co.louga.presentation.di

import android.content.Context
import co.louga.data.net.RemoteLougaApi
import co.louga.data.repository.WordDataRepository
import co.louga.domain.interactor.WordsRequestUseCase
import co.louga.domain.rx.ExecutionScheduler
import co.louga.presentation.mapper.WordViewModelDataMapper
import co.louga.presentation.rx.AppSchedulerProvider
import co.louga.presentation.ui.words.IWordsAdapter
import co.louga.presentation.ui.words.WordsAdapter
import co.louga.presentation.ui.words.WordsContract
import co.louga.presentation.ui.words.WordsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class WordsActivityModule {

  @Provides
  @Singleton
  fun provideLougaApi(): RemoteLougaApi = RemoteLougaApi()

  @Provides
  @Singleton
  open fun provideWordsPresenter(wordsRequestUseCase: WordsRequestUseCase,
      wordViewModelDataMapper: WordViewModelDataMapper)
      : WordsContract.Presenter {
    return WordsPresenter(wordsRequestUseCase, wordViewModelDataMapper)
  }

  @Provides
  open fun provideWordsRequestUseCase(wordDataRepository: WordDataRepository,
      appSchedulerProvider: AppSchedulerProvider): WordsRequestUseCase {
    val executionScheduler = ExecutionScheduler(appSchedulerProvider.io(),
        appSchedulerProvider.main())
    return WordsRequestUseCase(wordDataRepository, executionScheduler)
  }

  @Provides
  open fun provideWordsAdapter(context: Context): IWordsAdapter {
    return WordsAdapter(context)
  }


}
