package co.louga.data.repository.datasource

import co.louga.data.net.LougaApi
import co.louga.data.net.WordRequest
import co.louga.data.net.WordResponse

import io.reactivex.Observable

/**
 * [WordDataStore] implementation based on connections to the api (Cloud).
 */
internal class WordCloudDataStore
/**
 * Construct a [WordDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [LougaApi] implementation to use.
 */
(private val restApi: LougaApi) : WordDataStore {

    override fun fetchWords(query: String): Observable<WordResponse> {
        return this.restApi.fetchWords(WordRequest(query))
    }
}
