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

---

### Introdução ao MongoDB

#### O que é o MongoDB?

- Banco de dados NoSQL orientado a documentos
- Grandes volumes de dados, escalabilidade horizontal e modelagem flexível
- Não existe um esquema
- Permite que os documentos sejam armazenados em formato BSON (Binary JSON), proporcionando uma estrutura semiestruturada

#### Vantagens

- Flexibilidade na modelagem dos dados
- Escalabilidade horizontal para lidar com grandes volumes de dados
- Consultas ricas e suporte a consultas complexas
- Alta disponibilidade e tolerância a falhas
- Comunidade ativa e recursos de suporte

#### Desvantagens

- Menor consistência imediata em comparação com bancos de dados relacionais
- Consultas complexas podem exigir maior conhecimento e planejamento adequado
- Maior consumo de espaço de armazenamento em comparação com bancos de dados relacionais devido à flexibilidade dos documentos

#### Onde ele é usado?

- Aplicação Web: Onde a flexibilidade e a escalabilidade são cruciais para lidar com volumes variáveis de dados
- Análise de big data: Análise de grandes volumes de dados não estruturados ou semiestruturados, fornecendo uma plataforma para armazenar e processar esses dados
- Armazenamento de dados semiestruturados: Permite a inserção de documentos com estruturas diferentes em uma mesma coleção
- Caso de uso de geolocalização: Com suas funcionalidades de consulta geoespacial, é adequado para casos de uso que envolvem dados baseados em localização, como aplicativos de mapeamento e rastreamento

---

### Modelando dados usando documentos

#### Estrutura do MongoDB

É dividido em documentos, coleções que são uma lista de documentos e database que possui uma série de coleções.

![Captura de tela de 2023-12-26 06-25-02](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/d0af804a-885b-4b65-a3b2-1a3c44e5fc28)
<br/>(Fonte: Digital Innovation One, 2023)

Coleções: Agrupamento lógico de documentos, não exige esquema ou que os documentos tenham uma mesma estrutura.

Algumas regras de criação de coleções:

- Devem começar com uma letra ou underscore(\_)
- Podem conter letras, números ou underscores
- Não podem ser vazios
- Não podem ter mais de 64 bytes de comprimento

Documentos: São armazenados em documentos BSON(Binary JSON), que são estruturas flexíveis e semiestruturadas. Cada documento possui um identificador único chamado "\_id". São compostos por pares de chaves e valores.

Pontos de atenção:

- Tamanho máximo: Cada documento no MongoDB pode ter um tamanho máximo de 16 MB
- Aninhamento de documentos
- Flexibilidade na evolução do esquema

#### Tipos de Dados Simples

- String
- Number
- Boolean
- Date
- Null
- ObjectId

#### Tipos de Dados Complexos

- Array
- Documento Embutidos(Embedded Document)
- Referência(Reference)
- GeoJSON

Estrutura de um documento

```json
{
    "_id": ObjectId(""),
    "nome_campo": "valor_campo",
    ...
}
```

---

### Estratégias de modelagem de dados eficientes e escaláveis

#### Modelagem orientada por consultas

A modelagem de dados no MongoDB deve ser orientada palas consultas que serão realizadas com mais frequência.

#### Inner Documents

No MongoDB, é comum desnormalizar os dados para evitar operações de junção(join) custosas. Isso significa que os dados relacionados podem ser armazenados juntos em um único documento, em vez de serem distribuídos em várias coleções.

Exemplo:

```json
"_id": ObjectId("1231231212312312"),
"nome": "Sandro Ramos",
"reserva": [{
    "_id": ObjectId("123"),
    "destino": "Cataratas",
    "data": "2023-12-27",
}]
```

Quando usar?

Os dados aninhados são específicos para o documento pai. Os dados aninhados são sempre acessados juntamente com o documento pai. Normalmente a cardinalidade do relacionamento é um-para-muitos(Exemplo: um usuário pode ter várias reservas)

Quando não utilizar?

Se os dados aninhados precisarem ser consultados e atualizados independentemente do documento pai, é mais adequado utilizar coleções separadas.

#### Referências

Forma de relacionar os documentos entre si.

Aqui, ao invés de criarmos o inner document, vamos apenas realizar referência ao id do outro documento.

Exemplo:

```json
"_id": ObjectId("123"),
"destino": "Cataratas",
"data": "2023-12-27",
"usuario": ObejectId("12312312")//Referência em reserva ao usuário
```

Quando usar?

Os dados têm seu próprio significado e podem ser acessados independentemente do documento pai. Os dados têm uma cardinalidade mais alta (por exemplo, vários usuários podem ter reservas).

Quando Não usar?

Se os dados aninhados precisarem ser consultados e atualizados independentemente do documento pai, é mais adequado utilizar coleções separadas.

---

### Operações no MongoDB

#### Criando um DataBase

```
use {{nome_do_banco}}
```

Enquanto o database não tiver uma collection ele não será apresentado na lista.

#### Criando coleção

```
db.createCollection("destinos")
```

