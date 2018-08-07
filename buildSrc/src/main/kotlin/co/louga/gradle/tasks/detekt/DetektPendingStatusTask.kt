package co.louga.gradle.tasks.detekt

import co.louga.gradle.github.GithubStatusChecker
import co.louga.gradle.github.GithubStatusChecker.State.PENDING
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class DetektPendingStatusTask : DefaultTask() {

  lateinit var sha: String
  lateinit var token: String

  @TaskAction
  fun createStatus() {
    GithubStatusChecker().createStatus(token = token, sha = sha, state = PENDING)
  }
}
