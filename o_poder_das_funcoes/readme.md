# O Poder das Funções em Kotlin🇧🇷

### Objetivo

Avançar os estudos em conceitos mais avançados da linguagem de programação Kotlin, explorando funções de escopo e novos tipos.

#### Funções de Escopo

- let
- run
- with
- apply
- also

#### Alguns Tipos de Funções

- **Infix** Functions
- **Operator** Functions
- **Higher-Order** Funcions
- **Lambda** Funcions
- **Extensions**
- **Suspend** Functions (Coroutines)

---

### let

A função da biblioteca padrão Kotlin let pode ser usada para escopo e verificações de nulos. Quando chamado em um objeto, let executa o bloco de código fornecido e retorna o resultado de sua última expressão.

O objeto é acessível dentro do bloco pela referência a ele (por padrão) ou por um nome personalizado.

```kotlin
fun customPrint(s: String) {
    print(s.uppercase())
}

val empty = "test".let {               // 1
    customPrint(it)                    // 2
    it.isEmpty()                       // 3
}

println(" is empty: $empty")
​
​
fun printNonNull(str: String?) {
    println("Printing \"$str\":")
​
    str?.let {                         // 4
        print("\t")
        customPrint(it)
        println()
    }
}
​
fun printIfBothNonNull(strOne: String?, strTwo: String?) {
    strOne?.let { firstString ->       // 5
        strTwo?.let { secondString ->
            customPrint("$firstString : $secondString")
            println()
        }
    }
}
​
printNonNull(null)
printNonNull("my string")
printIfBothNonNull("First","Second")
```

1. Chama o bloco fornecido com base no resultado da string "teste".
2. Chama a função em "test" pela referência it.
3. `let` retorna o valor desta expressão.
4. Usa chamada segura, então `let` e seu bloco de código serão executados apenas em valores não nulos.
5. Usa o nome personalizado em vez dele, para que o `let` aninhado possa acessar o objeto de contexto do let externo.

---

### run

Assim como `let`, run é outra função de escopo da biblioteca padrão. Basicamente, faz a mesma coisa: executa um bloco de código e retorna seu resultado. A diferença é que dentro do run o objeto é acessado por this.

Isso é útil quando você deseja chamar os métodos do objeto em vez de passá-lo como argumento.

```kotlin
fun getNullableLength(ns: String?) {
    println("for \"$ns\":")

    ns?.run {                                                  // 1
        println("\tis empty? " + isEmpty())                    // 2
        println("\tlength = $length")
        length                                                 // 3
    }
}

getNullableLength(null)
getNullableLength("")
getNullableLength("some string with Kotlin")
```

1. Chama o bloco fornecido em uma variável anulável.
2. Dentro de `run`, os membros do objeto são acessados sem seu nome.
3. `run` retorna o comprimento da String fornecida se não for nula.

---

### with

`with` é uma função sem extensão que pode acessar membros de seu argumento de forma concisa: você pode omitir o nome da instância ao se referir a seus membros.

```kotlin
class Configuration(val host: String, val port: Int)

fun main() {
    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration) {
        println("$host:$port")
    }
​
    // instead of:
    println("${configuration.host}:${configuration.port}")
}
```

---

### apply

`apply` executa um bloco de código em um objeto e retorna o próprio objeto. Dentro do bloco, o objeto é referenciado por this. Esta função é útil para inicializar objetos.

```kotlin
data class Person(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun main() {
    val jake = Person()                         // 1
    val stringDescription = jake.apply {        // 2
        name = "Jake"                           // 3
        age = 30
        about = "Android developer"
    }
    .toString()                                 // 4

    println(stringDescription)
}
```

1. Cria uma instância Person() com valores de propriedade padrão.
2. Aplica o bloco de código (próximas 3 linhas) à instância.
3. Dentro de apply, é equivalente a jake.name = "Jake".
4. O valor de retorno é a própria instância, para que você possa encadear outras operações.

---

### also

`also` funciona como `apply`: executa um determinado bloco e retorna o objeto chamado. Dentro do bloco, o objeto é referenciado por ele, então é mais fácil passá-lo como argumento. Esta função é útil para incorporar ações adicionais, como registrar em cadeias de chamadas.

```kotlin
fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created")
}

val jake = Person("Jake", 30, "Android developer")   // 1
    .also {                                          // 2
        writeCreationLog(it)                         // 3
    }
```

