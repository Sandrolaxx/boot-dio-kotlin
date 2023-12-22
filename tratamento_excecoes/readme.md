# Tratamento de Exceções em Kotlin🇧🇷

### Objetivos

Compreensão do funcionamento das exceções e como realizar o tratamento dos erros.

---

### Exception classes

Todas as exceções em Kotlin herdam da classe `Throwable`. Cada exceção tem uma mensagem, stack trace e uma causa opcional.

```kotlin
throw Exception("Olá! Simulando uma exceção!");
```

Para capturar uma exceção, use o bloco `try catch`:

```kotlin
try {
    // some code
} catch (e: SomeException) {
    // handler
} finally {
    // optional finally block
}
```

Pode haver zero ou mais blocos catch e o bloco final pode ser omitido. No entanto, pelo menos um bloco catch ou finalmente é necessário.

---

### Try é uma expressão

try é uma expressão, o que significa que pode ter um valor de retorno:

```kotlin
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
```

O valor retornado de uma expressão try é a última expressão no bloco try ou a última expressão no bloco catch (ou blocos). O conteúdo do bloco final não afeta o resultado da expressão.

---

### Checked exceptions

Kotlin não verificou exceções. Há muitas razões para isso, mas forneceremos um exemplo simples que ilustra por que isso acontece.

A seguir está um exemplo de interface do JDK implementado pela classe StringBuilder:

```kotlin
Appendable append(CharSequence csq) throws IOException;
```

Esta assinatura diz que toda vez que eu adiciono uma string a algo (um StringBuilder, algum tipo de log, um console, etc.), preciso capturar as IOExceptions. Por quê? Porque a implementação pode estar executando operações IO (o Writer também implementa Appendable). O resultado é um código como este em todos os lugares:

```kotlin
try {
    log.append(message)
} catch (IOException e) {
    // Must be safe
}
```

E isso não é bom. Basta dar uma olhada em Effective Java, 3ª Edição, Item 77: Não ignore exceções.

Bruce Eckel diz o seguinte sobre exceções verificadas:

O exame de pequenos programas leva à conclusão de que exigir especificações de exceção poderia aumentar a produtividade do desenvolvedor e melhorar a qualidade do código, mas a experiência com grandes projetos de software sugere um resultado diferente – diminuição da produtividade e pouco ou nenhum aumento na qualidade do código.

E aqui estão algumas reflexões adicionais sobre o assunto:

