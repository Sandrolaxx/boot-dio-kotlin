# Estruturas de Controle e Coleções🇧🇷

### Objetivo

Trazer elementos das estruturas de repetição e condicionais, além de conhecer algumas das principais coleções do Kotlin.

#### Estruturas de Controle

* When
* Loops
* Ranges
* Verificação de igualdade
* Expressões condicionais

#### Coleções🇧🇷

*  List
*  Set
*  Map
*  Extension Functions(filter, map, flatMap etc)

---

### When

Em vez da instrução switch amplamente usada, o Kotlin fornece uma forma mais flexível e clara durante a construção. Pode ser usado como uma declaração ou como uma expressão. 

##### Declaração `when`: 

```kotlin
fun main() {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(MyClass())
    cases("hello")
}
​
fun cases(obj: Any) {                                
    when (obj) {                                     // 1   
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }   
}

class MyClass
```

1. Esta é uma declaração `when`.
2. Verifica se obj é igual a 1.
3. Verifica se obj é igual a "Hello".
4. Executa verificação de tipo.
5. Executa verificação de tipo inversa.
6. Instrução padrão (pode ser omitida).

Observe que todas as condições de ramificação são verificadas sequencialmente até que uma delas seja satisfeita. Assim, apenas a primeira ramificação adequada será executada. 

#### Expressão `when`:

```kotlin
fun main() {
    println(whenAssign("Hello"))
    println(whenAssign(3.4))
    println(whenAssign(1))
    println(whenAssign(MyClass()))
}
​
fun whenAssign(obj: Any): Any {
    val result = when (obj) {                   // 1
        1 -> "one"                              // 2
        "Hello" -> 1                            // 3
        is Long -> false                        // 4
        else -> 42                              // 5
    }
    return result
}
```

1. Esta é uma expressão quando.
2. Define o valor como "um" se obj for igual a 1.
3. Define o valor como um se obj for igual a "Hello".
4. Define o valor como falso se obj for uma instância de Long.
5. Define o valor 42 se nenhuma das condições anteriores for satisfeita. Ao contrário da instrução in when, a ramificação padrão geralmente é necessária na expressão when, exceto no caso em que o compilador pode verificar se outras ramificações cobrem todos os casos possíveis.

---

### Loops

Kotlin suporta as principais formas de loops: `for`, `while`, `do-while`. 

#### for

`for` em Kotlin funciona como na maioria das linguagens.

```kotlin
val cakes = listOf("carrot", "cheese", "chocolate")
​
for (cake in cakes) {                               // 1
    println("Yummy, it's a $cake cake!")
}
```

1. Iterando sobre `cakes` para cada bolo na lista.

#### while and do-while

As construções while e do-while também funcionam de maneira semelhante à maioria das linguagens.

```kotlin
fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")
​
fun main() {
    var cakesEaten = 0
    var cakesBaked = 0
    
    while (cakesEaten < 5) {                    // 1
        eatACake()
        cakesEaten ++
    }
    
    do {                                        // 2
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)
​
}
```

1. Executa o bloco enquanto a condição for verdadeira.
2. Executa primeiro o bloco e depois verifica a condição.

#### Iterators

Você pode definir seus próprios iteradores em suas classes implementando o operador iterador nelas.

```kotlin
class Animal(val name: String)
​
class Zoo(val animals: List<Animal>) {
​
    operator fun iterator(): Iterator<Animal> {             // 1
        return animals.iterator()                           // 2
    }
}
​
fun main() {
​
    val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))
​
    for (animal in zoo) {                                   // 3
        println("Watch out, it's a ${animal.name}")
    }
​
}
```

1. Define um iterador em uma classe. Deve ser nomeado iterador e ter o modificador de operador.
2. Retorna o iterador que atende aos seguintes requisitos do método:
* next(): Animal
* hasNext(): Booleano
3. Percorre os animals do zoo com o iterador definido pelo usuário.

O iterador pode ser declarado no tipo ou como uma função de extensão.

---

### Ranges

Existe um conjunto de ferramentas para definir intervalos em Kotlin. Vamos dar uma breve olhada neles.

