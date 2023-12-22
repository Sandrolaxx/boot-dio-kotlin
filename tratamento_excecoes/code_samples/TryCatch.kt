fun main() {
    val a = 23
    val b = 0

    try {
        val div = a / b

        print(div)
    } catch (e: Exception) {
        println("Exception: ${e.message}")
    } finally {
        println("finally always be executed!")
    }
}
