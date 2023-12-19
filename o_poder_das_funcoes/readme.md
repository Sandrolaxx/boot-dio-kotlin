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