# Programação Orientada a Objetos🇧🇷

### Objetivo

Conhecer os principais tipo de classes do Kotlin, as quais nos fornecem possibiilidades poderosas para utilizar POO.

---

### Abstração

Habilidade de concentrar-se nos aspectos essenciais de um domínio, ignorando características menos importantes ou acidentais.

Nesse contexto, classes e objetos são abstrações de entidades existentes no domínio/problema em questão.

---

### Encapsulamento

Se trata do conceito de esconder a implementação dos objetos, criando assim interfaces de uso mais concisas e fáceis de utilzar/entender.

> O encapsulamento favorece principalmente dois aspectos de um sistema: a manutenção e a evolução.

---

### Herança

É a possíbilidade de herdar características(propriedades) e comportamentos(funções) entre classes, ela é utilizada para promover o reuso de código através de estruturas mais genéricas e flexíveis.

Kotlin oferece suporte total ao mecanismo tradicional de herança orientada a objetos.

```kotlin
open class Dog {                // 1
    open fun sayHello() {       // 2
        println("wow wow!")
    }
}
​
class Yorkshire : Dog() {       // 3
    override fun sayHello() {   // 4
        println("wif wif!")
    }
}
​
fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}
```

1. As classes Kotlin são finais por padrão. Se você deseja permitir a herança de classe, marque a classe com o modificador open.
2. Os métodos Kotlin também são finais por padrão. Tal como acontece com as classes, o modificador open permite extensão de outras classes.
3. Uma classe herda uma superclasse quando você especifica: SuperclassName() após seu nome. Os parênteses vazios () indicam uma invocação do construtor padrão da superclasse.
4. A substituição de métodos ou atributos requer o modificador de substituição.

#### Herança com Construtor Parametrizado

```kotlin
open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}
​
class SiberianTiger : Tiger("Siberia")                  // 1
​
fun main() {
    val tiger: Tiger = SiberianTiger()

    tiger.sayHello()
}
```

1. Se você quiser usar um construtor parametrizado da superclasse ao criar uma subclasse, forneça os argumentos do construtor na declaração da subclasse.

#### Passando argumentos do construtor para a superclasse

```kotlin
open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}
​
class Asiatic(name: String) : Lion(name = name, origin = "India") // 1
​
fun main() {
    val lion: Lion = Asiatic("Rufo")                              // 2

    lion.sayHello()
}
```

1. `name` na declaração `Asiatic` não é nem `var` nem `val`: é um argumento construtor, cujo valor é passado para a propriedade `name` da superclasse `Lion`.
2. Cria uma instância `Asiatic` com o nome `Rufo`. A chamada invoca o construtor `Lion` com os argumentos `Rufo` e `India`.

---

### Polimorfismo

Capacidade de um objeto poder ser referenciado de formas diferentes, ou seja, é a capacidade de tratar objetos criados a partir das classes específicas como objetos de uma classe genérica.

Esse conceito nos oferece possibilidades incríveis para criação de soluções mais genéricas.

---

### Pilares da POO no Kotlin

Abstração:

- Herança
- Data Classes(Encapsulamento)
- Enum Classes
- Sealed Classes(Polimorfismo)
- Object Keyword

---

### Data Classes

As classes de dados facilitam a criação de classes usadas para armazenar valores. Essas classes recebem automaticamente métodos para copiar, obter uma representação de string e usar instâncias em coleções. Você pode substituir esses métodos por suas próprias implementações na declaração da classe.

```kotlin
data class User(val name: String, val id: Int) {           // 1
    override fun equals(other: Any?) =
        other is User && other.id == this.id               // 2
}

fun main() {
    val user = User("Alex", 1)
    println(user)                                          // 3
​
    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)
​
    println("user == secondUser: ${user == secondUser}")   // 4
    println("user == thirdUser: ${user == thirdUser}")
​
    // hashCode() function
    println(user.hashCode())                               // 5
    println(secondUser.hashCode())
    println(thirdUser.hashCode())
​
    // copy() function
    println(user.copy())                                   // 6
    println(user === user.copy())                          // 7
    println(user.copy("Max"))                              // 8
    println(user.copy(id = 3))                             // 9
​
    println("name = ${user.component1()}")                 // 10
    println("id = ${user.component2()}")
}
```

