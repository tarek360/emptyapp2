package co.louga.data.repository.datasource

import co.louga.data.net.RemoteLougaApi

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory that creates different implementations of [WordDataStore].
 */
@Singleton
class WordDataStoreFactory @Inject internal constructor() {

    /**
     * Create [WordDataStore] to retrieve data from the Cloud.
     */
    fun createWordFireStore(): WordDataStore {
        return WordFireStore()
    }

    /**
     * Create [WordDataStore] to retrieve data from the Cloud.
     */
    fun createCloudDataStore(): WordDataStore {
        val logaApi = RemoteLougaApi()
        return WordCloudDataStore(logaApi)
    }
}
