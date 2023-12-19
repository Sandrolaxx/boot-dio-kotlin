# Estruturas de Controle e Coleções🇧🇷

## Estruturas de Controle

### Objetivo

Trazer elementos das estruturas de repetição e condicionais, além de conhecer algumas das principais coleções do Kotlin.

#### Estruturas de Controle

- When
- Loops
- Ranges
- Verificação de igualdade
- Expressões condicionais

#### Coleções🇧🇷

- List
- Set
- Map
- Extension Functions(filter, map, flatMap etc)

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

- next(): Animal
- hasNext(): Booleano

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

## Coleções

### List

Uma lista é uma coleção ordenada de itens. No Kotlin, as listas podem ser mutáveis (MutableList) ou somente leitura (List). Para criação de listas, use as funções de biblioteca padrão listOf() para listas somente leitura e mutableListOf() para listas mutáveis. Para evitar modificações indesejadas, obtenha visualizações somente leitura de listas mutáveis convertendo-as em List.

```kotlin
val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1
val sudoers: List<Int> = systemUsers                              // 2
​
fun addSystemUser(newUser: Int) {                                 // 3
    systemUsers.add(newUser)
}
​
fun getSysSudoers(): List<Int> {                                  // 4
    return sudoers
}
​
fun main() {
    addSystemUser(4)                                              // 5
    println("Tot sudoers: ${getSysSudoers().size}")               // 6
    getSysSudoers().forEach {                                     // 7
        i -> println("Some useful info on user $i")
    }
    // getSysSudoers().add(5) <- Error!                           // 8
}
```

1. Cria uma MutableList.
2. Cria uma visualização somente leitura da lista.
3. Adiciona um novo item à MutableList.
4. Uma função que retorna uma lista imutável.
5. Atualiza o MutableList. Todas as visualizações somente leitura relacionadas também são atualizadas, pois apontam para o mesmo objeto.
6. Recupera o tamanho da lista somente leitura.
7. Itera a lista e imprime seus elementos.
8. A tentativa de gravar na visualização somente leitura causa um erro de compilação.

---

### Set

Um Set é uma coleção não ordenada que não suporta duplicatas. Para criar conjuntos, existem as funções setOf() e mutableSetOf(). Uma visualização somente leitura de um Set mutável pode ser obtida convertendo-o em Set.

```kotlin
val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3") // 1
​
fun addIssue(uniqueDesc: String): Boolean {
    return openIssues.add(uniqueDesc)                                                             // 2
}
​
fun getStatusLog(isAdded: Boolean): String {
    return if (isAdded) "registered correctly." else "marked as duplicate and rejected."          // 3
}
​
fun main() {
    val aNewIssue: String = "uniqueDescr4"
    val anIssueAlreadyIn: String = "uniqueDescr2"
​
    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")                              // 4
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")                // 5
}
```

1. Cria um Set com determinados elementos.
2. Retorna um valor booleano que mostra se o elemento foi realmente adicionado.
3. Retorna uma string, com base no parâmetro de entrada da função.
4. Imprime uma mensagem de sucesso: o novo elemento é adicionado ao Set.
5. Imprime uma mensagem de falha: o elemento não pode ser adicionado porque duplica um elemento existente.

---

### Map

Um map é uma coleção de pares chave/valor, onde cada chave é única e é usada para recuperar o valor correspondente. Para criar mapas, existem funções mapOf() e mutableMapOf(). Usar a função to infix torna a inicialização menos complicada. Uma visualização somente leitura de um mapa mutável pode ser obtida convertendo-o em Map.

```kotlin
const val POINTS_X_PASS: Int = 15
val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1
val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2
​
fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)) {                                        // 3
        println("Updating $accountId...")

        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // 4
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}
​
fun accountsReport() {
    println("EZ-Pass report:")

    EZPassReport.forEach {                                                              // 5
        k, v -> println("ID $k: credit $v")
    }
}
​
fun main() {
    accountsReport()                                                                    // 6
    updatePointsCredit(1)                                                               // 7
    updatePointsCredit(1)
    updatePointsCredit(5)                                                               // 8
    accountsReport()                                                                    // 9
}
```

