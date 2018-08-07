package co.louga.presentation

import android.support.multidex.MultiDexApplication
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.FirebaseFirestoreSettings
import co.louga.data.net.Backend
import co.louga.presentation.di.ApplicationComponent
import co.louga.presentation.di.ApplicationModule
import co.louga.presentation.di.DaggerApplicationComponent
import co.louga.presentation.di.WordsActivityModule


open class App : MultiDexApplication() {

  companion object {
    lateinit var component: ApplicationComponent
  }

  override fun onCreate() {
    super.onCreate()

    initComponent()
    initFirebase()
    Backend.instance.start(this)
  }

  open fun initFirebase() {
//    FirebaseFirestore.setLoggingEnabled(true)
//
//    val firestore = FirebaseFirestore.getInstance()
//    val settings = FirebaseFirestoreSettings.Builder()
//        .setTimestampsInSnapshotsEnabled(true)
//        .build()
//    firestore.firestoreSettings = settings
  }

  fun initComponent() {
    component = DaggerApplicationComponent.builder()
        .applicationModule(getApplicationModule())
        .wordsActivityModule(getWordsActivityModule())
        .build()
  }

  open fun getApplicationModule(): ApplicationModule? {
    return ApplicationModule(this)
  }

  open fun getWordsActivityModule(): WordsActivityModule? {
    return WordsActivityModule()
  }

}
