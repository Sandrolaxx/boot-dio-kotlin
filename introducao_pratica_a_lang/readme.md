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

### Funções com Parâmetros varargs

Varargs permite passar um número indefinido de parâmetros separado por vírgula.

```kotlin
fun imprimirTodos(vararg mensagens: String) {                            // 1
    for (m in mensagens) println(m)
}

imprimirTodos("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2
​
fun imprimirTodosComPrefixo(vararg mensagens: String, prefixo: String) {// 3
    for (m in mensagens) println(prefixo + m)
}

imprimirTodosComPrefixo(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefixo = "Greeting: "                                              // 4
)
​
fun log(vararg entradas: String) {
    imprimirTodos(*entradas)                                           // 5
}

log("Hello", "Hallo", "Salut", "Hola", "你好")
```

1. O modificador vararg torna o parâmetro em vararg.
2. Permite chamarmos `imprimirTodos` com N parâmetros.
3. Graças aos parâmetros nomeados, você pode adicionar outros parâmetros após vararg. Isso não é possível no Java por ele não ter valores nomeados, seria necessário primeiro passar os parâmetros tipados e depois o varags.
4. Usando parâmetros nomeados você pode definir `prefixo` após varargs.
5. Em tempo de execução, o vararg é apenas um vetor. Para passar um vararg como parâmetro, é necessário passar o operador especial (`*`) que nos permite passar `*entradas` (um vararg de String) ao invés de entradas como (um Array<String>).

---

### Variáveis

Kotlin possui uma poderosa inferência de tipos. Você pode explicitamente tipar uma variável, ou deixar que o compilador faça esse trabalho para você. Kotlin não força imutabilidade, embora isso seja recomendado. Em essência, utilize `val` no lugar de `var`.

```kotlin
var a: String = "initial"  // 1

println(a)

val b: Int = 1             // 2
val c = 3                  // 3
```

1. Declara uma variável mutável e inicializa ela.
2. Declara uma variável imutável e inicializa ela.
3. Declara uma variável imutável e inicializa ela sem especificar seu tipo. O compilador infere o tipo Int.

```kotlin
var e: Int  // 1

println(e)  // 2
```

1. Declara uma variável sem inicializar.
2. Ao tentar utilizar a variável, isso vai causar um erro em tempo de compilação: Variable 'e' must be initialized.

Sinta-se livre para inicializar uma variável quando quiser, com tudo, ela precisa ser inicializada antes da primeira leitura.

```kotlin
val d: Int  // 1
​
if (someCondition()) {
    d = 1   // 2
} else {
    d = 2   // 2
}
​
println(d) // 3
```

1. Declara uma variável sem inicializar.
2. Inicializa a variável de acordo com alguma condição.
3. Realiza a leitura do valor, isso só é possível após inicialização.

---

### Null Safety - Segurança contra Nulidade

Um modo de escapar do mundo no NullPointerException, um tipo de variável que o Kotlin não permite é o `null`. Caso você precise setar uma variável com o valor nulo, declare que o valor pode ser potencialmente nulo adicionando `?` ao final do tipo.

```kotlin
var neverNull: String = "Isso não pode ser nulo"         // 1
​
neverNull = null                                         // 2
​
var nullable: String? = "Aqui é possível receber nulo"   // 3
​
nullable = null                                          // 4
​
var inferredNonNull = "Compilador infere que é não nulo" // 5
​
inferredNonNull = null                                   // 6
​
fun strLength(notNull: String): Int {                    // 7
    return notNull.length
}
​
strLength(neverNull)                                     // 8
strLength(nullable)                                      // 9
```

1. Declara uma variável String não nula.
2. Quando tenta setar um valor nulo em uma variável não nula, um erro de compilação é gerado.
3. Declaração de uma String potencialmente nula.
4. Setando um valor nulo em uma variável potencialmente nula. Isso é OK.
5. Ao inferir tipos, o compilador assume que a variável é não nula para variáveis inicializadas com um valor.
6. Quando tenta setar um valor nulo em uma variável com tipo inferido, um erro de compilação é gerado.
7. Declaração de uma função com parâmetro String não-nulo.
8. Chamando a função passando um parâmetro não nulo. Isso é OK.
9. Quando chama a função com uma `String?` (potencialmente nulo) como argumento, um erro de compilação é gerado.

**Trabalhando com nulos**

Às vezes precisamos trabalhar com valores nulos, como interagir com código Java externo ou representar um estado verdadeiramente ausente. Kotlin prove uma forma elegante de tratar esses casos e resolver essas situações.

