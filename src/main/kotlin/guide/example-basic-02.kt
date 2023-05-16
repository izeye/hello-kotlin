package guide

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch { // launch a new coroutine and continue
        doWorld()
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

private suspend fun doWorld() {
    delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
    println("World!") // print after delay
}
