object ReceitaFederal {
    fun calcularImposto(salario: Double): Double {
        val aliquota =
                when {
                    (salario >= 0.00 && salario <= 1100.00) -> 0.05
                    (salario >= 1100.01 && salario <= 2500.00) -> 0.1
                    else -> 0.15
                }

        return aliquota * salario
    }
}

fun main() {
    val valorSalario = 1100.00
    val valorBeneficios = 400.00

    val valorImposto = ReceitaFederal.calcularImposto(valorSalario)
    val saida = valorSalario - valorImposto + valorBeneficios

    println(String.format("%.2f", saida))
}
