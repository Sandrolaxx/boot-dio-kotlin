open class Dog {
    open fun sayHello() {
        println("wow wow!")
    }
}

class Yorkshire : Dog() {
    override fun sayHello() {
        println("wif wif!")
    }
}

// Inheritance with Parameterized Constructor
open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}

class SiberianTiger : Tiger("Siberia")

// Passing Constructor Arguments to Superclass
open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}

class Asiatic(name: String) : Lion(name = name, origin = "India")

fun main() {
    val dog: Dog = Yorkshire()

    dog.sayHello()

    // with params
    val tiger: Tiger = SiberianTiger()

    tiger.sayHello()

    // passing constructor params to superclass
    val lion: Lion = Asiatic("Rufo")
    lion.sayHello()
}
