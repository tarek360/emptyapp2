package co.louga.presentation.ui.words

import co.louga.presentation.model.WordViewModel

interface IWordsAdapter {
  fun setData(data: Collection<WordViewModel>)
  fun getData(): Collection<WordViewModel>
}