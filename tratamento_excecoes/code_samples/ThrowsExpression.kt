class PersonThrow(val name: String?)

fun main() {
    val person = PersonThrow("name")

    println("Executando....")

    val personTwo = PersonThrow(null)

    println("Nothing nem chega a ser executado")

    println(personTwo)

    val s = person.name ?: throw IllegalArgumentException("Name required")
}
