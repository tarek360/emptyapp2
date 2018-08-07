package co.louga.presentation.ui.words

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
//import com.google.firebase.firestore.FirebaseFirestore
import co.louga.presentation.App
import co.louga.presentation.R
import co.louga.presentation.model.WordViewModel
import kotlinx.android.synthetic.main.activity_words.customViewSlider
import kotlinx.android.synthetic.main.activity_words.errorTextView
import javax.inject.Inject

class WordsActivity : AppCompatActivity(), WordsContract.View {

  companion object {
    fun buildIntent(baseContext: Context?, query: String): Intent? {

      val intent = Intent(baseContext, WordsActivity::class.java)
      intent.putExtra(KEY_CATEGORY, query)
      return intent
    }

    const val KEY_CATEGORY = "category"
  }

  @Inject
  lateinit var adapter: IWordsAdapter

  @Inject
  lateinit var presenter: WordsContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    App.component.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_words)

    customViewSlider.setAdapter(adapter)

    presenter.attachView(this)

    loadData()
  }

  override fun setData(data: Collection<WordViewModel>) {
    customViewSlider.setData(data)
  }

//  private fun loadFirebaseData() {
//    val doc = FirebaseFirestore.getInstance().collection("words")
//        .orderBy("").limit(1)
//
//    Log.d("firestore", "documents[0] => " + doc.get().result.documents[0].data)
//
//    doc.get().addOnSuccessListener { task ->
//      if (!task.isEmpty) {
//        for (document in task.documents) {
//          Log.d("firestore", document.id + " => " + document.data)
//        }
//      }
//    }
//    doc.get().addOnCompleteListener { task ->
//      if (task.isSuccessful) {
//        for (document in task.result) {
//          Log.d("firestore", document.id + " => " + document.data)
//        }
//      } else {
//        Log.d("firestore", "Error getting documents: ", task.exception)
//      }
//    }
//  }

  private fun loadData() {
    presenter.loadWords(intent.getStringExtra(KEY_CATEGORY) ?: "")
  }

  override fun getData(): Collection<WordViewModel> = adapter.getData()


  override fun showContent() {

  }

  override fun showError(msg: String) {
    errorTextView.text = msg
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }
}