```java
fun describeString(maybeString: String?): String {              // 1
    if (maybeString != null && maybeString.length > 0) {        // 2
        return "String of length ${maybeString.length}"
    } else {
        return "Empty or null string"                           // 3
    }
}
```

1. A função recebe uma String?(potencialmente nula) e retorna sua descrição
2. Se a String informada não for nula ou vazia, retorna então a informação sobre o seu tamanho.
3. Contudo, caso seja nulo será retornada que a String é vazia ou nula

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

---

### Functions with vararg Parameters

Varargs allow you to pass any number of arguments by separating them with commas.

```kotlin
fun printAll(vararg messages: String) {                            // 1
    for (m in messages) println(m)
}

printAll("Hello", "Hallo", "Salut", "Hola", "你好")                 // 2
​
fun printAllWithPrefix(vararg messages: String, prefix: String) {  // 3
    for (m in messages) println(prefix + m)
}

printAllWithPrefix(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefix = "Greeting: "                                          // 4
)
​
fun log(vararg entries: String) {
    printAll(*entries)                                             // 5
}

log("Hello", "Hallo", "Salut", "Hola", "你好")
```

1. The vararg modifier turns a parameter into a vararg.
2. This allows calling printAll with any number of string arguments.
3. Thanks to named parameters, you can even add another parameter of the same type after the vararg. This wouldn't be allowed in Java because there's no way to pass a value.
4. Using named parameters, you can set a value to prefix separately from the vararg.
5. At runtime, a vararg is just an array. To pass it along into a vararg parameter, use the special spread operator * that lets you pass in *entries (a vararg of String) instead of entries (an Array<String>).

---

### Variables

Kotlin has powerful type inference. While you can explicitly declare the type of a variable, you'll usually let the compiler do the work by inferring it. Kotlin does not enforce immutability, though it is recommended. In essence use val over var.

```kotlin
var a: String = "initial"  // 1
println(a)
val b: Int = 1             // 2
val c = 3                  // 3
```

1. Declares a mutable variable and initializes it.
2. Declares an immutable variable and initializes it.
3. Declares an immutable variable and initializes it without specifying the type. The compiler infers the type Int.

```kotlin
var e: Int  // 1
println(e)  // 2
```

1. Declares a variable without initialization.
2. An attempt to use the variable causes a compiler error: Variable 'e' must be initialized.

You're free to choose when you initialize a variable, however, it must be initialized before the first read.

```kotlin
val d: Int  // 1
​
if (someCondition()) {
    d = 1   // 2
} else {
    d = 2   // 2
}
​
println(d) // 3
```

1. Declares a variable without initialization.
2. Initializes the variable with different values depending on some condition.
3. Reading the variable is possible because it's already been initialized.

---

### Null Safety

In an effort to rid the world of NullPointerException, variable types in Kotlin don't allow the assignment of null. If you need a variable that can be null, declare it nullable by adding `?` at the end of its type.

```kotlin
var neverNull: String = "This can't be null"            // 1
​
neverNull = null                                        // 2
​
var nullable: String? = "You can keep a null here"      // 3
​
nullable = null                                         // 4
​
var inferredNonNull = "The compiler assumes non-null"   // 5
​
inferredNonNull = null                                  // 6
​
fun strLength(notNull: String): Int {                   // 7
    return notNull.length
}
​
strLength(neverNull)                                    // 8
strLength(nullable)                                     // 9
```

1. Declares a non-null String variable.
2. When trying to assign null to non-nullable variable, a compilation error is produced.
3. Declares a nullable String variable.
4. Sets the null value to the nullable variable. This is OK.
5. When inferring types, the compiler assumes non-null for variables that are initialized with a value.
6. When trying to assign null to a variable with inferred type, a compilation error is produced.
7. Declares a function with a non-null string parameter.
8. Calls the function with a String (non-nullable) argument. This is OK.
9. When calling the function with a String? (nullable) argument, a compilation error is produced.

**Working with Nulls**

Sometimes Kotlin programs need to work with null values, such as when interacting with external Java code or representing a truly absent state. Kotlin provides null tracking to elegantly deal with such situations.

```java
fun describeString(maybeString: String?): String {              // 1
    if (maybeString != null && maybeString.length > 0) {        // 2
        return "String of length ${maybeString.length}"
    } else {
        return "Empty or null string"                           // 3
    }
}
```

1. A function that takes in a nullable string and returns its description.
2. If the given string is not null and not empty, return information about its length.
3. Otherwise, tell the caller that the string is empty or null.