1. Cria um objeto Person() com os valores de propriedade fornecidos.
2. Aplica o bloco de código fornecido ao objeto. O valor de retorno é o próprio objeto.
3. Chama a função de registro passando o objeto como argumento.

---

### Infix Functions

Funções-membro e extensões com um único parâmetro podem ser transformadas em funções Infix.

```kotlin
fun main() {
​
  infix fun Int.times(str: String) = str.repeat(this)        // 1

  println(2 times "Bye ")                                    // 2
​
  val pair = "Ferrari" to "Katrina"                          // 3
  println(pair)
​
  infix fun String.onto(other: String) = Pair(this, other)   // 4

  val myPair = "McLaren" onto "Lucas"

  println(myPair)
​
  val sophia = Person("Sophia")
  val claudia = Person("Claudia")

  sophia likes claudia                                       // 5
}
​
class Person(val name: String) {
  val likedPeople = mutableListOf<Person>()

  infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}
```

1. Define uma função de extensão `infix` em Int.
2. Chama a função `infix`.
3. Cria um par chamando a função `infix` da biblioteca padrão.
4. Aqui está sua própria implementação de ser invocado de forma criativa.
5. A notação `infix` também funciona em funções membros (métodos).
6. A classe que contém se torna o primeiro parâmetro.

Observe que o exemplo usa funções locais (funções aninhadas em outra função).

---

### Operator Functions

Certas funções podem ser “atualizadas” pelas operadoras, permitindo suas chamadas com o símbolo da operadora correspondente.

```kotlin
operator fun Int.times(str: String) = str.repeat(this)       // 1

println(2 * "Bye ")                                          // 2
​
operator fun String.get(range: IntRange) = substring(range)  // 3

val str = "Always forgive your enemies; nothing annoys them so much."

println(str[0..14])                                          // 4
```

1. Isso leva a função infix acima um passo adiante usando o modificador do operador.
2. O símbolo do operador para times() é _ para que você possa chamar a função usando 2 _ "Tchau".
3. Uma função de operador permite fácil acesso ao intervalo em strings.
4. O operador get() habilita a sintaxe de acesso a colchetes.

---

### Higher-Order Functions

Uma função de ordem superior é uma função que recebe outra função como parâmetro e/ou retorna uma função.

#### Recebendo funções como parâmetros

```kotlin
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {  // 1
    return operation(x, y)                                          // 2
}
​
fun sum(x: Int, y: Int) = x + y                                     // 3
​
fun main() {
    val sumResult = calculate(4, 5, ::sum)                          // 4
    val mulResult = calculate(4, 5) { a, b -> a * b }               // 5
    println("sumResult $sumResult, mulResult $mulResult")
}
```

1. Declara uma função de ordem superior. São necessários dois parâmetros inteiros, x e y. Além disso, leva outra operação de função como parâmetro. Os parâmetros de operação e o tipo de retorno também são definidos na declaração.
2. A função de ordem superior retorna o resultado da invocação da operação com os argumentos fornecidos.
3. Declara uma função que corresponde à assinatura da operação.
4. Invoca a função de ordem superior passando dois valores inteiros e o argumento da função ::sum. :: é a notação que faz referência a uma função pelo nome em Kotlin.
5. Invoca a função de ordem superior passando um lambda como argumento de função. Parece mais claro, não é?

#### Retornando Funções

```kotlin
fun operation(): (Int) -> Int {                                     // 1
    return ::square
}
​
fun square(x: Int) = x * x                                          // 2
​
fun main() {
    val func = operation()                                          // 3
    println(func(2))                                                // 4
}
```

1. Declara uma função de ordem superior que retorna uma função. (Int) -> Int representa os parâmetros e o tipo de retorno da função square.
2. Declara uma função que corresponde à assinatura.
3. Invoca a operação para obter o resultado atribuído a uma variável. Aqui func torna-se quadrado que é retornado pela operação.
4. Invoca função. A função square é realmente executada.

---

### Funções Lambda

Funções lambda ("lambdas") são uma maneira simples de criar funções ad-hoc. Lambdas podem ser denotados de forma muito concisa em muitos casos, graças à inferência de tipo e à variável it implícita.

