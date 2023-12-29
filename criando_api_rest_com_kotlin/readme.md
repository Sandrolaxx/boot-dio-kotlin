# Criando uma API Rest com Kotlin e Persistência de Dados

### Objetivo

Conheça o projeto do Spring Boot, projeto que facilita a implementação de repositórios baseados em banco de dados relacionais. Nesse contexto, explore a linguagem de programação Kotlin e entenda como o projeto Spring Data JPA facilita a criação de aplicativos baseados em spring que usam tecnologias de acesso a dados.

---

#### Contextualizando JDBC, JPA, ORM e Hibernate

#### JDBC

JDBC significa Java EE Database Connectivity. É UMA API de nível de chamada, o que significa que as instruções SQL são transmitidas como sequências para a API que, então, se encarrega de executá-las no RDMS.

SQL é uma linguagem declarativa de sintaxe relativamente simples, voltada a bancos de dados relacionais.

Um banco de dados é uma coleção organizada de informações ou dados - estruturados, normalmente armazenados eletronicamente.

#### JPA

O JPA(Java Persistence API) define uma maneira para mapear Plain Old Java Objects, POJOs, para um banco de dados. Estes POJO's são chamados de entidades(Entities).

JPA, portanto, é um framework utilizado na camada de persistência para o desenvolvedor ter uma maior produtividade no contexto Java.

![Captura de tela de 2023-12-28 21-29-20](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/7eb33f45-c60f-4979-8baa-3eb305f1d7bf)
<br/>(Fonte: Digital Innovation One, 2023)

#### ORM e Hibernate

ORM (Mapeamento objeto-relacional), é uma técnica para aproximar o paradigma de desenvolvimento de aplicações orientadas a objetos ao paradigma do banco de dados relacional.

O Hibernate é uma ferramenta de consulta e persistência objeto/relacional de alta performance.

Na versão 3.x o Hibernate implementa a especificação JPA através do conceito de anotações, o que facilita ainda mais o mapeamento objeto-relacional, que pode agora ser feito diretamente na classe.

---

### Spring Data JPA

O Spring Data JPA, é a maior parte da família Spring Data, facilita a implementação de repositórios baseados em JPA. Este modulo lida com suporte aprimorado para camadas de acesso a dados baseados em JPA.

Facilita a criação de aplicações baseadas em Spring que usam tecnologias de acesse a dados.

![Captura de tela de 2023-12-28 21-44-43](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/f66779fd-cfb3-4847-9802-7bd886e02b77)<br/>(Fonte: Digital Innovation One, 2023)

---

### Domínio da Aplicação

Abaixo segue o link com a descrição do domínio da aplicação que vamos desenvolver.

Gist --> https://gist.github.com/cami-la/560b455b901778391abd2c9edea81286