1. Cria um mapa mutável.
2. Cria uma visualização somente leitura do Mapa.
3. Verifica se a chave do Mapa existe.
4. Lê o valor correspondente e incrementa-o com um valor constante.
5. Itera mapa imutável e imprime pares chave/valor.
6. Lê o saldo de pontos da conta, antes das atualizações.
7. Atualiza uma conta existente duas vezes.
8. Tenta atualizar uma conta inexistente: imprime uma mensagem de erro.
9. Lê o saldo de pontos da conta, após atualizações.

---

### Useful functions

#### filter

A função filter permite filtrar coleções. É necessário um predicado de filtro como parâmetro lambda. O predicado é aplicado a cada elemento. Os elementos que tornam o predicado verdadeiro são retornados na coleção de resultados.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1
​
val positives = numbers.filter { x -> x > 0 }  // 2
​
val negatives = numbers.filter { it < 0 }      // 3
```

1. Define coleção de números.
2. Obtém números positivos.
3. Usa a notação `it` mais curta para obter números negativos.

#### map

A função de extensão de map permite aplicar uma transformação a todos os elementos de uma coleção. É necessária uma função de transformador como parâmetro lambda.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1
​
val doubled = numbers.map { x -> x * 2 }      // 2
​
val tripled = numbers.map { it * 3 }          // 3
```

1. Define uma coleção de números.
2. Duplica números.
3. Usa a notação it mais curta para triplicar os números.

#### any, all, none

Estas funções verificam a existência de elementos de coleção que correspondem a um determinado predicado.

Função any

A função any retorna verdadeiro se a coleção contiver pelo menos um elemento que corresponda ao predicado fornecido.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val anyNegative = numbers.any { it < 0 }             // 2
​
val anyGT6 = numbers.any { it > 6 }                  // 3
```

1. Define uma coleção de números.
2. Verifica se existem elementos negativos.
3. Verifica se existem elementos maiores que 6.

Funcionar all

A função all retorna verdadeiro se todos os elementos da coleção corresponderem ao predicado fornecido.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val allEven = numbers.all { it % 2 == 0 }            // 2
​
val allLess6 = numbers.all { it < 6 }                // 3
```

1. Define uma coleção de números.
2. Verifica se todos os elementos são pares.
3. Verifica se todos os elementos são menores que 6.

Função none

A função none retorna verdadeiro se não houver elementos que correspondam ao predicado fornecido na coleção.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val allEven = numbers.none { it % 2 == 1 }           // 2
​
val allLess6 = numbers.none { it > 6 }               // 3
```

1. Define uma coleção de números.
2. Verifica se não existem elementos ímpares (todos os elementos são pares).
3. Verifica se não existem elementos maiores que 6.

#### find, findLast

As funções find e findLast retornam o primeiro ou o último elemento da coleção que corresponde ao predicado fornecido. Se não existirem tais elementos, essas funções retornarão nulo.

```kotlin
val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1
​
val first = words.find { it.startsWith("some") }                                // 2
val last = words.findLast { it.startsWith("some") }                             // 3
​
val nothing = words.find { it.contains("nothing") }                             // 4
```

1. Define uma coleção de palavras.
2. Procura a primeira palavra que começa com “alguns”.
3. Procura a última palavra começando com “alguns”.
4. Procura a primeira palavra que contém “nada”.

#### first, last

first, last

Essas funções retornam o primeiro e o último elemento da coleção correspondentemente. Você também pode usá-los com um predicado; neste caso, eles retornam o primeiro ou o último elemento que corresponde ao predicado fornecido.

Se uma coleção estiver vazia ou não contiver elementos que correspondam ao predicado, as funções lançarão NoSuchElementException.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val first = numbers.first()                          // 2
val last = numbers.last()                            // 3
​
val firstEven = numbers.first { it % 2 == 0 }        // 4
val lastOdd = numbers.last { it % 2 != 0 }           // 5
```

1. Define uma coleção de números.
2. Seleciona o primeiro elemento.
3. Seleciona o último elemento.
4. Seleciona o primeiro elemento par.
5. Escolhe o último elemento ímpar.

firstOrNull, lastOrNull