```kotlin
for(i in 0..3) {             // 1
    print(i)
}
print(" ")
​
for(i in 0 until 3) {        // 2
    print(i)
}
print(" ")
​
for(i in 2..8 step 2) {      // 3
    print(i)
}
print(" ")
​
for (i in 3 downTo 0) {      // 4
    print(i)
}
print(" ")
```

1. Itera em um intervalo que vai de 0 a 3 (inclusive). Como 'for(i=0; i<=3; ++i)' em outras linguagens de programação (C/C++/Java).
2. Itera em um intervalo de 0 a 3 (exclusivo). Como for loop em Python ou como 'for(i=0; i<3; ++i)' em outras linguagens de programação (C/C++/Java).
3. Itera em um intervalo com uma etapa de incremento personalizada para elementos consecutivos.
4. Itera em um intervalo na ordem inversa.

Os intervalos de caracteres também são suportados:

```kotlin
for (c in 'a'..'d') {              // 1
    print(c)
}

print(" ")
​
for (c in 'z' downTo 's' step 2) { // 2
    print(c)
}

print(" ")
```

1. Itera em um intervalo de caracteres em ordem alfabética.
2. Os intervalos de caracteres também suportam step e downTo.

Os intervalos também são úteis em instruções if:

```kotlin
val x = 2
if (x in 1..5) {             // 1
    print("x is in range from 1 to 5")
}
println()
​
if (x !in 6..10) {          // 2
    print("x is not in range from 6 to 10")
}
```

1. Verifica se um valor está no intervalo.
2. `!in` é o oposto de `in`.

---

### Equality Checks

Kotlin usa `==` para comparação estrutural e `===` para comparação referencial.
Mais precisamente, `a == b` compila para `if (a == null) b == null else a.equals(b)`.
​
```kotlin
val authors = setOf("Shakespeare", "Hemingway", "Twain")
val writers = setOf("Twain", "Shakespeare", "Hemingway")
​
println(authors == writers)   // 1
println(authors === writers)  // 2
```

1. Retorna verdadeiro porque chama authors.equals(writers) e define para ignorar a ordem dos elementos.
2. Retorna falso porque authors e writers são referências distintas.

---

### Conditional Expression

Não há operador ternário `condição? então: else` em Kotlin. Em vez disso, `if` pode ser usado como uma expressão:

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b         // 1
​
println(max(99, -42))
```

1. `if` é uma expressão aqui: retorna um valor.

---

# Control Flow🇺🇸

### Goal

Bring elements of repetition and conditional structures, in addition to getting to know some of Kotlin's main collections.

#### Control flow

* When
* Loops
* Ranges
* Equality check
* Conditional expressions

#### Collections

*  List
*  Set
*  Map
*  Extension Functions(filter, map, flatMap etc)

---

### When

Instead of the widely used switch statement, Kotlin provides the more flexible and clear when construction. It can be used either as a statement or as an expression. 

#### When Statement: 

```kotlin
fun main() {
    cases("Hello")
    cases(1)
    cases(0L)
    cases(MyClass())
    cases("hello")
}
​
fun cases(obj: Any) {                                
    when (obj) {                                     // 1   
        1 -> println("One")                          // 2
        "Hello" -> println("Greeting")               // 3
        is Long -> println("Long")                   // 4
        !is String -> println("Not a string")        // 5
        else -> println("Unknown")                   // 6
    }   
}

