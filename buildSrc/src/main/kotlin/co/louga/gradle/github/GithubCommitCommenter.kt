package co.louga.gradle.github

import net.soundvibe.jkob.json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.gradle.api.Task
import org.gradle.api.logging.Logging
import org.json.JSONObject

open class GithubCommitCommenter {

  private val logger = Logging.getLogger(Task::class.java)

  fun createComment(token: String, message: String, sha: String): String {

    val url = "$API_BASE_URL/repos/$OWNER_NAME/$REPO_NAME/commits/$sha/comments"

    val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { m -> logger.debug(m) })

    logger.level = HttpLoggingInterceptor.Level.BODY

    val okhttp = OkHttpClient.Builder().addInterceptor(logger).build()

    val bodyJson = json {
      "body" to message
    }

    val body = RequestBody.create(MediaType.parse("application/json"), bodyJson.toString())

    val request = Request.Builder()
        .url(url)
        .addHeader("Authorization", "token $token")
        .post(body)
        .build()

    val response = okhttp.newCall(request).execute()

    val jsonResponse = JSONObject(response.body()?.string())

    return jsonResponse.getString("html_url")
  }
}