package coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")
}
