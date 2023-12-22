data class PersonAlso(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun writeCreationLog(p: PersonAlso) {
    println("A new person ${p.name} was created")
}

fun main() {
    PersonAlso("Sandrolaxx", 25, "Java developer").also { writeCreationLog(it) }
}