```kotlin
// Todos os exemplos criam um objeto de função que executa letras maiúsculas.
//Então é uma função de String para String
​
val upperCase1: (String) -> String = { str: String -> str.uppercase() } // 1
​
val upperCase2: (String) -> String = { str -> str.uppercase() }         // 2
​
val upperCase3 = { str: String -> str.uppercase() }                     // 3
​
// val upperCase4 = { str -> str.uppercase() }                          // 4
​
val upperCase5: (String) -> String = { it.uppercase() }                 // 5
​
val upperCase6: (String) -> String = String::uppercase                  // 6
​
println(upperCase1("hello"))
println(upperCase2("hello"))
println(upperCase3("hello"))
println(upperCase5("hello"))
println(upperCase6("hello"))
```

1. Um lambda em toda a sua glória, com tipos explícitos em todos os lugares. O lambda é a parte entre chaves, que é atribuída a uma variável do tipo (String) -> String (um tipo de função).
2. Inferência de tipo dentro de lambda: o tipo do parâmetro lambda é inferido do tipo da variável à qual está atribuído.
3. Inferência de tipo fora do lambda: o tipo da variável é inferido a partir do tipo do parâmetro lambda e do valor de retorno.
4. Você não pode fazer as duas coisas juntas, o compilador não tem chance de inferir o tipo dessa maneira.
5. Para lambdas com um único parâmetro, não é necessário nomeá-lo explicitamente. Em vez disso, você pode usar a variável it implícita. Isto é especialmente útil quando o tipo pode ser inferido (o que geralmente é o caso).
6. Se o seu lambda consiste em uma única chamada de função, você pode usar ponteiros de função (`::`) .

---

### Funções e propriedades de extensão

Kotlin permite adicionar novos membros a qualquer classe com o mecanismo de extensões. Ou seja, existem dois tipos de extensões: funções de extensão e propriedades de extensão. Eles se parecem muito com funções e propriedades normais, mas com uma diferença importante: você precisa especificar o tipo que deseja estender.

```kotlin
data class Item(val name: String, val price: Float)                                         // 1
​
data class Order(val items: Collection<Item>)
​
fun Order.maxPricedItemValue(): Float = this.items.maxByOrNull { it.price }?.price ?: 0F    // 2
fun Order.maxPricedItemName() = this.items.maxByOrNull { it.price }?.name ?: "NO_PRODUCTS"
​
val Order.commaDelimitedItemNames: String                                                   // 3
    get() = items.map { it.name }.joinToString()
​
fun main() {
​
    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))

    println("Max priced item name: ${order.maxPricedItemName()}")                           // 4
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")                                      // 5
​
}
```

1. Define modelos simples de Item e Pedido. Order pode conter uma coleção de objetos Item.
2. Adiciona funções de extensão para o tipo de pedido.
3. Adiciona uma propriedade de extensão para o tipo de pedido.
4. Chama funções de extensão diretamente em uma instância de Order.
5. Acessa a propriedade de extensão em uma instância de Order.

É ainda possível executar extensões em referências nulas. Em uma função de extensão, você pode verificar se há nulo no objeto e usar o resultado em seu código:

```kotlin
fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"  // 1
```

---

# The Power of Functions in Kotlin🇺🇸

### Goal

Advance studies in more advanced concepts of the Kotlin programming language, exploring scope functions and new types.

#### Scope Functions

- let
- run
- with
- apply
- also

#### Some Types of Functions

- **Infix** Functions
- **Operator** Functions
- **Higher-Order** Functions
- **Lambda** Functions
- **Extensions**
- **Suspend** Functions (Coroutines)

---

### let

The Kotlin standard library function let can be used for scoping and null-checks. When called on an object, let executes the given block of code and returns the result of its last expression.

The object is accessible inside the block by the reference it (by default) or a custom name.

```kotlin
fun customPrint(s: String) {
    print(s.uppercase())
}

val empty = "test".let {               // 1
    customPrint(it)                    // 2
    it.isEmpty()                       // 3
}
println(" is empty: $empty")
​
​
fun printNonNull(str: String?) {
    println("Printing \"$str\":")
​
    str?.let {                         // 4
        print("\t")
        customPrint(it)
        println()
    }
}
​
fun printIfBothNonNull(strOne: String?, strTwo: String?) {
    strOne?.let { firstString ->       // 5
        strTwo?.let { secondString ->
            customPrint("$firstString : $secondString")
            println()
        }
    }
}
​
printNonNull(null)
printNonNull("my string")
printIfBothNonNull("First","Second")
```

