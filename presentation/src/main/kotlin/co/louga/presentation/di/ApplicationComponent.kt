package co.louga.presentation.di

import co.louga.presentation.ui.words.WordsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, WordsActivityModule::class])
interface ApplicationComponent {

    fun inject(wordsActivity: WordsActivity)
}
