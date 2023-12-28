# Contextualizando o Desenvolvimento Web com Spring Boot 3

### Objetivo

Para o desenvolvimento de uma aplicação web, é importante utilizar ferramentas modernas e confiáveis para garantir a qualidade, desempenho e segurança de um software. Neste curso, vamos conhecer algumas ferramentas pertinentes para o desenvolvimento de um produto computacional de qualidade, uma Rest API Spring Boot e Kotlin.

Percurso:

- Parte 1 - Entendendo a Arquitetura Rest

- Parte 2 - Overview do Spring Framework

- Parte 3 - Arquitetura de três camadas com Spring Boot(Controller, Service, Model)

---

### Entendendo Arquitetura Rest

#### O que é API?

Significa Application Programming Interface. No contexto de API's, a palavra Aplicação refere-se a qualquer software com uma função distinta. A Interface pode ser pensada como um contrato de serviço entre duas aplicações.

Esse contrato define como as duas se comunicam usando solicitações e respostas. A documentação de suas respectivas API's contém informações sobre como os desenvolvedores devem estruturar essas solicitações e respostas.

#### Como as API's funcionam?

A arquitetura da API é geralmente explicada em termos de cliente e servidor. A aplicação que envia a solicitação é chamada de cliente e a aplicação que envia a resposta é chamada de servidor.

Exemplo visual:

![Captura de tela de 2023-12-28 06-24-19](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/99400c3f-50fe-4868-8c23-9b8522542f4c)
<br/>(Fonte: Digital Innovation One, 2023)

#### Só existe o REST para realizar essa comunicação?

Não temos mais algumas opções:

- API's SOAP: Cliente e servidor trocam mensagens usando XML. Esta é uma API menos flexível que era mais popular no passado.
- API's RPC: O cliente conclui uma função(ou um procedimento) no servidor e o servidor envia a saída de volta ao cliente.
- API's WebSocket: O servidor pode enviar mensagens de retorno de chamada a clientes conectados, tornando-o mais eficiente que a API REST.
- API's REST: O cliente envia a solicitação ao servidor como dados. O servidor usa essa entrada do cliente para iniciar funções internas e retorna os dados de saída ao cliente.

#### O que são API's REST?

REST significa Transferência Representacional de Estado. Clientes e servidores trocam dados utilizando HTTP.

O HTTP permite, criar, atualizar, pesquisar, executar e remover operações, atuando sob determinados recursos. A principal característica da API REST é a ausência de estado.

![Captura de tela de 2023-12-28 06-34-47](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/ee886120-66cd-4a5d-81cc-51060adb377e)
<br/>(Fonte: Digital Innovation One, 2023)

#### Métodos e Status HTTP

![Captura de tela de 2023-12-28 06-42-49](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/e07fb5ac-7a5b-4280-8c2b-22df6d13afd4)
<br/>(Fonte: Digital Innovation One, 2023)

#### JSON

É um formato de troca de dados entre sistemas independente de linguagem de programação derivado do JavaScript. É frequentemente utilizado em aplicações Ajax, configurações, banco de dados e serviços web RESTful.

#### API em Resumo

![Captura de tela de 2023-12-28 06-43-09](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/9cd7b49a-69fd-47cf-a8e8-99250d4f77a8)
<br/>(Fonte: Digital Innovation One, 2023)

---

### Overview do Spring Framework

O Spring é um framework Java criado com o objetivo de facilitar o desenvolvimento de aplicações. Baseado na IoC e DI, fornecendo para isso um container, que representa o núcleo do framework e que é responsável por criar e gerenciar os componentes da aplicação, os quais são comumente chamados de beans.

Spring Boot é um framework Java open source, ele traz mais agilidade para o processo de desenvolvimento, uma vez que devs conseguem reduzir o tempo gasto com as configurações iniciais.

#### O que é o Spring Framework?

Consiste em recursos organizados em cerca de 20 módulos.

![Captura de tela de 2023-12-28 06-52-56](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/ef72f01e-5cbb-4dad-8f9e-31e9dd65dfa5)
<br/>(Fonte: Digital Innovation One, 2023)

#### Spring Boot Starters

Com o Spring Boot conseguimos abstrair e faciliar a configuração de, por exemplo:

- Servidores;
- Gerenciamento de dependências;
- Configuração de bibliotecas;
- Métricas & health checks;
- Entre outros!

Os starters são dependências que agrupam outras dependências com um propósito em comum. Dessa forma, somente uma configuração é realizada no seu gerenciador de dependências. Por exemplo, o spring-boot-starter-test, contém funcionalidades úteis e anotações que facilitam e ajudam a testar sua aplicação.

#### Spring Initializr

Para facilitar a criação de aplicações utilizando outras IDE's o Spring disponibilizou o Spring Initializr. Ele é uma UI que permite a criação de projetos Spring Boot de forma facilitada.

---

### Arquitetura de Três Camadas(MVC)

A arquitetura em três camadas tem por objetivo promover a separação das funcionalidades usando camadas para a separação da lógica de apresentação, lógica de negócio e lógica de acesso a dados.

A separação em três camadas torna o sistema mais flexível, permitindo que as camadas sejam desenvolvidas e modificadas independentemente.

Exemplo visual:
![Captura de tela de 2023-12-28 07-04-07](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/6d9aa9d9-45d8-4bbe-8096-f90b36a9b5a2)
<br/>(Fonte: Digital Innovation One, 2023)

#### Arquivo de Configuração

Ao trabalhar com o Spring Boot, nos deparamos com várias configurações que devem ser realizadas. O Spring Boot permite utilizar dois tipos de arquivos de configurações: `application.properties` ou `application.yml`.

Para mais sobre [Arquitetura em Camadas](https://www.ibm.com/br-pt/topics/three-tier-architecture)


[Arquitetura de três camadas vc Arquitetura MVC](https://ichi.pro/pt/arquitetura-de-tres-camadas-vs-arquitetura-mvc-33882218292263)