Essas funções funcionam quase da mesma maneira, com uma diferença: elas retornam nulo se não houver elementos correspondentes.

```kotlin
val words = listOf("foo", "bar", "baz", "faz")         // 1
val empty = emptyList<String>()                        // 2
​
val first = empty.firstOrNull()                        // 3
val last = empty.lastOrNull()                          // 4
​
val firstF = words.firstOrNull { it.startsWith('f') }  // 5
val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
val lastF = words.lastOrNull { it.endsWith('f') }      // 7
val lastZ = words.lastOrNull { it.endsWith('z') }      // 8
```

1. Define uma coleção de palavras.
2. Define uma coleção vazia.
3. Seleciona o primeiro elemento da coleção vazia. Era para ser nulo.
4. Seleciona o último elemento da coleção vazia. Deveria ser nulo também.
5. Escolhe a primeira palavra que começa com 'f'.
6. Escolhe a primeira palavra que começa com 'z'.
7. Escolhe a última palavra que termina com 'f'.
8. Escolhe a última palavra que termina com 'z'.

#### count

As funções de contagem retornam o número total de elementos em uma coleção ou o número de elementos que correspondem ao predicado fornecido.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val totalCount = numbers.count()                     // 2
val evenCount = numbers.count { it % 2 == 0 }        // 3
```

1. Define uma coleção de números.
2. Conta o número total de elementos.
3. Conta o número de elementos pares.

#### associateBy, groupBy

As funções associateBy e groupBy constroem mapas a partir dos elementos de uma coleção indexada pela chave especificada. A chave é definida no parâmetro keySelector.

Você também pode especificar um valueSelector opcional para definir o que será armazenado no valor do elemento map.

A diferença entre associateBy e groupBy é como eles processam objetos com a mesma chave:
associadoBy usa o último elemento adequado como valor.

- groupBy cria uma lista de todos os elementos adequados e a coloca no valor.
- O mapa retornado preserva a ordem de iteração de entrada da coleção original.
  ​

```kotlin
data class Person(val name: String, val city: String, val phone: String) // 1
​
val people = listOf(                                                     // 2
    Person("John", "Boston", "+1-888-123456"),
    Person("Sarah", "Munich", "+49-777-789123"),
    Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
    Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))
​
val phoneBook = people.associateBy { it.phone }                          // 3
val cityBook = people.associateBy(Person::phone, Person::city)           // 4
val peopleCities = people.groupBy(Person::city, Person::name)            // 5
val lastPersonCity = people.associateBy(Person::city, Person::name)      // 6
```

1. Define uma classe de dados que descreve uma pessoa.
2. Define um conjunto de pessoas.
3. Constrói um mapa desde os números de telefone até as informações de seus proprietários. it.phone é o keySelector aqui. O valueSelector não é fornecido, portanto os valores do mapa são os próprios objetos Person.
4. Constrói um mapa dos números de telefone até as cidades onde os proprietários moram. Person::city é o valueSelector aqui, então os valores do mapa contêm apenas cidades.
5. Constrói um mapa que contém as cidades e as pessoas que ali vivem. Os valores do mapa são listas de nomes de pessoas.
6. Constrói um mapa que contém as cidades e a última pessoa que mora lá. Os valores do mapa são nomes da última pessoa que morou em cada cidade.

#### partition

A função de partição divide a coleção original em um par de listas usando um determinado predicado:

1. Elementos para os quais o predicado é verdadeiro.
2. Elementos para os quais o predicado é falso.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)                // 1
​
val evenOdd = numbers.partition { it % 2 == 0 }           // 2
val (positives, negatives) = numbers.partition { it > 0 } // 3
```

1. Define uma coleção de números.
2. Divide os números em um par de listas com números pares e ímpares.
3. Divide os números em duas listas com números positivos e negativos. A desestruturação do par é aplicada aqui para obter os membros do par.

#### flatMap

flatMap transforma cada elemento de uma coleção em um objeto iterável e constrói uma única lista dos resultados da transformação. A transformação é definida pelo usuário.

```kotlin
val fruitsBag = listOf("apple","orange","banana","grapes")  // 1
val clothesBag = listOf("shirts","pants","jeans")           // 2
val cart = listOf(fruitsBag, clothesBag)                    // 3
val mapBag = cart.map { it }                                // 4
val flatMapBag = cart.flatMap { it }                        // 5
```

