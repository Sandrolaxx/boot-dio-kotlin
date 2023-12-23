/**
 * Desafio Geronimo acredita que perde muito tempo lembrando qual número do mês representa cada um
 * deles. Ele precisa de um programa que mude os meses do ano do calendário do celular dele para
 * facilitar e agilizar a leitura. Ajude-o e faça um programa que, com uma determinada entrada de
 * uma data, retorne essa data com o mês escrito por extenso.
 *
 * Entrada: As entradas serão datas em formato numeral. O dia ou mês que possuirem somente um
 * digito, terão um 0 (zero) na frente.
 *
 * Saida: As saídas serão as mesmas datas da entrada porém, com o mês escrito por extenso. O mês
 * deve ter a primeira letra em maiúsculo.
 */
fun main() {
    val entrada: String? = "19/10/1998"
    val (dia, mes, ano) = entrada!!.split("/")
    val mapMonth =
            mutableMapOf(
                    1 to "Janeiro",
                    2 to "Fevereiro",
                    3 to "Marco",
                    4 to "Abril",
                    5 to "Maio",
                    6 to "Junho",
                    7 to "Julho",
                    8 to "Agosto",
                    9 to "Setembro",
                    10 to "Outubro",
                    11 to "Novembro",
                    12 to "Dezembro",
            )

    var mesPorExtenso = "Mês Inválido!"

    if (mapMonth.containsKey(mes.toInt())) {
        mesPorExtenso = mapMonth.get(mes.toInt())!!
    }

    println("$dia de $mesPorExtenso de $ano")
}
