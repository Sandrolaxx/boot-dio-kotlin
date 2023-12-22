class IllegalVoterException(message: String) : Throwable(message)

@Throws(IllegalVoterException::class)
fun vote(name: String, age: Int) {
    if (age < 16) {
        throw IllegalVoterException("Apenas pessoas com 16 anos ou mais podem votar.")
    }

    println("Voto realizado por $name com idade: $age")
}

fun main() {
    vote("Sandrolax", 25)
    vote("Amabilly", 11)
}
