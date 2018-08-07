package co.louga.data.repository

import co.louga.data.entity.mapper.WordEntityDataMapper
import co.louga.data.repository.datasource.WordDataStoreFactory
import co.louga.domain.model.Word
import co.louga.domain.repository.WordsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordDataRepository
@Inject internal constructor(private val wordDataStoreFactory: WordDataStoreFactory,
    private val wordEntityDataMapper: WordEntityDataMapper) : WordsRepository {

  override fun getWords(query: String): Observable<Collection<Word>> {
    return wordDataStoreFactory.createCloudDataStore().fetchWords(query).map {
      wordEntityDataMapper.map(it.words)
    }
  }
}
