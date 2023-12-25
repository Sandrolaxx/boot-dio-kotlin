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

#### SGBD - DDL - Comando ALTER TABLE:

- Utilizado para modificar a estrutura da tabela
- É possível: Adicionar, alterar ou excluir colunas, modificar restrições, índices, renomear, entre outras alterações
- Caso existam dados na tabela e a alteração seja de tipo de dado, necessário criar uma coluna auxiliar, migrar os dados, alterar a coluna original e adicionar os dados novamente

Exemplo:

```SQL
ALTER TABLE USUARIOS MODIFY COLUMN endereco VARCHAR(255);
```

#### SGBD - DDL - Comando ALTER TABLE:

- Usado para remover uma tabela existente de um banco de dados
- Remove permanentemente

Exemplo:

```SQL
DROP TABLE {{tabela}}
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

#### Chave Primária

Tem por objetivo:

- Identificar exclusivamente o registro
- Não pode conter valores nulos(NULL)
- Uma tabela pode ter apenas uma chave primária

Formas de criar uma chave primária:

```SQL
--NO MOMENTO DA CRIAÇÃO DA TABELA
CREATE TABLE {{tabela}}
(id INT PRIMARY KEY AUTOINCREMENT,
...);

--APÓS TABELA CRIADA
ALTER TABLE {{tabela}} MODIFY COLUMN id INT PRIMARY KEY;
```

#### Chave Estrangeira

Ela é usada para estabelecer e mentar a integridade dos dados entre tabelas relacionadas:

- Pode ser nula(NOT NULL); \*\* registro órfão
- É possível ter mais de uma (ou nenhuma) em uma tabela
- ON DELETE especifica o que acontece com os registros dependentes quando um registro pai é excluído
- ON UPDATE define o comportamento dos registros dependentes quando um registro pai é atualizado
- CASCADE, SET NULL, SET DEFAULT e RESTRICT

```SQL
--NO MOMENTO DA CRIAÇÃO DA TABELA
CREATE TABLE {{tabela}}
(id INT PRIMARY KEY,
chave_estrangeira INT,
FOREING KEY (chave_estrangeira) REFERENCES {{outra_tabela}} (id)
...);

--APÓS TABELA CRIADA
ALTER TABLE {{tabela}}
ADD CONSTRAINT {{nome_constraint}};
FOREIGN KEY(id)
REFERENCES {{outra_tabela}} (id)
```

#### SQL - Structured Query Language

É uma linguagem de consulta e é amplamente utilizada para interação com nosso banco de dados. Como ele podemos manipular nossa base, tabelas, usuários e demais recursos do banco de dados.

Organização da SQL:

- DQL - Linguagem de consulta de dados --> SELECT
- DML - Linguagem de manipulação de dados --> INSERT, UPDATE e DELETE
- DDL - Linguagem de definição de dados --> CREATE, ALTER e DROP
- DCL - Linguagem de controle de dados --> GRANT, REVOKE
- DTL - Linguagem de transação de dados --> BEGIN, COMMIT e ROLLBACK

---

### MER e DER

O Modelo Entidade-Relacionamento(MER) é representado através de diagramas chamados Diagramas Entidade-Relacionamento(DER).

Entidades: São nomeadas com substantivos concretos ou abstratos que representam de forma clara sua função dentro do domínio.

Atributos: São as características ou propriedades das entidades. Eles descrevem informações específicas sobre uma entidade.

Relacionamentos: Representam as associações entre entidades.

Cardinalidade: Se refere como as entidades se relacionam umas com as outras, indica o número máximo de ocorrências que uma entidade pode ter associada a outra.

- Relacionamento 1..1(Um para um)
- Relacionamento 1..n(Um para muitos)
- Relacionamento n..n(Muitos para muitos)

---

### Modelagem de Dados

Modelagem de dados é um processo realizado no momento em que estamos criando nossa base, nesse ponto devemos refletir sobre qual a melhor forma de criar as tabelas e seus relacionamentos, para em um futuro próximo não tenhamos problemas ao ter um banco de dados modelado incorretamente, o que pode gerar muita dor de cabeça e custos adicionais ao projeto, uma vez que realizar essas grandes alterações com o sistema em produção não é algo trivial. Para nos auxiliar na modelagem temos as formas normais, que nos guiam na modelagem correta de nosso banco, aqui abordaremos as três principais.

#### Primeira Forma Normal

Estabelece que cada valor em uma tabela deve ser atômico, ou seja, indivisível. Nenhum campo deve conter múltiplos valores ou listas. Temos o caso clássico do campo "endereco" como coluna, pois ele contém múltiplos valores, como rua, número, cidade e estado. Para atingir a 1FN, precisamos dividir o campo "endereco" em colunas separadas.

Ela garante que cada valor seja atômico e que os registros sejam únicos e identificáveis.

#### Segunda Forma Normal

Estabelece que uma tabela deve estar na 1FN e todos os atributos não chave devem depender totalmente da chave primária. **Dica** se sua tabela tem uma chave primária simples não existe a possibilidade de termos dependência parcial e por tanto ela já se encontra na 2FN.

Ela garante que os atributos não chave dependam da chave primária, evitando dependências parciais.

#### Terceira Forma Normal

Deve estar conforme a 1FN e 2FN, essa forma diz que nenhuma coluna não-chave pode depender de outra coluna não-chave. Exemplo, relação Estado --> Cidade.

Ela elimina dependências transitivas entre os atributos não chave, garantindo que cada atributo não chave dependa apenas da chave primária, não havendo dependências indiretas entre eles.
