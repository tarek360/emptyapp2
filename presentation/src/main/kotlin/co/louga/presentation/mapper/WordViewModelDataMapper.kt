package co.louga.presentation.mapper

import co.louga.data.entity.mapper.Mapper
import co.louga.domain.model.Word
import co.louga.presentation.model.WordViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordViewModelDataMapper @Inject internal constructor() : Mapper<Word, WordViewModel>() {

  override fun map(src: Word): WordViewModel = WordViewModel(
      src.id,
      src.word,
      src.desc,
      src.imgUrl
  )

  override fun mapBack(dst: WordViewModel): Word = Word(
      dst.id,
      dst.word,
      dst.desc,
      dst.imgUrl
  )
}
