# Conhecendo o Kotlin e Sua Documentação Oficial

### Introdução

É uma linguagem de programação que mesmo sendo muito recente, já está madura, destinada a tornar os desenvolvedores(as) mais felizes.

É concisa, segura, interoperável com Java e outras linguagens, além de oferecer muitas maneiras de reutilizar código entre várias plataformas para uma programação produtiva.

#### Pontos interessantes

![Captura de tela de 2023-12-11 06-23-29](https://github.com/Sandrolaxx/boot-dio-kotlin/assets/61207420/ebc1d1bf-0e1a-499d-a524-b4c5c25c38c3)
<br/>(Fonte: kotlinlang.org, 2023)

Kotlin tem algumas iniciativas, que permitem do convencional e conhecido desenvolvimento Android nativo, desenvolvimento web com [Kotlin/JS](https://kotlinlang.org/docs/js-overview.html), desenvolvimento [back-end](https://kotlinlang.org/lp/server-side/), desenvolvimento [Mutiplataforma Nativo](https://kotlinlang.org/docs/native-overview.html) até [Data Science](https://kotlinlang.org/docs/data-science-overview.html). Isso dá muito poder e flexibilidade a linguagem, aprender uma única vez e ter uma gama de áreas para poder atuar.

Link [doc oficial](https://kotlinlang.org)

---

### Como instalar?

Kotlin está incluso no Intellij IDEA e Android Studio. Entretanto, podemos usar o Kotlin sem nenhum desses IDEs, através do [Kotlin Playgroud](https://play.kotlinlang.org).

#### ASDF

```
asdf plugin add kotlin
```

Mostra todas as versões disponíveis
```
asdf list-all kotlin
```

Instala a última versão
```
asdf install kotlin latest
```

Seta a última versão instalada como global
```
asdf global kotlin latest
```

Verificar se o Kotlin foi instalado corretamente
```
kotlin -help
```

#### Links úteis:

* [Introdução ao Kotlin](https://kotlinlang.org/docs/getting-started.html)
* [Kotlin no VS Code](https://in-kotlin.com/ide/vscode/setup-vscode-for-kotlin-development)
* [Repositório do Kotlin no GitHub](https://github.com/JetBrains/kotlin)
* [Compilador Kotlin](https://kotlinlang.org/docs/command-line.html)

---

### Porque Kotlin?

Em sua documentação oficial, temos algumas justificativas interessantes sobre esta linguagem de programação:

* Concisa
* Segura
* Expressiva
* Interoperável
* Multiplataforma
* Simples
* Funções interessantes de Assincronismo(coroutines)
* Suporte a POO e funcional
* Ideal para testes