1. Calls the given block on the result on the string "test".
2. Calls the function on "test" by the it reference.
3. let returns the value of this expression.
4. Uses safe call, so let and its code block will be executed only on non-null values.
5. Uses the custom name instead of it, so that the nested let can access the context object of the outer let.

---

### run

Like `let`, run is another scoping function from the standard library. Basically, it does the same: executes a code block and returns its result. The difference is that inside run the object is accessed by this.

This is useful when you want to call the object's methods rather than pass it as an argument.

```kotlin
fun getNullableLength(ns: String?) {
    println("for \"$ns\":")
    ns?.run {                                                  // 1
        println("\tis empty? " + isEmpty())                    // 2
        println("\tlength = $length")
        length                                                 // 3
    }
}

getNullableLength(null)
getNullableLength("")
getNullableLength("some string with Kotlin")
```

1. Calls the given block on a nullable variable.
2. Inside `run`, the object's members are accessed without its name.
3. `run` returns the length of the given String if it's not null.

---

### with

`with` is a non-extension function that can access members of its argument concisely: you can omit the instance name when referring to its members.

```kotlin
class Configuration(val host: String, val port: Int)

fun main() {
    val configuration = Configuration(host = "127.0.0.1", port = 9000)

    with(configuration) {
        println("$host:$port")
    }
​
    // instead of:
    println("${configuration.host}:${configuration.port}")
}
```

---

### apply

apply executes a block of code on an object and returns the object itself. Inside the block, the object is referenced by this. This function is handy for initializing objects.

```kotlin
data class Person(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun main() {
    val jake = Person()                         // 1
    val stringDescription = jake.apply {        // 2
        name = "Jake"                           // 3
        age = 30
        about = "Android developer"
    }
    .toString()                                 // 4

    println(stringDescription)
}
```

1. Creates a Person() instance with default property values.
2. Applies the code block (next 3 lines) to the instance.
3. Inside apply, it's equivalent to jake.name = "Jake".
4. The return value is the instance itself, so you can chain other operations.

---

### also

also works like apply: it executes a given block and returns the object called. Inside the block, the object is referenced by it, so it's easier to pass it as an argument. This function is handy for embedding additional actions, such as logging in call chains.

```kotlin
fun writeCreationLog(p: Person) {
    println("A new person ${p.name} was created")
}

val jake = Person("Jake", 30, "Android developer")   // 1
    .also {                                          // 2
        writeCreationLog(it)                         // 3
    }
```

1. Creates a Person() object with the given property values.
2. Applies the given code block to the object. The return value is the object itself.
3. Calls the logging function passing the object as an argument.

---

### Infix Functions

Member functions and extensions with a single parameter can be turned into infix functions.

```kotlin
fun main() {
​
  infix fun Int.times(str: String) = str.repeat(this)        // 1
  println(2 times "Bye ")                                    // 2
​
  val pair = "Ferrari" to "Katrina"                          // 3
  println(pair)
​
  infix fun String.onto(other: String) = Pair(this, other)   // 4
  val myPair = "McLaren" onto "Lucas"
  println(myPair)
​
  val sophia = Person("Sophia")
  val claudia = Person("Claudia")
  sophia likes claudia                                       // 5
}
​
class Person(val name: String) {
  val likedPeople = mutableListOf<Person>()
  infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}
```

1. Defines an infix extension function on Int.
2. Calls the infix function.
3. Creates a Pair by calling the infix function to from the standard library.
4. Here's your own implementation of to creatively called onto.
5. Infix notation also works on members functions (methods).
6. The containing class becomes the first parameter.

Note that the example uses local functions (functions nested within another function).

---

### Operator Functions

Certain functions can be "upgraded" to operators, allowing their calls with the corresponding operator symbol.

```kotlin
operator fun Int.times(str: String) = str.repeat(this)       // 1

println(2 * "Bye ")                                          // 2
​
operator fun String.get(range: IntRange) = substring(range)  // 3

val str = "Always forgive your enemies; nothing annoys them so much."

println(str[0..14])                                          // 4
```

1. This takes the infix function from above one step further using the operator modifier.
2. The operator symbol for times() is _ so that you can call the function using 2 _ "Bye".
3. An operator function allows easy range access on strings.
4. The get() operator enables bracket-access syntax.

---

### Higher-Order Functions

A higher-order function is a function that takes another function as parameter and/or returns a function.

#### Taking Functions as Parameters