1. Define uma coleção de Strings com nomes de frutas.
2. Define uma coleção de Strings com nomes de roupas.
3. Adiciona FruitBag e ClothesBag à lista do carrinho.
4. Constrói um mapa de elementos do carrinho, que é uma lista de duas listas.
5. Constrói um flatMap de elementos do carrinho, que é uma lista única que consiste em elementos de ambas as listas.

#### minOrNull, maxOrNull

As funções minOrNull e maxOrNull retornam o menor e o maior elemento de uma coleção. Se a coleção estiver vazia, eles retornarão nulo.

```kotlin
val numbers = listOf(1, 2, 3)
val empty = emptyList<Int>()
val only = listOf(3)
​
println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}") // 1
println("Empty: $empty, min = ${empty.minOrNull()}, max = ${empty.maxOrNull()}")        // 2
println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")            // 3
```

1. Para coleção não vazia, as funções retornam o menor e o maior elemento.
2. Para coleções vazias, ambas as funções retornam nulo.
3. Para coleção com apenas um elemento, ambas as funções retornam o mesmo valor.

#### sorted

sorted retorna uma lista de elementos da coleção classificados de acordo com sua ordem de classificação natural (crescente).

sortedBy classifica os elementos de acordo com a ordem de classificação natural dos valores retornados pela função seletora especificada.

```kotlin
val shuffled = listOf(5, 4, 2, 1, 3, -10)                   // 1
val natural = shuffled.sorted()                             // 2
val inverted = shuffled.sortedBy { -it }                    // 3
val descending = shuffled.sortedDescending()                // 4
val descendingBy = shuffled.sortedByDescending { abs(it)  } // 5
```

1. Define uma coleção de números embaralhados.
2. Classifica na ordem natural.
3. Classifica-o na ordem natural invertida usando -it como função seletora.
4. Classifica na ordem natural invertida usando sortedDescendente.
5. Classifica na ordem natural invertida dos valores absolutos dos itens usando abs(it) como função seletora.

#### Map Element Access

Quando aplicado a um mapa, o operador [] retorna o valor correspondente à chave fornecida, ou nulo se não existir tal chave no mapa.

A função getValue retorna um valor existente correspondente à chave fornecida ou lança uma exceção se a chave não for encontrada. Para mapas criados com withDefault, getValue retorna o valor padrão em vez de lançar uma exceção.

```kotlin
val map = mapOf("key" to 42)
​
val value1 = map["key"]                                     // 1
val value2 = map["key2"]                                    // 2
​
val value3: Int = map.getValue("key")                       // 1
​
val mapWithDefault = map.withDefault { k -> k.length }
val value4 = mapWithDefault.getValue("key2")                // 3
​
try {
    map.getValue("anotherKey")                              // 4
} catch (e: NoSuchElementException) {
    println("Message: $e")
}
​
```

1. Retorna 42 porque é o valor correspondente à chave “chave”.
2. Retorna nulo porque "key2" não está no mapa.
3. Retorna o valor padrão porque “key2” está ausente. Para esta chave é 4.
4. Lança NoSuchElementException porque "anotherKey" não está no mapa.

#### zip

A função zip mescla duas coleções fornecidas em uma nova coleção. Por padrão, a coleção de resultados contém pares de elementos da coleção de origem com o mesmo índice. No entanto, você pode definir sua própria estrutura do elemento da coleção de resultados.

O tamanho da coleção de resultados é igual ao tamanho mínimo de uma coleção de origem.

```kotlin
val A = listOf("a", "b", "c")                  // 1
val B = listOf(1, 2, 3, 4)                     // 1
​
val resultPairs = A zip B                      // 2
val resultReduce = A.zip(B) { a, b -> "$a$b" } // 3
```

1. Define duas coleções.
2. Mescla-os em uma lista de pares. A notação infixa é usada aqui.
3. Mescla-os em uma lista de valores String pela transformação fornecida.

#### getOrElse

getOrElse fornece acesso seguro aos elementos de uma coleção. É necessário um índice e uma função que fornece o valor padrão nos casos em que o índice está fora do limite.

