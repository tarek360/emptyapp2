import platform.posix.execlp
import platform.posix.fork
import platform.posix.system
import platform.posix.waitpid

fun main(_args: Array<String>) {
    system("mkdir -p ./reports")
    system("echo 'I am Native' > reports/native.txt")
    executeSh("myShell.sh")
    println("kotlin native")
    throw Exception("my native Exception")
}

fun executeSh(filePath: String) {
    val childPid = fork()
    when {
        childPid < 0 -> {
            println("An error has occurred while executing: $filePath")
        }
        childPid == 0 -> {// We are the child
            execlp("sh", "sh", filePath, null)
        }
        else -> {
            waitpid(childPid, null, 0)
        }
    }
}