#### Inserindo Documentos

```
db.usuarios.insertOne({});

// Suporta múltiplas inserções
db.usuarios.insertMany([{}]);
```

Exemplo:

```
db.usuarios.insertOne({
    "nome": "Sandrolaxx",
    "email": "sandrolaxx@gmail.com",
    "idade": 30,
    "data_nascimento": "1998-03-10"
})
```

#### Consultando Documentos

- db.usuarios.find({})
- db.usuarios.findOne({})
- db.usuarios.findOneAndUpdate({}, {$set:{}})
- db.usuarios.findOneAndDelete({})

#### Atualização Documentos

- db.usuarios.updateOne({}, {$set:{}})
- db.usuarios.updateMany({}, {$set:{}})
- $inc -- Incrementar
- $push -- Adicionar elemento array
- $set -- atualizar valor
- $unset -- deleta o valor em específico
- $rename -- atualizar o nome do campo

#### Atualização Documentos

- db.usuarios.deleteOne({})
- db.usuarios.deleteMany({})

---

### Consultas

#### Operador de igualdade

Realiza consultas baseadas em um valor específico para um campo.

Exemplo:

```
db.usuarios.find({"endereco.cidade": "Cascavel"})
```

#### Operadores lógicos

Realizar consultas baseadas em um valor específico para um campo.

- $and
- $or
- $not

Exemplo and:

```
db.usuarios.find({$and:[{idade: 25}, {nome: "Sandrolaxx"}]})
```

Exemplo or:

```
db.usuarios.find({$or:[{idade: 22}, {nome: "Flavia"}]})
```

#### Operador de Comparação

-$eq: ==
-$ne: != 
-$gt: >
-$gte: >= 
-$lt: <
-$lte: <= 
-$in: []
-$nin: []

Exemplo eq:

```
db.usuarios.find({idade: {$ne: 25}})
```

Exemplo gt:

```
db.usuarios.find({idade: {$gt: 22}})
```

Exemplo lt:

```
db.usuarios.find({idade: {$lt: 24}})
```

Exemplo in:

```
db.usuarios.find({nome: {$in: ["Sandrolaxx", "Robert"]}})
```

Exemplo nin:

```
db.usuarios.find({nome: {$nin: ["Sandrolaxx", "Robert"]}})
```

#### Projeções

Definir quais campos devem ser retornados da consulta.

Exemplo:

```
db.usuarios.find({nome: {$eq: "Sandrolaxx"}}, {nome: 1})
```

#### Ordenação

Ordenar os resultados de uma consulta com base em um ou mais campos.

Exemplo:

```
db.usuarios.find({idade: {$gt: 20}}, {nome: 1, idade: 2}).sort({idade: 1})
```

#### Limitação

Limitar a quantidade de número de documentos retornados em uma consulta.

Exemplo:

```
db.usuarios.find({idade: {$gt: 20}}, {nome: 1, idade: 2}).sort({idade: 1}).limit(2)
```

#### Paginação

```
db.usuarios.find().skip(10).limit(5)
```

---

### Redis

#### O que é o Redis?

O Redis é um sistema de armazenamento de dados em memória de alto desempenho.

#### Principais características

- Armazenamento em Memória
- Estrutura de Dados Versátil
- Operações Atômicas
- Cache de Alto Desempenho
- Pub/Sub (Publicação/Subscrição)

#### Principais utilizações

- Cache de dados
- Filas de Mensagens
- Contagem de acessos e estatísticas em tempo real
- Gerenciamento de Sessões
- Cache de resultados de consultas

#### Principais comandos

- SET       - Adicionar novo valor
- GET       - Buscar o valor
- DEL       - Deletar valor
- EXISTS    - Verificar se existe uma chave(ela não expirou ainda) 
- KEYS      - Retornar chaves correspondentes
- INCR      - Realizar incremento de um valor
- DECR      - Realizar decremento de um valor
- EXPIRES   - Define o tempo de vida do registro
- TTL       - Retorna o tempo de vida do nosso registro

> É possível testar o redis em [try.redis.io](https://try.redis.io/)

Exemplos de comandos:

```
set nome "sandrolax"
set nome_2 "gabs"

get nome
//Retorno "sandrolax"

keys nome*
//Retorno 
//1) "nome_2"
//2) "nome"

del nome
//Retorno (integer) 1

expire nome 10
//Vai realizar a exclusão daqui 10s

exists nome
//Retorno (integer) 1

exists nome_x
//Retorno (integer) 0


set acessos 1

incr acessos
//Retorno (integer) 2

decr acessos
//Retorno (integer) 1
```

Criando um array

```
LPUSH usuarios "Sandrolax" "Jooj" "Roberto"
//Retorno (integer) 3

//Listando dados array
lrange usuarios 0 -1
//Retorno
1) "Roberto"
2) "Jooj"
3) "Sandrolax"

//Adicionar novo elemento
LPUSH usuarios "Clebim"
//Retorno (integer) 4

//Retorna o tamanho da lista
LLEN usuarios
(integer) 4
```