```kotlin
val list = listOf(0, 10, 20)

println(list.getOrElse(1) { 42 })    // 1
println(list.getOrElse(10) { 42 })   // 2
```

1. Imprime o elemento no índice 1.
2. Imprime 42 porque o índice 10 está fora dos limites.

getOrElse também pode ser aplicado ao Map para obter o valor da chave fornecida.

```kotlin
val map = mutableMapOf<String, Int?>()
println(map.getOrElse("x") { 1 })       // 1
​
map["x"] = 3
println(map.getOrElse("x") { 1 })       // 2
​
map["x"] = null
println(map.getOrElse("x") { 1 })       // 3
```

1. Imprime o valor padrão porque a chave “x” não está no mapa.
2. Imprime 3, o valor da chave “x”.
3. Imprime o valor padrão porque o valor da chave “x” não está definido.

---

# Control Flow and Collections🇺🇸

## Control Flow

### Goal

Bring elements of repetition and conditional structures, in addition to getting to know some of Kotlin's main collections.

#### Control flow

- When
- Loops
- Ranges
- Equality check
- Conditional expressions

#### Collections

- List
- Set
- Map
- Extension Functions(filter, map, flatMap etc)

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

- next(): Animal
- hasNext(): Boolean

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

---

## Collections

### List

A list is an ordered collection of items. In Kotlin, lists can be either mutable (MutableList) or read-only (List). For list creation, use the standard library functions listOf() for read-only lists and mutableListOf() for mutable lists. To prevent unwanted modifications, obtain read-only views of mutable lists by casting them to List.

```kotlin
val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1
val sudoers: List<Int> = systemUsers                              // 2
​
fun addSystemUser(newUser: Int) {                                 // 3
    systemUsers.add(newUser)
}
​
fun getSysSudoers(): List<Int> {                                  // 4
    return sudoers
}
​
fun main() {
    addSystemUser(4)                                              // 5
    println("Tot sudoers: ${getSysSudoers().size}")               // 6
    getSysSudoers().forEach {                                     // 7
        i -> println("Some useful info on user $i")
    }
    // getSysSudoers().add(5) <- Error!                           // 8
}
```

1. Creates a MutableList.
2. Creates a read-only view of the list.
3. Adds a new item to the MutableList.
4. A function that returns an immutable List.
5. Updates the MutableList. All related read-only views are updated as well since they point to the same object.
6. Retrieves the size of the read-only list.
7. Iterates the list and prints its elements.
8. Attempting to write to the read-only view causes a compilation error.

---

### Set

A set is an unordered collection that does not support duplicates. For creating sets, there are functions setOf() and mutableSetOf(). A read-only view of a mutable set can be obtained by casting it to Set.

```kotlin
val openIssues: MutableSet<String> = mutableSetOf("uniqueDescr1", "uniqueDescr2", "uniqueDescr3") // 1
​
fun addIssue(uniqueDesc: String): Boolean {
    return openIssues.add(uniqueDesc)                                                             // 2
}
​
fun getStatusLog(isAdded: Boolean): String {
    return if (isAdded) "registered correctly." else "marked as duplicate and rejected."          // 3
}
​
fun main() {
    val aNewIssue: String = "uniqueDescr4"
    val anIssueAlreadyIn: String = "uniqueDescr2"
​
    println("Issue $aNewIssue ${getStatusLog(addIssue(aNewIssue))}")                              // 4
    println("Issue $anIssueAlreadyIn ${getStatusLog(addIssue(anIssueAlreadyIn))}")                // 5
}
```

1. Creates a Set with given elements.
2. Returns a boolean value showing if the element was actually added.
3. Returns a string, based on function input parameter.
4. Prints a success message: the new element is added to the Set.
5. Prints a failure message: the element can't be added because it duplicates an existing element.

---

### Map

A map is a collection of key/value pairs, where each key is unique and is used to retrieve the corresponding value. For creating maps, there are functions mapOf() and mutableMapOf(). Using the to infix function makes initialization less noisy. A read-only view of a mutable map can be obtained by casting it to Map.

