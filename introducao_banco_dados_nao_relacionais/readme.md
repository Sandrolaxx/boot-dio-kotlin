# Introdução a Banco de Dados NoSQL

### O que é um banco de dados não relacional?

- Termo correto: Not Only SQL
- Não seguem modelo de tabelas relacionais
- Projetados para lidar com alto volume de dados, alta escalabilidade
- Alta flexibilidade na estrutura de dados
- Eles são amplamente utilizados em cenários onde a consistência imediata dos dados não é crítica

#### Diferenças

| SQL                               | NoSQL                                     |
| --------------------------------- | ----------------------------------------- |
| Modelo fixo de dados              | Modelo de dados flexível                  |
| Escalabilidade vertical(hardware) | Escalabilidade horizontal                 |
| Transações ACID 100%              | Transações ACID ausentes total ou parcial |
| Linguagens de consulta SQL        | Cada SGBD tem sua própria                 |

#### Vantagens

- Flexibilidade na modelagem
- Alta escalabilidade
- Melhor desempenho em cenário de consulta intensiva
- Tolerância a falhas

#### Desvantagens

- Menor consistência de dados imediata
- Menor suporte a consultas complexas \*\*depender do SGBD

---

### Visão geral dos tipos de NoSQL

Dividido em tipos:

- Key-Value
- Documento
- Coluna
- Grafos
- Entre outros...

#### Key-Value - Chave Valor

Armazena dados como pares de chave e valor, onde cada chave é um identificador único para acessar o valor correspondente.

> Exemplos de SGBD: Redis, Riak, Amazon DynamoDB

Uso: Um site pode usar o banco Redis para armazenar informações de sessão de usuário.

#### Documento

Armazenam dados em documentos semiestruturados, geralmente em formato JSON ou BSON.

> Exemplo de SGBD: MongoDB, Couchbase, Apache, CouchDB

Uso: Um catálogo de e-commerce pode usar o MongoDB para armazenar informações de produtos, como nome, descrição, preço e atributos adicionais.

#### Coluna

Armazena dados em formato de colunas, o que permite alta escalabilidade e eficiência em determinados tipos de consultas.

> Exemplos de SGBD: Apache Cassandra, ScyllaDB, HBase

Uso: Um sistema de registro de aplicativos pode usar o Apache Cassandra para armazenar registros de log.

#### Grafos

Armazena e consulta dados interconectados, onde os relacionamentos entre os dados são tão importantes quanto os próprios dados.

> Exemplos de SGBD: Neo4j, Amazon Neptune, JanusGraph

Uso: Uma rede social pode usar o Neo4j para armazenar os perfis dos usuários em suas conexões, permitindo consultas eficientes para encontrar amigos em comum.