[Java's checked exceptions were a mistake](https://radio-weblogs.com/0122027/stories/2003/04/01/JavasCheckedExceptionsWereAMistake.html) (Rod Waldhoff)

[The Trouble with Checked Exceptions](https://www.artima.com/intv/handcuffs.html) (Anders Hejlsberg)

Se quiser alertar os chamadores sobre possíveis exceções ao chamar código Kotlin de Java, Swift ou Objective-C, você pode usar a anotação @Throws. Leia mais sobre como usar esta anotação para [Java](https://kotlinlang.org/docs/java-to-kotlin-interop.html#checked-exceptions) e para [Swift e Objective-C](https://kotlinlang.org/docs/native-objc-interop.html#errors-and-exceptions).

---

### O tipo Nothing

throw é uma expressão em Kotlin, então você pode usá-la, por exemplo, como parte de uma expressão de Elvis:

```kotlin
val s = person.name ?: throw IllegalArgumentException("Name required")
```

A expressão throw tem o tipo Nothing. Este tipo não possui valores e é usado para marcar locais de código que nunca podem ser alcançados. No seu próprio código, você pode usar Nothing para marcar uma função que nunca retorna:

```kotlin
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```

Ao chamar esta função, o compilador saberá que a execução não continua além da chamada:

```kotlin
val s = person.name ?: fail("Name required")

println(s) // 's' é conhecido por ser inicializado neste ponto
```

Você também pode encontrar esse tipo ao lidar com inferência de tipos. A variante anulável deste tipo, Nothing?, tem exatamente um valor possível, que é nulo. 

Se você usar null para inicializar um valor de um tipo inferido e não houver outras informações que possam ser usadas para determinar um tipo mais específico, o compilador inferirá a propriedade Nothing?:

```kotlin
val x = null           // 'x' tem tipo `Nothing?`
val l = listOf(null)   // 'l' tem tipo `List<Nothing?>
```

---

### Exceções customizadas

É possível assim como no Java criarmos nossas exceções personalizadas, dando um direcionamento mais assertivo quanto a origem do erro ocorrido.

Basta nomearmos nossa exceção e estendermos Throwable:

```kotlin
class IllegalVoterException(message: String) : Throwable(message)

@Throws(IllegalVoterException::class)
fun vote(name: String, age: Int) {
    if (age < 16) {
        throw IllegalVoterException("Apenas pessoas com 16 anos ou mais podem votar.")
    }

    println("Voto realizado por $name com idade: $age")
}

fun main() {
    vote("Sandrolax", 25)
    vote("Amabilly", 11)
}
```

---

# Exception Handling in Kotlin🇺🇸

### Goals

Understanding how exceptions work and how to handle errors.

---

### Exception classes

All exception classes in Kotlin inherit the Throwable class. Every exception has a message, a stack trace, and an optional cause.

To throw an exception object, use the throw expression:

```kotlin
throw Exception("Hi There!")
```

To catch an exception, use the `try...catch` expression:

```kotlin
try {
    // some code
} catch (e: SomeException) {
    // handler
} finally {
    // optional finally block
}
```

There may be zero or more catch blocks, and the finally block may be omitted. However, at least one catch or finally block is required.

---

### Try is an expression

try is an expression, which means it can have a return value:

```kotlin
val a: Int? = try { input.toInt() } catch (e: NumberFormatException) { null }
```

The returned value of a try expression is either the last expression in the try block or the last expression in the catch block (or blocks). The contents of the finally block don't affect the result of the expression.

---

### Checked exceptions

Kotlin does not have checked exceptions. There are many reasons for this, but we will provide a simple example that illustrates why it is the case.

The following is an example interface from the JDK implemented by the StringBuilder class:

```kotlin
Appendable append(CharSequence csq) throws IOException;
```

This signature says that every time I append a string to something (a StringBuilder, some kind of a log, a console, etc.), I have to catch the IOExceptions. Why? Because the implementation might be performing IO operations (Writer also implements Appendable). The result is code like this all over the place:

```kotlin
try {
    log.append(message)
} catch (IOException e) {
    // Must be safe
}
```

And that's not good. Just take a look at Effective Java, 3rd Edition, Item 77: Don't ignore exceptions.

Bruce Eckel says this about checked exceptions:

Examination of small programs leads to the conclusion that requiring exception specifications could both enhance developer productivity and enhance code quality, but experience with large software projects suggests a different result – decreased productivity and little or no increase in code quality.

And here are some additional thoughts on the matter:

[Java's checked exceptions were a mistake](https://radio-weblogs.com/0122027/stories/2003/04/01/JavasCheckedExceptionsWereAMistake.html) (Rod Waldhoff)

[The Trouble with Checked Exceptions](https://www.artima.com/intv/handcuffs.html) (Anders Hejlsberg)

If you want to alert callers about possible exceptions when calling Kotlin code from Java, Swift, or Objective-C, you can use the @Throws annotation. Read more about using this annotation for [Java](https://kotlinlang.org/docs/java-to-kotlin-interop.html#checked-exceptions) and [Swift and Objective-C](https://kotlinlang.org/docs/native-objc-interop.html#errors-and-exceptions).


---

### The Nothing type

throw is an expression in Kotlin, so you can use it, for example, as part of an Elvis expression:

```kotlin
val s = person.name ?: throw IllegalArgumentException("Name required")
```

The throw expression has the type Nothing. This type has no values and is used to mark code locations that can never be reached. In your own code, you can use Nothing to mark a function that never returns:

```kotlin
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```

When you call this function, the compiler will know that the execution doesn't continue beyond the call:

```kotlin
val s = person.name ?: fail("Name required")

println(s)     // 's' is known to be initialized at this point
```

You may also encounter this type when dealing with type inference. The nullable variant of this type, Nothing?, has exactly one possible value, which is null. 

If you use null to initialize a value of an inferred type and there's no other information that can be used to determine a more specific type, the compiler will infer the Nothing? type:

```kotlin
val x = null           // 'x' has type `Nothing?`
val l = listOf(null)   // 'l' has type `List<Nothing?>
```

---

### Custom exceptions

It is possible, just like in Java, to create our personalized approaches, giving more assertive guidance regarding the origin of the error that occurred.

Just name our exception and extend it Throwable:

```kotlin
class IllegalVoterException(message: String) : Throwable(message)

@Throws(IllegalVoterException::class)
fun vote(name: String, age: Int) {
    if (age < 16) {
        throw IllegalVoterException("Apenas pessoas com 16 anos ou mais podem votar.")
    }

    println("Voto realizado por $name com idade: $age")
}

fun main() {
    vote("Sandrolax", 25)
    vote("Amabilly", 11)
}
```