```kotlin
const val POINTS_X_PASS: Int = 15

val EZPassAccounts: MutableMap<Int, Int> = mutableMapOf(1 to 100, 2 to 100, 3 to 100)   // 1
val EZPassReport: Map<Int, Int> = EZPassAccounts                                        // 2
​
fun updatePointsCredit(accountId: Int) {
    if (EZPassAccounts.containsKey(accountId)) {                                        // 3
        println("Updating $accountId...")

        EZPassAccounts[accountId] = EZPassAccounts.getValue(accountId) + POINTS_X_PASS  // 4
    } else {
        println("Error: Trying to update a non-existing account (id: $accountId)")
    }
}
​
fun accountsReport() {
    println("EZ-Pass report:")

    EZPassReport.forEach {                                                              // 5
        k, v -> println("ID $k: credit $v")
    }
}
​
fun main() {
    accountsReport()                                                                    // 6
    updatePointsCredit(1)                                                               // 7
    updatePointsCredit(1)
    updatePointsCredit(5)                                                               // 8
    accountsReport()                                                                    // 9
}
```

1. Creates a mutable Map.
2. Creates a read-only view of the Map.
3. Checks if the Map's key exists.
4. Reads the corresponding value and increments it with a constant value.
5. Iterates immutable Map and prints key/value pairs.
6. Reads the account points balance, before updates.
7. Updates an existing account two times.
8. Tries to update a non-existing account: prints an error message.
9. Reads the account points balance, after updates.

---

### Useful functions

#### filter

filter function enables you to filter collections. It takes a filter predicate as a lambda-parameter. The predicate is applied to each element. Elements that make the predicate true are returned in the result collection.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1
​
val positives = numbers.filter { x -> x > 0 }  // 2
​
val negatives = numbers.filter { it < 0 }      // 3
```

1. Defines collection of numbers.
2. Gets positive numbers.
3. Uses the shorter `it` notation to get negative numbers.

#### map

map extension function enables you to apply a transformation to all elements in a collection. It takes a transformer function as a lambda-parameter.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1
​
val doubled = numbers.map { x -> x * 2 }      // 2
​
val tripled = numbers.map { it * 3 }          // 3
```

1. Defines a collection of numbers.
2. Doubles numbers.
3. Uses the shorter it notation to triple the numbers.

#### any, all, none

These functions check the existence of collection elements that match a given predicate.

Function any

Function any returns true if the collection contains at least one element that matches the given predicate.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val anyNegative = numbers.any { it < 0 }             // 2
​
val anyGT6 = numbers.any { it > 6 }                  // 3
```

1. Defines a collection of numbers.
2. Checks if there are negative elements.
3. Checks if there are elements greater than 6.

Function all

Function all returns true if all elements in collection match the given predicate.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val allEven = numbers.all { it % 2 == 0 }            // 2
​
val allLess6 = numbers.all { it < 6 }                // 3
```

1. Defines a collection of numbers.
2. Checks whether all elements are even.
3. Checks whether all elements are less than 6.

Function none

Function none returns true if there are no elements that match the given predicate in the collection.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val allEven = numbers.none { it % 2 == 1 }           // 2
​
val allLess6 = numbers.none { it > 6 }               // 3
```

1. Defines a collection of numbers.
2. Checks if there are no odd elements (all elements are even).
3. Checks if there are no elements greater than 6.

#### find, findLast

The find and findLast functions return the first or the last collection element that matches the given predicate. If there are no such elements, these functions return null.

```kotlin
val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1
​
val first = words.find { it.startsWith("some") }                                // 2
val last = words.findLast { it.startsWith("some") }                             // 3
​
val nothing = words.find { it.contains("nothing") }                             // 4
```

1. Defines a collection of words.
2. Looks for the first word starting with "some".
3. Looks for the last word starting with "some".
4. Looks for the first word containing "nothing".

#### first, last

first, last

These functions return the first and the last element of the collection correspondingly. You can also use them with a predicate; in this case, they return the first or the last element that matches the given predicate.

If a collection is empty or doesn't contain elements matching the predicate, the functions throw NoSuchElementException.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val first = numbers.first()                          // 2
val last = numbers.last()                            // 3
​
val firstEven = numbers.first { it % 2 == 0 }        // 4
val lastOdd = numbers.last { it % 2 != 0 }           // 5
```

