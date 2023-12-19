fun main() {
    val filterNumbers = listOf(1, -2, 3, -4, 5, -6)

    val positives = filterNumbers.filter { x -> x > 0 }

    val negatives = filterNumbers.filter { it < 0 }

    println("Filter: ")
    println(positives)
    println(negatives)

    println("--------------------\n")

    val mapNumbers = listOf(1, -2, 3, -4, 5, -6)

    val doubled = mapNumbers.map { x -> x * 2 }

    val tripled = mapNumbers.map { it * 3 }

    println("Map: ")

    println(doubled)
    println(tripled)

    println("--------------------\n")

    val anyNumbers = listOf(1, -2, 3, -4, 5, -6)
    val anyNegative = anyNumbers.any { it < 0 }
    val anyGT6 = anyNumbers.any { it > 6 }

    print("Any: ")

    print(anyNegative)
    print(" - " + anyGT6 + "\n")

    val allNumbers = listOf(1, -2, 3, -4, 5, -6)
    val allAllEven = allNumbers.all { it % 2 == 0 }
    val allAllLess6 = allNumbers.all { it < 6 }

    print("All: ")

    print(allAllEven)
    print(" - " + allAllLess6 + "\n")

    val noneNumbers = listOf(1, -2, 3, -4, 5, -6)
    val allEven = noneNumbers.none { it % 2 == 1 }
    val allLess6 = noneNumbers.none { it > 6 }

    print("None: ")

    print(allEven)
    print(" - " + allLess6 + "\n")
    println("--------------------\n")

    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")

    val firstW = words.find { it.startsWith("some") }
    val lastW = words.findLast { it.startsWith("some") }

    val nothing = words.find { it.contains("nothing") }

    println("Find First: ")
    println(firstW)

    println("Find Last: ")
    println(lastW)

    println("Find Nothing: ")
    println(nothing)

    println("--------------------\n")

    val numbers = listOf(1, -2, 3, -4, 5, -6)

    val first = numbers.first()
    val last = numbers.last()

    val firstEven = numbers.first { it % 2 == 0 }
    val lastOdd = numbers.last { it % 2 != 0 }

    println("First Number: ")
    println(first)

    println("Last Number: ")
    println(last)

    println("First Even Number: ")
    println(firstEven)

    println("Last Odd: ")
    println(lastOdd)

    println("--------------------\n")

    val words2 = listOf("foo", "bar", "baz", "faz")
    val empty = emptyList<String>()

    val firstOrNull = empty.firstOrNull()
    val lastOrNull = empty.lastOrNull()

    println("First Or Null: ")
    println(firstOrNull)

    println("Last Or Null: ")
    println(lastOrNull)

    val firstF = words2.firstOrNull { it.startsWith('f') }
    val lastZ = words2.lastOrNull { it.endsWith('z') }

    println("First 'f' Or Null: ")
    println(firstF)

    println("Last 'z' Or Null: ")
    println(lastZ)

    println("--------------------\n")

    val countNumbers = listOf(1, -2, 3, -4, 5, -6)

    val totalCount = countNumbers.count()
    val evenCount = countNumbers.count { it % 2 == 0 }

    println("Total count: ")
    println(totalCount)

    println("Even count: ")
    println(evenCount)

    println("--------------------\n")

    data class Person(val name: String, val city: String, val phone: String)

    val people =
            listOf(
                    Person("John", "Boston", "+1-888-123456"),
                    Person("Sarah", "Munich", "+49-777-789123"),
                    Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
                    Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
            )

    val phoneBook = people.associateBy { it.phone }
    val cityBook = people.associateBy(Person::phone, Person::city)
    val peopleCities = people.groupBy(Person::city, Person::name)
    val lastPersonCity = people.associateBy(Person::city, Person::name)

    println("All peaple" + people)
    println("associateBy - Phone: ")
    println(phoneBook)
    println("associateBy - City: ")
    println(cityBook)
    println("associateBy - Cities: ")
    println(peopleCities)
    println("associateBy - Last Person City: ")
    println(lastPersonCity)

    println("--------------------\n")

    val partitionNumbers = listOf(1, -2, 3, -4, 5, -6)
    val evenOdd = partitionNumbers.partition { it % 2 == 0 }

    println("Partition: ")
    println(partitionNumbers)

    println("Partition - Even Odd: ")
    println(evenOdd)

    val (pos, negat) = partitionNumbers.partition { it > 0 }

    println("Partition - Positives: ")
    println(pos)

    println("Partition - Negatives: ")
    println(negat)

    println("--------------------\n")

    val fruitsBag = listOf("apple", "orange", "banana", "grapes")
    val clothesBag = listOf("shirts", "pants", "jeans")
    val cart = listOf(fruitsBag, clothesBag)

    val mapBag = cart.map { it }
    val flatMapBag = cart.flatMap { it }

    println("FlatMap - Map Exemple: ")
    println(mapBag)

    println("FlatMap - FlatMap Exemple: ")
    println(flatMapBag)

    println("--------------------\n")

    val minOrNullNumbers = listOf(1, 2, 3)
    val emptyMinOrNullNumbers = emptyList<Int>()
    val only = listOf(3)

    println("minOrNull, maxOrNull: ")
    println(minOrNullNumbers)

    println(
            "Numbers: $minOrNullNumbers, min = ${minOrNullNumbers.minOrNull()} max = ${minOrNullNumbers.maxOrNull()}"
    )
    println(
            "Empty: $emptyMinOrNullNumbers, min = ${emptyMinOrNullNumbers.minOrNull()}, max = ${emptyMinOrNullNumbers.maxOrNull()}"
    )
    println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")

    println("--------------------\n")

    val shuffled = listOf(5, 4, 2, 1, 3, -10)
    val natural = shuffled.sorted()
    val inverted = shuffled.sortedBy { -it }
    val descending = shuffled.sortedDescending()
    // val descendingBy = shuffled.sortedByDescending { abs(it) }

    println("Sorted - natural: ")
    println(natural)

    println("Sorted - inverted: ")
    println(inverted)

    println("Sorted - descending: ")
    println(descending)

    // println("Sorted - descendingBy: ")
    // println(descendingBy)

    println("--------------------\n")

    val map = mapOf("key" to 42)

    println("Map Element Access - Map: ")
    println(map)

    val value1 = map["key"]
    val value2 = map["key2"]

    println("Map Element Access - value1: ")
    println(value1)

    println("Map Element Access - value2: ")
    println(value2)

    val value3: Int = map.getValue("key")

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key2")

    println("Map Element Access - value3: ")
    println(value3)

    println("Map Element Access - value4: ")
    println(value4)

    try {
        map.getValue("anotherKey")
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }

    println("--------------------\n")

    val A = listOf("a", "b", "c")
    val B = listOf(1, 2, 3, 4)

    val resultPairs = A zip B
    val resultReduce = A.zip(B) { a, b -> "$a$b" }

    println("Zip - resultPairs: ")
    println(resultPairs)

    println("Zip - resultReduce: ")
    println(resultReduce)

    println("--------------------\n")

    val list = listOf(0, 10, 20)

    println("Get Or Else - list: ")
    println(list)

    println(list.getOrElse(1) { 42 })
    println(list.getOrElse(10) { 42 })

    val mapOrElse = mutableMapOf<String, Int?>()

    println("Get Or Else - map: ")
    println(mapOrElse)

    println(mapOrElse.getOrElse("x") { 1 })

    mapOrElse["x"] = 3
    println(mapOrElse.getOrElse("x") { 1 })

    mapOrElse["x"] = null
    println(mapOrElse.getOrElse("x") { 1 })
}
