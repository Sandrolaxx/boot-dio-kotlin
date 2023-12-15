fun imprimirMensagem(mensagem: String): Unit {
    println(mensagem)
}

fun imprimirMensagemComPrefixo(mensagem: String, prefixo: String = "Info") {
    println("[$prefixo] $mensagem");
}

fun soma(x: Int, y: Int): Int {
    return x + y
}

fun multiplicacao(x: Int, y: Int) = x * y

fun main() {
    imprimirMensagem("Olá!")
    imprimirMensagemComPrefixo("Olá!", "Log")
    imprimirMensagemComPrefixo("Olá!")
    imprimirMensagemComPrefixo(prefixo = "Log", mensagem = "Olá!")
    
    println(soma(1, 4))
    println(multiplicacao(2, 4))
}
