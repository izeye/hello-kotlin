import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Hello world for Coroutines.
 *
 * @author Johnny Lim
 */
fun main() {
    val startTimeMillis = System.currentTimeMillis()

    runBlocking {
        launch {
            doSomething()
        }

        launch {
            doSomethingElse()
        }
    }

    println(System.currentTimeMillis() - startTimeMillis)
}

private suspend fun doSomething() {
    delay(1000)
    println("doSomething()")
}

private suspend fun doSomethingElse() {
    delay(2000)
    println("doSomethingElse()")
}