1. Define uma classe de dados com o modificador de dados.
2. Substitua o método equals padrão declarando os usuários iguais se eles tiverem o mesmo ID.
3. O método toString é gerado automaticamente, o que torna a saída println bonita.
4. Nosso equals personalizado considera duas instâncias iguais se seus IDs forem iguais.
5. Instâncias de classe de dados com atributos exatamente correspondentes têm o mesmo hashCode.
6. A função de cópia gerada automaticamente facilita a criação de uma nova instância.
7. copy cria uma nova instância, de forma que o objeto e sua cópia tenham referências distintas.
8. Ao copiar, você pode alterar os valores de determinadas propriedades. copy aceita argumentos na mesma ordem que o construtor da classe.
9. Use copy com argumentos nomeados para alterar o valor independentemente da ordem das propriedades.
10. As funções componenteN geradas automaticamente permitem obter os valores das propriedades na ordem de declaração.

---

### Enum Classes

As classes Enum são usadas para modelar tipos que representam um conjunto finito de valores distintos, como direções, estados, modos e assim por diante.

```kotlin
enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}
​
fun main() {
    val state = State.RUNNING                         // 2
    val message = when (state) {                      // 3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }

    println(message)
}
```

1. Define uma classe enum simples com três constantes enum. O número de constantes é sempre finito e todas são distintas.
2. Acessa uma constante enum por meio do nome da classe.
3. Com enums, o compilador pode inferir se uma expressão, de modo que você não precise do caso else.

Enums podem conter propriedades e métodos como outras classes, separados da lista de constantes enum por ponto e vírgula.

```kotlin
enum class Color(val rgb: Int) {                      // 1
    RED(0xFF0000),                                    // 2
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);
​
    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
}
​
fun main() {
    val red = Color.RED
    
    println(red)                                      // 4
    
    println(red.containsRed())                        // 5
    println(Color.BLUE.containsRed())                 // 6
    println(Color.YELLOW.containsRed())               // 7
}
```

1. Define uma classe enum com uma propriedade e um método.
2. Cada constante enum deve passar um argumento para o parâmetro do construtor.
3. Os membros da classe Enum são separados das definições constantes por ponto e vírgula.
4. O toString padrão retorna o nome da constante, aqui "RED".
5. Chama um método em uma constante enum.
6. Chama um método por meio do nome da classe enum.
7. Os valores RGB de VERMELHO e AMARELO compartilham os primeiros bits (FF), então isso imprime 'verdadeiro'.

---

### Sealed Classes

Classes seladas permitem restringir o uso de herança. Depois de declarar uma classe selada, ela só poderá ser subclassificada dentro do mesmo pacote onde a classe selada foi declarada. Ela não pode ser subclassificada fora do pacote onde a classe selada é declarada.

```kotlin
sealed class Mammal(val name: String)                                                   // 1
​
class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)
​
fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5     
    }                                                                                   // 6
}
​
fun main() {
    println(greetMammal(Cat("Snowy")))
}
```

1. Define uma classe selada.
2. Define subclasses. Observe que todas as subclasses devem estar no mesmo pacote.
3. Usa uma instância da classe selada como argumento em uma expressão when.
4. Um smartcast é executado, convertendo Mamífero em Humano.
5. Um smartcast é executado, lançando Mammal para Cat.
6. O caso else não é necessário aqui, uma vez que todas as subclasses possíveis da classe selada são cobertas. Com uma superclasse não selada, seria necessário algo mais.

---

### Palavra reservada Object

Classes e objetos em Kotlin funcionam da mesma maneira que na maioria das linguagens orientadas a objetos: uma classe é um projeto e um objeto é uma instância de uma classe. Normalmente, você define uma classe e depois cria múltiplas instâncias dessa classe:

```kotlin
import java.util.Random
​
class LuckDispatcher {                    //1 
    fun getNumber() {                     //2 
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}
​
fun main() {
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()
    
    d1.getNumber()                        //4 
    d2.getNumber()
}
```

1. Define um projeto.
2. Define um método.
3. Cria instâncias.
4. Chama o método nas instâncias.

No Kotlin você também tem a palavra-chave object. É usado para obter um tipo de dados com uma única implementação.