```kotlin
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {  // 1
    return operation(x, y)                                          // 2
}
​
fun sum(x: Int, y: Int) = x + y                                     // 3
​
fun main() {
    val sumResult = calculate(4, 5, ::sum)                          // 4
    val mulResult = calculate(4, 5) { a, b -> a * b }               // 5
    println("sumResult $sumResult, mulResult $mulResult")
}
```

1. Declares a higher-order function. It takes two integer parameters, x and y. Additionally, it takes another function operation as a parameter. The operation parameters and return type are also defined in the declaration.
2. The higher order function returns the result of operation invocation with the supplied arguments.
3. Declares a function that matches the operationsignature.
4. Invokes the higher-order function passing in two integer values and the function argument ::sum. :: is the notation that references a function by name in Kotlin.
5. Invokes the higher-order function passing in a lambda as a function argument. Looks clearer, doesn't it?

#### Returning Functions

```kotlin
fun operation(): (Int) -> Int {                                     // 1
    return ::square
}
​
fun square(x: Int) = x * x                                          // 2
​
fun main() {
    val func = operation()                                          // 3
    println(func(2))                                                // 4
}
```

1. Declares a higher-order function that returns a function. (Int) -> Int represents the parameters and return type of the square function.
2. Declares a function matching the signature.
3. Invokes operation to get the result assigned to a variable. Here func becomes square which is returned by operation.
4. Invokes func. The square function is actually executed.

---

### Lambda Functions

Lambda functions ("lambdas") are a simple way to create functions ad-hoc. Lambdas can be denoted very concisely in many cases thanks to type inference and the implicit it variable.

```kotlin
// All examples create a function object that performs upper-casing.
// So it's a function from String to String
​
val upperCase1: (String) -> String = { str: String -> str.uppercase() } // 1
​
val upperCase2: (String) -> String = { str -> str.uppercase() }         // 2
​
val upperCase3 = { str: String -> str.uppercase() }                     // 3
​
// val upperCase4 = { str -> str.uppercase() }                          // 4
​
val upperCase5: (String) -> String = { it.uppercase() }                 // 5
​
val upperCase6: (String) -> String = String::uppercase                  // 6
​
println(upperCase1("hello"))
println(upperCase2("hello"))
println(upperCase3("hello"))
println(upperCase5("hello"))
println(upperCase6("hello"))
```

1. A lambda in all its glory, with explicit types everywhere. The lambda is the part in curly braces, which is assigned to a variable of type (String) -> String (a function type).
2. Type inference inside lambda: the type of the lambda parameter is inferred from the type of the variable it's assigned to.
3. Type inference outside lambda: the type of the variable is inferred from the type of the lambda parameter and return value.
4. You cannot do both together, the compiler has no chance to infer the type that way.
5. For lambdas with a single parameter, you don't have to explicitly name it. Instead, you can use the implicit it variable. This is especially useful when the type of it can be inferred (which is often the case).
6. If your lambda consists of a single function call, you may use function pointers (`::`) .

---

### Extension Functions and Properties

Kotlin lets you add new members to any class with the extensions mechanism. Namely, there are two types of extensions: extension functions and extension properties. They look a lot like normal functions and properties but with one important difference: you need to specify the type that you extend.

```kotlin
data class Item(val name: String, val price: Float)                                         // 1
​
data class Order(val items: Collection<Item>)
​
fun Order.maxPricedItemValue(): Float = this.items.maxByOrNull { it.price }?.price ?: 0F    // 2
fun Order.maxPricedItemName() = this.items.maxByOrNull { it.price }?.name ?: "NO_PRODUCTS"
​
val Order.commaDelimitedItemNames: String                                                   // 3
    get() = items.map { it.name }.joinToString()
​
fun main() {
​
    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))

    println("Max priced item name: ${order.maxPricedItemName()}")                           // 4
    println("Max priced item value: ${order.maxPricedItemValue()}")
    println("Items: ${order.commaDelimitedItemNames}")                                      // 5
​
}
```

1. Defines simple models of Item and Order. Order can contain a collection of Item objects.
2. Adds extension functions for the Order type.
3. Adds an extension property for the Order type.
4. Calls extension functions directly on an instance of Order.
5. Accesses the extension property on an instance of Order.

It is even possible to execute extensions on null references. In an extension function, you can check the object for null and use the result in your code:

```kotlin
fun <T> T?.nullSafeToString() = this?.toString() ?: "NULL"  // 1
```

---
