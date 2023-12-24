# Introdução a Banco de Dados Relacionais (SQL)

### Objetivo

Fornecer uma introdução aos Bancos de Dados relacionais e desenvolver habilidades na criação, modelagem e consulta desses bancos.

Conteúdo:

- Introdução aos Bancos de Dados Relacionais e conceitos básicos de SQL.
- Modelagem de tabelas, colunas e registros com operações CRUD.
- Chaves primárias e estrangeiras com modelagem de tabelas relacionadas.
- Normalização de dados, identificar e corrigir problemas
- Consultas avançadas com junções e subconsultas
- Funções agregadas e agrupamento de resultados com GROUP BY e HAVING
- Uso de índices para otimização de consultas(Tunning)

---

### Introdução aos Bancos de Dados Relacionais

#### Conceitos Básicos e Estrutura de Bancos de Dados Relacional

O que é um Banco de Dados?

É um conjunto de dados organizado, é estruturado e normalmente armazenado de forma eletrônica.

Tipo de bancos de dados:

- Relacionais/SQL
- Não Relacionais/NoSQL (Not OnlySQL)
- Orientado a Objetos
- Hierárquico

SGBD - Sistema de Gerenciamento de Banco de Dados: São sistemas que nos permitem realizar a manipulação do banco de dados, gerenciamento de usuários, etc.

SGBD - CRUD: Acrônimo para CREATE, READ, UPDATE e DELETE.

SGBD - Estrutura

![Captura de tela de 2023-12-24 17-24-46](https://github.com/Sandrolaxx/frostNext/assets/61207420/9534d480-8a74-4fd5-b521-5247db33ba25)

Características:

- Relacionamento entre tabalas
- Linguagem de Consulta Estruturada (SQL)
- Integridade referencial
- Normalização de dados
- Segurança
- Flexibilidade e extensibilidade
- Suporte a transação ACID

ACID: Atomicidade, Consistência, Isolamento e Durabilidade.

SGBD - Tipos de dados: Pode variar muito conforme os diversos SGBD's, os mais comuns são:

- Inteiro(Integer)
- Decimal/Numérico(Decimal/Numeric)
- Caractere/Varchar(Character/Varhcar)
- Data/Hora(Date/Time)
- Booleano(Boolean)
- Texto(Text)

#### SGBD - DDL - Comando CREATE TABLE:

- Restrições de valo: NOT NULL, UNIQUE, DEFAULT.
- Chaves primárias e estrangeiras
- Auto Incremento

Exemplo:

```SQL
CREATE TABLE USUARIOS {
    id INT,
    nome VARCHAR(255) NOT NULL COMMENT 'Nome do usuário',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT 'E-mail do usuário'
    endereco VARCHAR(50) NOT NULL COMMENT 'E-mail do usuário',
    data_nascimento DATE NOT NULL COMMNET 'Data de nascimento do usuário'
}
```

#### SGBD - DML - Comando INSERT:

- **INSERT INTO** {{NOME_TABELA}} ([coluna1, coluna2]) **VALUES** ([valorColuna1, valorColuna2])
- É possível omitir as colunas

Exemplo:

```SQL
INSERT INTO USUARIOS (id, nome, email, endereco, data_nacimento) VALUES (1, 'Sandrolax', 'sandrolax@gmail', 'Rua ludimilo fonseca', '1998-23-04');

--OU OMITINDO COLUNAS

INSERT INTO USUARIOS VALUES (1, 'Sandrolax', 'sandrolax@gmail', 'Rua ludimilo fonseca', '1998-23-04');
```

#### SGBD - DML - Comando UPDATE:

- **UPDATE** {{NOME_TABELA}} **SET** {{coluna_01}} = {{novo_valor_1}} **WHERE** {{CONDICAO}};

Exemplo:

```SQL
UPDATE USUARIOS SET nome = 'Teste Bala' WHERE id = 1;
```

#### SGBD - DML - Comando DELETE:

- **DELETE FROM** {{NOME_TABELA}} **WHERE** {{CONDICAO}};

Exemplo:

```SQL
DELETE FROM USUARIOS WHERE name = 'Sandrolax';
```

#### SGBD - DQL - Comando SELECT:

- **SELECT** {{LISTA_COLUNAS}} **FROM** {{NOME_TABELA}}
- É possível passar '\*' para retornar todas as colunas

Exemplo:

```SQL
--TRAZENDO TODAS AS COLUNAS
SELECT * FROM USUARIOS;

--TRAZENDO APENAS ID
SELECT id FROM USUARIOS;

--TRAZENDO APENAS NOME de usuarios com nome = 'Sandrolax'
SELECT id FROM USUARIOS WHERE name = 'Sandrolax';
```

Temos alguns operadores suportados no comando:

- =(igualdade)
- <> ou !(desigualdade)
- \>(maior que)
- <(menor que)
- \>=(maior ou igualque)
- <=(menor ou igual que)
- LIKE(comparação de padrões)
- IN(pertence a uma lista de valores)
- BETWEEN(entre um intervalo)
- AND(&&(e) lógico)
- OR(||(ou) lógico)

#### SQL - Structured Query Language

É uma linguagem de consulta e é amplamente utilizada para interação com nosso banco de dados. Como ele podemos manipular nossa base, tabelas, usuários e demais recursos do banco de dados.

Organização da SQL:

- DQL - Linguagem de consulta de dados --> SELECT
- DML - Linguagem de manipulação de dados --> INSERT, UPDATE e DELETE
- DDL - Linguagem de definição de dados --> CREATE, ALTER e DROP
- DCL - Linguagem de controle de dados --> GRANT, REVOKE
- DTL - Linguagem de transação de dados --> BEGIN, COMMIT e ROLLBACK

### MER e DER

O Modelo Entidade-Relacionamento(MER) é representado através de diagramas chamados Diagramas Entidade-Relacionamento(DER).

Entidades: São nomeadas com substantivos concretos ou abstratos que representam de forma clara sua função dentro do domínio.

Atributos: São as características ou propriedades das entidades. Eles descrevem informações específicas sobre uma entidade.

Relacionamentos: Representam as associações entre entidades.

Cardinalidade: Se refere como as entidades se relacionam umas com as outras, indica o número máximo de ocorrências que uma entidade pode ter associada a outra.

- Relacionamento 1..1(Um para um)
- Relacionamento 1..n(Um para muitos)
- Relacionamento n..n(Muitos para muitos)
