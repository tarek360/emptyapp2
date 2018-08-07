package co.louga.presentation.ui.words

import co.louga.presentation.model.WordViewModel

interface WordsContract {
  interface View {
    fun showContent()
    fun setData(data: Collection<WordViewModel>)
    fun getData(): Collection<WordViewModel>
    fun showError(msg : String)
  }

  interface Presenter {
    fun attachView(view: View)
    fun detachView()
    fun loadWords(category: String)
  }
}