1. Defines a collection of numbers.
2. Picks the first element.
3. Picks the last element.
4. Picks the first even element.
5. Picks the last odd element.

firstOrNull, lastOrNull

These functions work almost the same way with one difference: they return null if there are no matching elements.

```kotlin
val words = listOf("foo", "bar", "baz", "faz")         // 1
val empty = emptyList<String>()                        // 2
​
val first = empty.firstOrNull()                        // 3
val last = empty.lastOrNull()                          // 4
​
val firstF = words.firstOrNull { it.startsWith('f') }  // 5
val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
val lastF = words.lastOrNull { it.endsWith('f') }      // 7
val lastZ = words.lastOrNull { it.endsWith('z') }      // 8
```

1. Defines a collection of words.
2. Defines an empty collection.
3. Picks the first element from empty collection. It supposed to be null.
4. Picks the last element from empty collection. It supposed to be null as well.
5. Picks the first word starting with 'f'.
6. Picks the first word starting with 'z'.
7. Picks the last word ending with 'f'.
8. Picks the last word ending with 'z'.

#### count

count functions returns either the total number of elements in a collection or the number of elements matching the given predicate.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
​
val totalCount = numbers.count()                     // 2
val evenCount = numbers.count { it % 2 == 0 }        // 3
```

1. Defines a collection of numbers.
2. Counts the total number of elements.
3. Counts the number of even elements.

#### associateBy, groupBy

Functions associateBy and groupBy build maps from the elements of a collection indexed by the specified key. The key is defined in the keySelector parameter. You can also specify an optional valueSelector to define what will be stored in the value of the map element.

The difference between associateBy and groupBy is how they process objects with the same key:
associateBy uses the last suitable element as the value.

- groupBy builds a list of all suitable elements and puts it in the value.
- The returned map preserves the entry iteration order of the original collection.
  ​

```kotlin
data class Person(val name: String, val city: String, val phone: String) // 1
​
val people = listOf(                                                     // 2
    Person("John", "Boston", "+1-888-123456"),
    Person("Sarah", "Munich", "+49-777-789123"),
    Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
    Person("Vasilisa", "Saint-Petersburg", "+7-999-123456"))
​
val phoneBook = people.associateBy { it.phone }                          // 3
val cityBook = people.associateBy(Person::phone, Person::city)           // 4
val peopleCities = people.groupBy(Person::city, Person::name)            // 5
val lastPersonCity = people.associateBy(Person::city, Person::name)      // 6
```

1. Defines a data class that describes a person.
2. Defines a collection of people.
3. Builds a map from phone numbers to their owners' information. it.phone is the keySelector here. The valueSelector is not provided, so the values of the map are Person objects themselves.
4. Builds a map from phone numbers to the cities where owners live. Person::city is the valueSelector here, so the values of the map contain only cities.
5. Builds a map that contains cities and people living there. The values of the map are lists of person names.
6. Builds a map that contains cities and the last person living there. The values of the map are names of the last person living in each city.

#### partition

The partition function splits the original collection into a pair of lists using a given predicate:

1. Elements for which the predicate is true.
2. Elements for which the predicate is false.

```kotlin
val numbers = listOf(1, -2, 3, -4, 5, -6)                // 1
​
val evenOdd = numbers.partition { it % 2 == 0 }           // 2

