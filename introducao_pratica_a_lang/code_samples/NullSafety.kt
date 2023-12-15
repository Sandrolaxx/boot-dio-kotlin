fun main() {
    var neverNull: String = "This can't be null"

    // neverNull = null //Null can not be a value of a non-null type String

    var nullable: String? = "You can keep a null here"

    nullable = null

    var inferredNonNull = "The compiler assumes non-null"

    // inferredNonNull = null  //Null can not be a value of a non-null type String

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    strLength(neverNull)
    println(nullable)
    // strLength(nullable)  //Type mismatch: inferred type is Nothing? but String was expected

    fun describeString(maybeString: String?): String {
        if (maybeString != null && maybeString.length > 0) {
            return "String of length ${maybeString.length}"
        } else {
            return "Empty or null string"
        }
    }

    println(describeString(neverNull))
    println(describeString(nullable))
}
