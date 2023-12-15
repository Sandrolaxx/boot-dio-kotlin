# Introdução Prática à Linguagem de Programação Kotlin🇧🇷

### Olá, mundo!

```kotlin
ackage com.aktie              // 1

fun main() {                  // 2
    println("Olá, mundo!")    // 3
}
```

1. O código Kotlin é normalmente definido em pacotes. Pacotes são uma especificação opcional: Caso você não especifique o pacote no arquivo, ele vai utilizar o pacote padrão.

2. Função main é um ponto de entrada de execução da aplicação. Desde o Kotlin 1.3, você pode declarar a função main sem nenhum parâmetro. O tipo de retorno não precisa ser especificado, isso quer dizer que a função não retorna nada.

3. `println` escreve uma linha no console padrão. Isso é importado de maneira implicita. Note que o ponto e vírgula é opcional.

Nas versões inferiores a 1.3 do Kotlin, a função main precisa ter um parâmetro do tipo `Array<String>`.

```kotlin
fun main(args: Array<String>) {
    println("Olá, mundo!")
}
```

---

### Funções - Valor de Parâmetro Padrão e Argumentos Nomeados

```kotlin
fun imprimirMensagem(mensagem: String): Unit {                                // 1
    println(mensagem)
}
​
fun imprimirMensagemComPrefixo(mensagem: String, prefixo: String = "Info") {  // 2
    println("[$prefixo] $mensagem")
}
​
fun soma(x: Int, y: Int): Int {                                               // 3
    return x + y
}
​
fun multiplicacao(x: Int, y: Int) = x * y                                     // 4
​
fun main() {
    imprimirMensagem("Olá!")                                                  // 5                    
    imprimirMensagemComPrefixo("Olá!", "Log")                                 // 6
    imprimirMensagemComPrefixo("Olá!")                                        // 7
    imprimirMensagemComPrefixo(prefixo = "Log", mensagem = "Olá!")              // 8
    println(soma(1, 2))                                                       // 9
    println(multiplicacao(2, 4))                                              // 10
}

```

1. Uma função simples que recebe o valor do tipo String e retorna um `Unit`(sem valor de retorno).
2. Uma função que recebe um segundo parâmetro opcional, caso não informado terá o valor padrão `Info`. O valor de retorno é omitido, mesmo que `Unit`.
3. Uma função que retorna um inteiro.
4. Uma função em formato de expressão que retorna um valor inteiro.
5. Realiza a chamada da função passando o valor "Olá!".
6. Realiza a chamada passando dois valores, um para cada.
7. Realiza a mesma chamada omitindo o segundo valor. Assim assumindo o valor padrão(Info) o segundo parâmetro.
8. Realiza a chamada utilizando parâmetros nomeados e mudando sua ordem.
9. Apresenta no console o resultado da função `soma`.
10. Apresenta no console o resultado da função `multiplicacao`.

---

# Practical Introduction to Kotlin Programming Language🇺🇸

### Hello world

```kotlin
ackage com.aktie                // 1

fun main() {                    // 2
    println("Hello, World!")    // 3
}
```

1. Kotlin code is usually defined in packages. Package specification is optional: If you don't specify a package in a source file, its content goes to the default package.

2. An entry point to a Kotlin application is the main function. Since Kotlin 1.3, you can declare main without any parameters. The return type is not specified, which means that the function returns nothing.

3. `println` writes a line to the standard output. It is imported implicitly. Also note that semicolons are optional.

In Kotlin versions earlier than 1.3, the main function must have a parameter of type `Array<String>`.

```kotlin
fun main(args: Array<String>) {
    println("Hello, World!")
}
```

---

### Functions - Default Parameter Values and Named Arguments

```kotlin
fun printMessage(message: String): Unit {                               // 1
    println(message)
}
​
fun printMessageWithPrefix(message: String, prefix: String = "Info") {  // 2
    println("[$prefix] $message")
}
​
fun sum(x: Int, y: Int): Int {                                          // 3
    return x + y
}
​
fun multiply(x: Int, y: Int) = x * y                                    // 4
​
fun main() {
    printMessage("Hello")                                               // 5                    
    printMessageWithPrefix("Hello", "Log")                              // 6
    printMessageWithPrefix("Hello")                                     // 7
    printMessageWithPrefix(prefix = "Log", message = "Hello")           // 8
    println(sum(1, 2))                                                  // 9
    println(multiply(2, 4))                                             // 10
}
```

1. A simple function that takes a parameter of type String and returns Unit (i.e., no return value).
2. A function that takes a second optional parameter with default value Info. The return type is omitted, meaning that it's actually Unit.
3. A function that returns an integer.
4. A single-expression function that returns an integer (inferred).
5. Calls the first function with the argument Hello.
6. Calls the function with two parameters, passing values for both of them.
7. Calls the same function omitting the second one. The default value Info is used.
8. Calls the same function using named arguments and changing the order of the arguments.
9. Prints the result of the sum function call.
10. Prints the result of the multiply function call.