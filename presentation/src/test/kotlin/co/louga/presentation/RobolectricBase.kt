package co.louga.presentation

import android.os.Build
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

//@Config(application = TestRobolectricApplication::class, sdk = [(Build.VERSION_CODES.LOLLIPOP)])
@RunWith(RobolectricTestRunner::class)
open class RobolectricBase {

  @Test
  @Ignore
  fun fake() {
  }

}