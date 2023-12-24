fun main() {
    val numeroRomano: String? = "IX"
    var result = 0

    val numerosRomanos =
            mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)

    for (i in numeroRomano!!.indices) {
        val element = numerosRomanos.getOrElse(numeroRomano[i]) { 0 }
        val next =
                if (i < numeroRomano.length - 1) {
                    numerosRomanos.getOrElse(numeroRomano[i + 1]) { 0 }
                } else 0

        if (element < next) {
            result -= element
        } else {
            result += element
        }
    }

    print(result)
}
