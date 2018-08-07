package co.louga.presentation.ui.words

import android.os.Build
import android.widget.ProgressBar
import co.louga.presentation.R
import co.louga.presentation.TestRobolectricApplication
import co.louga.presentation.di.MockWordsActivityModule
import co.louga.presentation.model.WordViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@Config(application = TestRobolectricApplication::class, sdk = [(Build.VERSION_CODES.LOLLIPOP)])
@RunWith(RobolectricTestRunner::class)
class WordsActivityTest {

  private lateinit var controller: ActivityController<WordsActivity>
  private lateinit var activity: WordsActivity

  @Mock
  private lateinit var presenter: WordsContract.Presenter

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    val app = RuntimeEnvironment.application as TestRobolectricApplication
    app.setWordsActivityModule(MockWordsActivityModule(presenter))

    val intent = WordsActivity.buildIntent(RuntimeEnvironment.application.applicationContext, "test")

    controller = Robolectric.buildActivity(WordsActivity::class.java, intent).create()
        .start()
        .postCreate(null)
        .resume()
        .visible()

    activity = controller.get()
  }

  @Test
  fun assertViewsExist() {
    val loadingView = activity.findViewById<ProgressBar>(R.id.loadingView)
    assertNotNull("loadingView could not be found", loadingView)
  }

  @Test
  fun testLoadWords() {
    verify(presenter).attachView(any())
    verify(presenter).loadWords(any())
  }

  @Test
  fun checkIfDataSetToAdapter() {
    val list = ArrayList<WordViewModel>()
    list.add(WordViewModel(2, "Name", "desc", "url"))
    activity.setData(list)
    assertEquals(1, activity.adapter.getData().size)
  }

  @After
  fun tearDown() {
    // Destroy activity after every test
    controller
        .pause()
        .stop()
        .destroy()
  }
}
