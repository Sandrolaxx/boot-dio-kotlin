fun main() {
    val titulo = "Senhor dos Aneis"
    val autor = "J. R. R Tolkien"
    
    val slugTitulo = titulo.generateSlug()
    val slugAutor = autor.generateSlug()
    
    println("Slug gerado para o livro: ${slugTitulo}_${slugAutor}")
}

fun String.generateSlug(): String {
    val regex = Regex("[^a-z | \\-]")

    return this.lowercase().replace(regex, "").replace(" ", "-")
}