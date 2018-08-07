package co.louga.gradle.tasks.detekt

import co.louga.gradle.github.GithubCommitCommenter
import co.louga.gradle.github.GithubStatusChecker
import co.louga.gradle.github.GithubStatusChecker.State.FAILURE
import co.louga.gradle.github.GithubStatusChecker.State.SUCCESS
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DetektReportTask : DefaultTask() {

  lateinit var sha: String
  lateinit var token: String

  @TaskAction
  fun report() {
    val report = ReportBuilder().build(sha)
    val state: GithubStatusChecker.State

    val message: String

    if (report.isPassed) {
      state = SUCCESS
      message = "✅ Detekt: Great Stuff! 👍"
    } else {
      state = FAILURE
      message = report.body
    }

    val commentUrl = GithubCommitCommenter().createComment(token = token, message = message, sha = sha)

    GithubStatusChecker().createStatus(token = token, sha = sha, state = state, targetUrl = commentUrl)
  }
}
