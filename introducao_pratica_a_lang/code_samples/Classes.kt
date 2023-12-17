class Customer

class Contact(val id: Int, var email: String)

fun main() {

    val customer = Customer()

    val contact = Contact(1, "clebim@gmail.com")

    println(contact.id)
    println(contact.email)

    contact.email = "sandrolax@gmail.com"

    println(contact.email)
    
}
