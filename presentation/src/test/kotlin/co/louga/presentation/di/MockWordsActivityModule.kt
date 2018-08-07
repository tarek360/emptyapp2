package co.louga.presentation.di

import co.louga.domain.interactor.WordsRequestUseCase
import co.louga.presentation.mapper.WordViewModelDataMapper
import co.louga.presentation.ui.words.WordsContract

class MockWordsActivityModule(private val presenter: WordsContract.Presenter)
    : WordsActivityModule() {

    override fun provideWordsPresenter(wordsRequestUseCase: WordsRequestUseCase, wordViewModelDataMapper: WordViewModelDataMapper)
            : WordsContract.Presenter {
        return presenter
    }
}