val (positives, negatives) = numbers.partition { it > 0 } // 3
```

1. Defines a collection of numbers.
2. Splits numbers into a Pair of lists with even and odd numbers.
3. Splits numbers into two lists with positive and negative numbers. Pair destructuring is applied here to get the Pair members.

#### flatMap

flatMap transforms each element of a collection into an iterable object and builds a single list of the transformation results. The transformation is user-defined.

```kotlin
val fruitsBag = listOf("apple","orange","banana","grapes")  // 1
val clothesBag = listOf("shirts","pants","jeans")           // 2
val cart = listOf(fruitsBag, clothesBag)                    // 3
val mapBag = cart.map { it }                                // 4
val flatMapBag = cart.flatMap { it }                        // 5
```

1. Defines a collection of Strings with fruit names.
2. Defines a collection of Strings with clothes names.
3. Adds fruitsBag and clothesBag to the cart list.
4. Builds a map of cart elements, which is a list of two lists.
5. Builds a flatMap of cart elements, which is a single list consisting of elements from both lists.

#### minOrNull, maxOrNull

minOrNull and maxOrNull functions return the smallest and the largest element of a collection. If the collection is empty, they return null.

```kotlin
val numbers = listOf(1, 2, 3)
val empty = emptyList<Int>()
val only = listOf(3)
​
println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}") // 1
println("Empty: $empty, min = ${empty.minOrNull()}, max = ${empty.maxOrNull()}")        // 2
println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")            // 3
```

1. For non-empty collection, functions return the smallest and the largest element.
2. For empty collections, both functions return null.
3. For collection with only one element, both functions return the same value.

#### sorted

sorted returns a list of collection elements sorted according to their natural sort order (ascending).

sortedBy sorts elements according to natural sort order of the values returned by specified selector function.

```kotlin
val shuffled = listOf(5, 4, 2, 1, 3, -10)                   // 1
val natural = shuffled.sorted()                             // 2
val inverted = shuffled.sortedBy { -it }                    // 3
val descending = shuffled.sortedDescending()                // 4
val descendingBy = shuffled.sortedByDescending { abs(it)  } // 5
```

1. Defines a collection of shuffled numbers.
2. Sorts it in the natural order.
3. Sorts it in the inverted natural order by using -it as a selector function.
4. Sorts it in the inverted natural order by using sortedDescending.
5. Sorts it in the inverted natural order of items' absolute values by using abs(it) as a selector function.

#### Map Element Access

When applied to a map, [] operator returns the value corresponding to the given key, or null if there is no such key in the map.

getValue function returns an existing value corresponding to the given key or throws an exception if the key wasn't found. For maps created with withDefault, getValue returns the default value instead of throwing an exception.

```kotlin
val map = mapOf("key" to 42)
​
val value1 = map["key"]                                     // 1
val value2 = map["key2"]                                    // 2
​
val value3: Int = map.getValue("key")                       // 1
​
val mapWithDefault = map.withDefault { k -> k.length }
val value4 = mapWithDefault.getValue("key2")                // 3
​
try {
    map.getValue("anotherKey")                              // 4
} catch (e: NoSuchElementException) {
    println("Message: $e")
}
​
```

1. Returns 42 because it's the value corresponding to the key "key".
2. Returns null because "key2" is not in the map.
3. Returns the default value because "key2" is absent. For this key it's 4.
4. Throws NoSuchElementException because "anotherKey" is not in the map.

#### zip

zip function merges two given collections into a new collection. By default, the result collection contains Pairs of source collection elements with the same index. However, you can define your own structure of the result collection element.

The size of the result collection equals to the minimum size of a source collection.

```kotlin
val A = listOf("a", "b", "c")                  // 1
val B = listOf(1, 2, 3, 4)                     // 1
​
val resultPairs = A zip B                      // 2
val resultReduce = A.zip(B) { a, b -> "$a$b" } // 3
```

1. Defines two collections.
2. Merges them into a list of pairs. The infix notation is used here.
3. Merges them into a list of String values by the given transformation.

#### getOrElse

getOrElse provides safe access to elements of a collection. It takes an index and a function that provides the default value in cases when the index is out of bound.

```kotlin
val list = listOf(0, 10, 20)

println(list.getOrElse(1) { 42 })    // 1
println(list.getOrElse(10) { 42 })   // 2
```

1. Prints the element at the index 1.
2. Prints 42 because the index 10 is out of bounds.

getOrElse can also be applied to Map to get the value for the given key.

```kotlin
val map = mutableMapOf<String, Int?>()
println(map.getOrElse("x") { 1 })       // 1
​
map["x"] = 3
println(map.getOrElse("x") { 1 })       // 2
​
map["x"] = null
println(map.getOrElse("x") { 1 })       // 3
```

1. Prints the default value because the key "x" is not in the map.
2. Prints 3, the value for the key "x".
3. Prints the default value because the value for the key "x" is not defined.