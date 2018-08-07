package co.louga.domain.repository

import io.reactivex.Observable
import co.louga.domain.model.Word

/**
 * Interface that represents a Repository for getting [Word] related data.
 */
interface WordsRepository {
    /**
     * Get an [Observable] which will emit a List of [Word].
     */
    fun getWords(query: String): Observable<Collection<Word>>
}

