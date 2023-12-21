fun main() {
    infix fun Int.times(str: String) = str.repeat(this)

    println(2 times "Bye ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)

    val myPair = "McLaren" onto "Lucas"

    println(myPair)

    val sophia = PersonInfix("Sophia")
    val claudia = PersonInfix("Claudia")

    sophia likes claudia
}

class PersonInfix(val name: String) {
    val likedPeople = mutableListOf<PersonInfix>()

    infix fun likes(other: PersonInfix) {
        likedPeople.add(other)
    }
}
