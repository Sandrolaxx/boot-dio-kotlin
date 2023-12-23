/**
 * O Design Pattern "Builder" é uma técnica utilizada para criar objetos complexos passo a passo,
 * separando o processo de construção da representação final do objeto. Isso ajuda a melhorar a
 * legibilidade e a flexibilidade do código, especialmente quando você precisa criar objetos com
 * muitos parâmetros ou configurações diferentes.
 *
 * Neste desafio, buscando soluções para um problema de negócios em uma plataforma de e-commerce, é
 * necessário melhorar a forma como os pedidos personalizados são tratados. Você tem a opção de
 * implementar a solução utilizando o padrão Builder para criar pedidos de forma mais eficiente e
 * organizada, ou seguir uma abordagem alternativa sem a necessidade de utilizar o padrão Builder.
 *
 * Detalhamento da tarefa:
 *
 * Capture o nome do cliente. Solicite ao usuário a quantidade de produtos que deseja adicionar ao
 * pedido. Para cada produto, capture o nome, preço e quantidade. Capture o endereço de entrega.
 * Calcule o total do pedido somando o preço de cada produto multiplicado pela quantidade. Imprima
 * os detalhes do pedido, incluindo os produtos, seus preços, quantidades, total a pagar e endereço
 * de entrega.
 */
class Product(val name: String, val price: Double, val quantity: Int)

class CustomOrder
private constructor(
        val customerName: String,
        val products: List<Product>,
        val total: Double,
        val deliveryAddress: String
) {
    /** Classe interna para "linkar" o BuIlder com a classe CustomOrder */
    class Builder {
        private var customerName: String = ""
        private var products: MutableList<Product> = mutableListOf()
        private var deliveryAddress: String = ""

        fun setCustomerName(name: String) = apply { customerName = name }
        fun addProduct(product: Product) = apply { products.add(product) }
        fun setDeliveryAddress(address: String) = apply { deliveryAddress = address }

        fun build(): CustomOrder {
            var total = 0.0
            products.forEach({ product -> total += product.quantity * product.price })

            return CustomOrder(customerName, products, total, deliveryAddress)
        }
    }

    fun printReceipt() {
        println("${this.customerName}")
        this.products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.name} | ${product.price} | ${product.quantity}")
        }
        println("Total: ${this.total}")
        println("End: ${this.deliveryAddress}")
    }
}

fun main() {
    val customerName = "Sandrolax "

    val orderBuilder = CustomOrder.Builder().setCustomerName(customerName)

    val numProducts = 2
    for (i in 1..numProducts) {
        val productName = "Mouse"
        val productPrice = 10.0
        val productQuantity = 2

        orderBuilder.addProduct(Product(productName, productPrice, productQuantity))
    }

    val deliveryAddress = "TESTE, 123"

    val customOrder = orderBuilder.setDeliveryAddress(deliveryAddress).build()

    customOrder.printReceipt()
}