class MyClass
```

1. This is a when statement.
2. Checks whether obj equals to 1.
3. Checks whether obj equals to "Hello".
4. Performs type checking.
5. Performs inverse type checking.
6. Default statement (might be omitted).

Note that all branch conditions are checked sequentially until one of them is satisfied. So, only the first suitable branch will be executed.

#### When Expression:

```kotlin
fun main() {
    println(whenAssign("Hello"))
    println(whenAssign(3.4))
    println(whenAssign(1))
    println(whenAssign(MyClass()))
}
​
fun whenAssign(obj: Any): Any {
    val result = when (obj) {                   // 1
        1 -> "one"                              // 2
        "Hello" -> 1                            // 3
        is Long -> false                        // 4
        else -> 42                              // 5
    }
    return result
}
```

1. This is a when expression.
2. Sets the value to "one" if obj equals to 1.
3. Sets the value to one if obj equals to "Hello".
4. Sets the value to false if obj is an instance of Long.
5. Sets the value 42 if none of the previous conditions are satisfied. Unlike in when statement, the default branch is usually required in when expression, except the case when the compiler can check that other branches cover all possible cases.

---

### Loops

Kotlin supports all the commonly used loops: `for`, `while`, `do-while`. 

#### for

`for` in Kotlin works the same way as in most languages.

```kotlin
val cakes = listOf("carrot", "cheese", "chocolate")
​
for (cake in cakes) {                               // 1
    println("Yummy, it's a $cake cake!")
}
```

1. Loops through each cake in the list.

#### while and do-while

while and do-while constructs work similarly to most languages as well.

```kotlin
fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")
​
fun main() {
    var cakesEaten = 0
    var cakesBaked = 0
    
    while (cakesEaten < 5) {                    // 1
        eatACake()
        cakesEaten ++
    }
    
    do {                                        // 2
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)
​
}
```

1. Executes the block while the condition is true.
2. Executes the block first and then checking the condition.

#### Iterators

You can define your own iterators in your classes by implementing the iterator operator in them.

```kotlin
class Animal(val name: String)
​
class Zoo(val animals: List<Animal>) {
​
    operator fun iterator(): Iterator<Animal> {             // 1
        return animals.iterator()                           // 2
    }
}
​
fun main() {
​
    val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))
​
    for (animal in zoo) {                                   // 3
        println("Watch out, it's a ${animal.name}")
    }
​
}
```

1. Defines an iterator in a class. It must be named iterator and have the operator modifier.
2. Returns the iterator that meets the following method requirements:
* next(): Animal
* hasNext(): Boolean
3. Loops through animals in the zoo with the user-defined iterator.

The iterator can be declared in the type or as an extension function.

---

### Ranges

There is a set of tools for defining ranges in Kotlin. Let's have a brief look at them.

```kotlin
for(i in 0..3) {             // 1
    print(i)
}
print(" ")
​
for(i in 0 until 3) {        // 2
    print(i)
}
print(" ")
​
for(i in 2..8 step 2) {      // 3
    print(i)
}
print(" ")
​
for (i in 3 downTo 0) {      // 4
    print(i)
}
print(" ")
```

1. Iterates over a range starting from 0 up to 3 (inclusive). Like 'for(i=0; i<=3; ++i)' in other programming languages (C/C++/Java).
2. Iterates over a range starting from 0 up to 3 (exclusive). Like for loop in Python or like 'for(i=0; i<3; ++i)' in other programming languages (C/C++/Java).
3. Iterates over a range with a custom increment step for consecutive elements.
4. Iterates over a range in reverse order.

Char ranges are also supported:

```kotlin
for (c in 'a'..'d') {              // 1
    print(c)
}

print(" ")
​
for (c in 'z' downTo 's' step 2) { // 2
    print(c)
}

print(" ")
```

1. Iterates over a char range in alphabetical order.
2. Char ranges support step and downTo as well.
Ranges are also useful in if statements:

```kotlin
val x = 2
if (x in 1..5) {             // 1
    print("x is in range from 1 to 5")
}
println()
​
if (x !in 6..10) {          // 2
    print("x is not in range from 6 to 10")
}
```

1. Checks if a value is in the range.
2. `!in` is the opposite of `in`.

---

### Equality Checks

Kotlin uses `==` for structural comparison and `===` for referential comparison.
More precisely, `a == b` compiles down to `if (a == null) b == null else a.equals(b)`.
​
```kotlin
val authors = setOf("Shakespeare", "Hemingway", "Twain")
val writers = setOf("Twain", "Shakespeare", "Hemingway")
​
println(authors == writers)   // 1
println(authors === writers)  // 2
```

1. Returns true because it calls authors.equals(writers) and sets ignore element order.
2. Returns false because authors and writers are distinct references.

---

### Conditional Expression

There is no ternary operator `condition ? then : else` in Kotlin. Instead, `if` may be used as an expression:

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b         // 1
​
println(max(99, -42))
```

1. if is an expression here: it returns a value.