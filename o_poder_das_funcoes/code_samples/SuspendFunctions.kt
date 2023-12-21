import kotlinx.coroutines.*

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)

    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)

    return 29
}

fun main() {
    runBlocking {
        doWorld()

        println(doSomethingUsefulOne())
        println(doSomethingUsefulTwo())
    }

}

suspend fun doWorld() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }

    println("Hello")
}