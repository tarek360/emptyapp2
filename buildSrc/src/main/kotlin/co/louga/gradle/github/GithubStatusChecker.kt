package co.louga.gradle.github

import co.louga.gradle.github.GithubStatusChecker.State.FAILURE
import co.louga.gradle.github.GithubStatusChecker.State.PENDING
import co.louga.gradle.github.GithubStatusChecker.State.SUCCESS
import net.soundvibe.jkob.json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor

open class GithubStatusChecker {

  enum class State(val state: String) {
    SUCCESS("success"),
    FAILURE("failure"),
    PENDING("pending")
  }

  fun createStatus(token: String, sha: String, state: State, targetUrl: String = "") {

    val url = "$API_BASE_URL/repos/$OWNER_NAME/$REPO_NAME/statuses/$sha"

    val description: String = when (state) {
      SUCCESS -> "Build Passed"
      FAILURE -> "Check the report in the comment"
      PENDING -> "Checking.."
    }
    val context = "Detekt"

    val bodyJson = json {
      "state" to state.state
      "target_url" to targetUrl
      "description" to description
      "context" to context
    }

    val body = RequestBody.create(MediaType.parse("application/json"), bodyJson.toString())

    val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { m -> println(m) })

    logger.level = HttpLoggingInterceptor.Level.BODY

    val okhttp = OkHttpClient.Builder().addInterceptor(logger).build()

    val request = Request.Builder()
        .url(url)
        .addHeader("Authorization", "token $token")
        .post(body)
        .build()
    okhttp.newCall(request).execute()
  }
}