Se você é um usuário Java e deseja entender o que significa “único”, você pode pensar no padrão Singleton: ele garante que apenas uma instância dessa classe seja criada, mesmo que 2 threads tentem criá-la.

Para conseguir isso em Kotlin, você só precisa declarar um objeto: nenhuma classe, nenhum construtor, apenas uma instância lenta. Por que preguiçoso? Porque será criado uma vez quando o objeto for acessado. Caso contrário, nem será criado.

#### Expressão `object`

Aqui está um uso típico básico de uma expressão de objeto: uma estrutura simples de objeto/propriedades. Não há necessidade de fazer isso na declaração de classe: você cria um único objeto, declara seus membros e acessa-o dentro de uma função. Objetos como esse são frequentemente criados em Java como instâncias de classe anônimas.

```kotlin
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1
​
    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }
​
    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3
​
    print("Total price: $$total")                                               //4
​
}
​
fun main() {
    rentPrice(10, 2, 1)                                                         //5
}
```

1. Cria uma função com parâmetros.
2. Cria um objeto para usar no cálculo do valor do resultado.
3. Acessa as propriedades do objeto.
4. Imprime o resultado.
5. Chama a função. É quando o objeto é realmente criado.

#### Declaração `object`

Você também pode usar a declaração do objeto. Não é uma expressão e não pode ser usada em uma atribuição de variável. Você deve usá-lo para acessar diretamente seus membros:

```kotlin
object DoAuth {                                                 //1 
    fun takeParams(username: String, password: String) {        //2 
        println("input Auth parameters = $username:$password")
    }
}
​
fun main(){
    DoAuth.takeParams("foo", "qwerty")                          //3
}
```

1. Cria uma declaração de objeto.
2. Define o método do objeto.
3. Chama o método. É quando o objeto é realmente criado.

### Companion Objects(Métodos estáticos*)

Uma declaração de objeto dentro de uma classe define outro caso útil: o objeto complementar. Sintaticamente é semelhante aos métodos estáticos em Java: você chama os membros do objeto usando seu nome de classe como qualificador. Se você planeja usar um objeto complementar em Kotlin, considere usar uma função em nível de pacote.

```kotlin
class BigBen {                                  //1 
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}
​
fun main() {
    BigBen.getBongs(12)                         //4
}
```

1. Define uma classe.
2. Define um companheiro. Seu nome pode ser omitido.
3. Define um método de objeto complementar.
4. Chama o método do objeto complementar por meio do nome da classe.

---

# Object Oriented Programming🇺🇸

### Goal

Know the main types of Kotlin classes, which provide us with powerful possibilities for using OOP.

---

### Abstraction

Ability to concentrate on the essential aspects of a domain, ignoring less important or incidental features.

In this context, classes and objects are abstractions of existing entities in the domain/problem in question.

---

### Encapsulation

This is the concept of hiding the implementation of objects, thus creating more concise and simpler to use/understand user interfaces.

> Encapsulation mainly favors two aspects of a system: maintenance and evolution.

---

### Heritage

It is a possibility of inheriting characteristics (properties) and behaviors (functions) between classes, it is used to promote the use of code through more generic and flexible structures.

Kotlin fully supports the traditional object-oriented guidance mechanism.

```kotlin
open class Dog {                // 1
    open fun sayHello() {       // 2
        println("wow wow!")
    }
}
​
class Yorkshire : Dog() {       // 3
    override fun sayHello() {   // 4
        println("wif wif!")
    }
}
​
fun main() {
    val dog: Dog = Yorkshire()
    dog.sayHello()
}
```

1. Kotlin classes are final by default. If you want to allow the class inheritance, mark the class with the open modifier.
2. Kotlin methods are also final by default. As with the classes, the open modifier allows overriding them.
3. A class inherits a superclass when you specify the : SuperclassName() after its name. The empty parentheses () indicate an invocation of the superclass default constructor.
4. Overriding methods or attributes requires the override modifier.

#### Inheritance with Parameterized Constructor

```kotlin
open class Tiger(val origin: String) {
    fun sayHello() {
        println("A tiger from $origin says: grrhhh!")
    }
}
​
class SiberianTiger : Tiger("Siberia")                  // 1
​
fun main() {
    val tiger: Tiger = SiberianTiger()
    tiger.sayHello()
}
```

