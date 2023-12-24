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
