package co.louga.data.repository.datasource

import android.util.Log
//import com.google.firebase.firestore.FirebaseFirestore
import co.louga.data.entity.WordEntity
import co.louga.data.net.WordResponse
import io.reactivex.Observable
import java.util.ArrayList


/**
 * [WordDataStore] implementation based on connections to the api (Cloud).
 */
internal class WordFireStore : WordDataStore {

  companion object {
    const val TAG = "WordFireStore"
  }

  override fun fetchWords(query: String): Observable<WordResponse> {
//    val doc = FirebaseFirestore.getInstance().collection("words")
//
//    Log.d("firestore", "documents[0] => " + doc.get().result.documents[0].data)
//
//    doc.get().addOnCompleteListener { task ->
//      if (task.isSuccessful) {
//        for (document in task.result) {
//          Log.d(TAG, document.id + " => " + document.data)
//        }
//      } else {
//        Log.d(TAG, "Error getting documents: ", task.exception)
//      }
//    }


    val words = ArrayList<WordEntity>()
    words.add(WordEntity(1, "a", "b", ""))
    return Observable.just(WordResponse(words))
  }
}
