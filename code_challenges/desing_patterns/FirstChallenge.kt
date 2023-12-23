/**
 * O Singleton é uma abordagem de design de software que visa assegurar a existência de apenas uma
 * instância de uma classe e fornecer um ponto centralizado para acessá-la. Isso é especialmente
 * benéfico em contextos nos quais é desejável manter uma única ocorrência de uma classe responsável
 * pelo controle de um recurso compartilhado, como configurações, conexões de banco de dados ou
 * caches.
 *
 * Neste desafio, você deve criar um sistema de gerenciamento de usuários que permita adicionar e
 * listar usuários. Você tem a opção de implementar o padrão Singleton para garantir que haja apenas
 * uma instância do gerenciador de usuários em toda a aplicação. No entanto, a implementação do
 * padrão Singleton é opcional e você pode optar por seguir uma abordagem diferente para resolver o
 * desafio, se preferir.
 *
 * Especificações do Desafio:
 *
 * Crie uma classe User com os seguintes atributos: id (inteiro) e name (string). Implemente uma
 * classe UserManager que siga o padrão Singleton. Esta classe deve possuir as seguintes
 * funcionalidades: a. Adicionar um novo usuário ao sistema, recebendo o nome como entrada. b.
 * Listar todos os usuários cadastrados. No programa principal (main), siga as etapas abaixo: a.
 * Solicite ao usuário a quantidade de usuários que deseja cadastrar. b. Peça ao usuário para
 * informar os nomes dos usuários, um por linha. c. Após receber os nomes e cadastrar os usuários,
 * liste os usuários cadastrados.
 * 
 * Exemplo Entrada:2, Ada, Linus --> Saída: 1 - Ada, 2 - Linus
 */
class UserChallenge(val id: Int, val name: String)

/*
 * No Kotlin, a declaração de um objeto (por meio da palavra-chave object)
 * é uma maneira concisa e eficaz de implementar o padrão Singleton.
 */
object UserManager {

    private val users = mutableListOf<UserChallenge>()

    fun addUser(name: String) {
        val existingUser = users.find { it.name == name }
        val lastUser = users.lastOrNull()
        val id = if (lastUser != null) lastUser.id + 1 else 1

        if (existingUser == null) {
            users.add(UserChallenge(id, name))
        }
    }

    fun listUsers() {
        users.forEach { user -> println("${user.id} - ${user.name}") }
    }
}

fun main() {
    val quantity = readLine()?.toIntOrNull() ?: 0

    for (i in 1..quantity) {
        val name = readLine() ?: ""
        UserManager.addUser(name)
    }

    UserManager.listUsers()
}
