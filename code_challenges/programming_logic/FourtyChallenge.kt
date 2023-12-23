/**
 * Faça um programa que calcule e imprima o salário a ser transferido para um funcionário. Para
 * realizar o calculo, primeiro receba o valor do salário bruto (valorSalario) e adicional dos
 * benefícios (valorBeneficios). Por fim, o salário a ser transferido é calculado da seguinte
 * maneira:
 *
 * (valorSalario - valorImpostos) + valorBeneficios
 *
 * Para calcular o valorImpostos, seguem as aliquotas (baseadas no valor do salário bruto):
 *
 * De R$ 0.00 a R$ 1100.00 = 5.00% De R$ 1100.01 a R$ 2500.00 = 10.00% Maior que R$ 2500.00 = 15.00%
 * 
 * Exemplo entrada: 2000 e 250 --> Saída: 2050.00
 */
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
