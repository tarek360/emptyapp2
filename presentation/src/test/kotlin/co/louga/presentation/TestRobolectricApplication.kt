package co.louga.presentation

import co.louga.presentation.di.WordsActivityModule

class TestRobolectricApplication : App() {

    private var wordsActivityModule: WordsActivityModule? = null

    override fun getWordsActivityModule(): WordsActivityModule? {
        return if (wordsActivityModule == null) {
            super.getWordsActivityModule()
        } else wordsActivityModule
    }

    fun setWordsActivityModule(wordsActivityModule: WordsActivityModule) {
        this.wordsActivityModule = wordsActivityModule
        initComponent()
    }

    override fun initFirebase() {
    }
}