1. If you want to use a parameterized constructor of the superclass when creating a subclass, provide the constructor arguments in the subclass declaration.

#### Passing Constructor Arguments to Superclass

```kotlin
open class Lion(val name: String, val origin: String) {
    fun sayHello() {
        println("$name, the lion from $origin says: graoh!")
    }
}
​
class Asiatic(name: String) : Lion(name = name, origin = "India") // 1
​
fun main() {
    val lion: Lion = Asiatic("Rufo")                              // 2
    lion.sayHello()
}
```

1. `name` in the `Asiatic` declaration is neither a `var` nor `val`: it's a constructor argument, whose value is passed to the `name` property of the superclass `Lion`.
2. Creates an `Asiatic` instance with the name `Rufo`. The call invokes the `Lion` constructor with arguments `Rufo` and `India`.

---

### Polymorphism

The ability of an object to be referenced in different ways, that is, the ability to treat objects created from specific classes as objects of a generic class.

This concept offers us incredible possibilities for creating more generic solutions.

---

### Pillars of OOP in Kotlin

Abstraction:

- Heritage
- Data Classes (Encapsulation)
- Enum Classes
- Sealed Classes (Polymorphism)
- Object Keyword

---

### Data Classes

Data classes make it easy to create classes that are used to store values. Such classes are automatically provided with methods for copying, getting a string representation, and using instances in collections. You can override these methods with your own implementations in the class declaration.

```kotlin
data class User(val name: String, val id: Int) {           // 1
    override fun equals(other: Any?) =
        other is User && other.id == this.id               // 2
}

fun main() {
    val user = User("Alex", 1)
    println(user)                                          // 3
​
    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)
​
    println("user == secondUser: ${user == secondUser}")   // 4
    println("user == thirdUser: ${user == thirdUser}")
​
    // hashCode() function
    println(user.hashCode())                               // 5
    println(secondUser.hashCode())
    println(thirdUser.hashCode())
​
    // copy() function
    println(user.copy())                                   // 6
    println(user === user.copy())                          // 7
    println(user.copy("Max"))                              // 8
    println(user.copy(id = 3))                             // 9
​
    println("name = ${user.component1()}")                 // 10
    println("id = ${user.component2()}")
}
```

1. Defines a data class with the data modifier.
2. Override the default equals method by declaring users equal if they have the same id.
3. Method toString is auto-generated, which makes println output look nice.
4. Our custom equals considers two instances equal if their ids are equal.
5. Data class instances with exactly matching attributes have the same hashCode.
6. Auto-generated copy function makes it easy to create a new instance.
7. copy creates a new instance, so the object and its copy have distinct references.
8. When copying, you can change values of certain properties. copy accepts arguments in the same order as the class constructor.
9. Use copy with named arguments to change the value despite of the properties order.
10. Auto-generated componentN functions let you get the values of properties in the order of declaration.

---

### Enum Classes

Enum classes are used to model types that represent a finite set of distinct values, such as directions, states, modes, and so forth.

```kotlin
enum class State {
    IDLE, RUNNING, FINISHED                           // 1
}
​
fun main() {
    val state = State.RUNNING                         // 2
    val message = when (state) {                      // 3
        State.IDLE -> "It's idle"
        State.RUNNING -> "It's running"
        State.FINISHED -> "It's finished"
    }

    println(message)
}
```

1. Defines a simple enum class with three enum constants. The number of constants is always finite and they are all distinct.
2. Accesses an enum constant via the class name.
3. With enums, the compiler can infer if a when-expression is exhaustive so that you don't need the else-case.

Enums may contain properties and methods like other classes, separated from the list of enum constants by a semicolon.

```kotlin
enum class Color(val rgb: Int) {                      // 1
    RED(0xFF0000),                                    // 2
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);
​
    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
}
​
fun main() {
    val red = Color.RED
    println(red)                                      // 4
    println(red.containsRed())                        // 5
    println(Color.BLUE.containsRed())                 // 6
    println(Color.YELLOW.containsRed())               // 7
}
```

