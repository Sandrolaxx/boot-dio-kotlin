/**
 * Desafio Os algarismos romanos são representados por sete símbolos diferentes: I, V, X, L, C, D e
 * M. Cada um com seu respectivo valor:
 *
 * I : 1 V : 5 X : 10 L : 50 C : 100 D : 500 M : 1000
 *
 * Eles são geralmente escritos do maior para o menor. Porém, para escrevermos “4” não usamos
 * “IIII”, mas sim “IV” porque 5 - 1 = 4.
 *
 * Entrada Você receberá uma entrada em numeral romano. Saída Você deverá imprimir o numeral romano
 * convertido para um número inteiro.
 *
 * Exemplo de entrada --> MCII --> 1102 | V --> 2 ATENÇÃO A NÚMEROS COMO IV E IX
 */
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
