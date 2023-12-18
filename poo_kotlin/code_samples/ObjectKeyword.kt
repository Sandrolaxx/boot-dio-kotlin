import java.util.Random

class LuckDispatcher {
    fun getNumber() {
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}

// object expression
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {
    val dayRates =
            object {
                var standard: Int = 30 * standardDays
                var festivity: Int = 50 * festivityDays
                var special: Int = 100 * specialDays
            }

    val total = dayRates.standard + dayRates.festivity + dayRates.special

    print("Total price: $$total")
}

// object declaration
object DoAuth {
    fun takeParams(username: String, password: String) {
        println("input Auth parameters = $username:$password")
    }
}

// companion Objects
class BigBen {                                  //1 
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}


fun main() {
    val d1 = LuckDispatcher()
    val d2 = LuckDispatcher()

    d1.getNumber()
    d2.getNumber()

    // object expression
    rentPrice(10, 2, 1)

    // object declaration
    DoAuth.takeParams("foo", "qwerty")

    // companion Objects
    BigBen.getBongs(12)
}