1. Defines an enum class with a property and a method.
2. Each enum constant must pass an argument for the constructor parameter.
3. Enum class members are separated from the constant definitions by a semicolon.
4. The default toString returns the name of the constant, here "RED".
5. Calls a method on an enum constant.
6. Calls a method via enum class name.
7. The RGB values of RED and YELLOW share first bits (FF) so this prints 'true'.

---

### Sealed Classes

Sealed classes let you restrict the use of inheritance. Once you declare a class sealed, it can only be subclassed from inside the same package where the sealed class is declared. It cannot be subclassed outside of the package where the sealed class is declared.

```kotlin
sealed class Mammal(val name: String)                                                   // 1
​
class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)
​
fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5     
    }                                                                                   // 6
}
​
fun main() {
    println(greetMammal(Cat("Snowy")))
}
```

1. Defines a sealed class.
2. Defines subclasses. Note that all subclasses must be in the same package.
3. Uses an instance of the sealed class as an argument in a when expression.
4. A smartcast is performed, casting Mammal to Human.
5. A smartcast is performed, casting Mammal to Cat.
6. The else-case is not necessary here since all possible subclasses of the sealed class are covered. With a non-sealed superclass else would be required.

---

### Object Keyword

Classes and objects in Kotlin work the same way as in most object-oriented languages: a class is a blueprint, and an object is an instance of a class. Usually, you define a class and then create multiple instances of that class:

```kotlin
import java.util.Random
​
class LuckDispatcher {                    //1 
    fun getNumber() {                     //2 
        var objRandom = Random()
        println(objRandom.nextInt(90))
    }
}
​
fun main() {
    val d1 = LuckDispatcher()             //3
    val d2 = LuckDispatcher()
    
    d1.getNumber()                        //4 
    d2.getNumber()
}
```

1. Defines a blueprint.
2. Defines a method.
3. Creates instances.
4. Calls the method on instances.

In Kotlin you also have the object keyword. It is used to obtain a data type with a single implementation.

If you are a Java user and want to understand what "single" means, you can think of the Singleton pattern: it ensures you that only one instance of that class is created even if 2 threads try to create it.

To achieve this in Kotlin, you only need to declare an object: no class, no constructor, only a lazy instance. Why lazy? Because it will be created once when the object is accessed. Otherwise, it won't even be created.

#### `object` Expression

Here is a basic typical usage of an object expression: a simple object/properties structure. There is no need to do so in class declaration: you create a single object, declare its members and access it within one function. Objects like this are often created in Java as anonymous class instances.

```kotlin
fun rentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit {  //1
​
    val dayRates = object {                                                     //2
        var standard: Int = 30 * standardDays
        var festivity: Int = 50 * festivityDays
        var special: Int = 100 * specialDays
    }
​
    val total = dayRates.standard + dayRates.festivity + dayRates.special       //3
​
    print("Total price: $$total")                                               //4
​
}
​
fun main() {
    rentPrice(10, 2, 1)                                                         //5
}
```

1. Creates a function with parameters.
2. Creates an object to use when calculating the result value.
3. Accesses the object's properties.
4. Prints the result.
5. Calls the function. This is when the object is actually created.

#### `object` Declaration

You can also use the object declaration. It isn't an expression, and can't be used in a variable assignment. You should use it to directly access its members:

```kotlin
object DoAuth {                                                 //1 
    fun takeParams(username: String, password: String) {        //2 
        println("input Auth parameters = $username:$password")
    }
}
​
fun main(){
    DoAuth.takeParams("foo", "qwerty")                          //3
}
```

1. Creates an object declaration.
2. Defines the object method.
3. Calls the method. This is when the object is actually created.

Companion Objects

An object declaration inside a class defines another useful case: the companion object. Syntactically it's similar to the static methods in Java: you call object members using its class name as a qualifier. If you plan to use a companion object in Kotlin, consider using a package-level function instead.

```kotlin
class BigBen {                                  //1 
    companion object Bonger {                   //2
        fun getBongs(nTimes: Int) {             //3
            for (i in 1 .. nTimes) {
                print("BONG ")
            }
        }
    }
}
​
fun main() {
    BigBen.getBongs(12)                         //4
}
```

1. Defines a class.
2. Defines a companion. Its name can be omitted.
3. Defines a companion object method.
4. Calls the companion object method via the class name.