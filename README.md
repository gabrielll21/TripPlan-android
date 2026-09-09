# TripPlan

TripPlan é um aplicativo Android desenvolvido em Kotlin para auxiliar no planejamento de viagens. O projeto permite registrar as informações principais de uma viagem, encontrar atividades de acordo com as preferências do usuário, personalizar a atividade escolhida e consultar um resumo final.

## Sobre o projeto

O aplicativo conduz o usuário por um fluxo de quatro telas. Primeiro, são definidos o destino, as datas e as preferências da viagem. Em seguida, o TripPlan filtra e apresenta atividades relacionadas às categorias selecionadas. O usuário pode escolher uma atividade, ajustar sua duração e dificuldade e, por fim, visualizar o resumo do planejamento.

O projeto utiliza dados locais definidos no próprio código. Não há integração com APIs externas, banco de dados, autenticação ou mapas.

## Funcionalidades

- Configuração do destino da viagem.
- Seleção das datas de partida e retorno com `DatePickerDialog`.
- Seleção das preferências Aventura, Cultura e Praia.
- Filtragem das atividades pelas preferências selecionadas.
- Exibição das atividades em um `RecyclerView` com `LinearLayoutManager`.
- Cards com imagem, nome, descrição, categoria, duração e dificuldade.
- Seleção de uma atividade por callback do Adapter.
- Ajuste da duração com `SeekBar`.
- Seleção da dificuldade com `RadioButton`.
- Tela de resumo final da viagem e da atividade escolhida.
- Fluxo de navegação implementado no código entre quatro Activities.
- Impressão dos dados no console durante as transições e a finalização.

## Tecnologias utilizadas

- Kotlin 2.2.10.
- Android SDK.
- Android Studio.
- Android Views e layouts XML.
- AndroidX RecyclerView e CardView, disponibilizados pela árvore de dependências atual.
- Material Components for Android 1.14.0.
- Gradle 9.5.0 com Android Gradle Plugin 9.3.2.
- Git e GitHub.

Embora o projeto possua dependências do Jetpack Compose configuradas, as telas descritas neste README utilizam Activities e layouts XML tradicionais.

## Estrutura do projeto

### Código Kotlin

- `MainActivity.kt`: coleta o destino, as datas e as preferências e inicia o fluxo do planejamento.
- `Activity2.kt`: cria, filtra e apresenta as atividades disponíveis em um `RecyclerView`.
- `Activity3.kt`: exibe os detalhes da atividade e permite ajustar duração e dificuldade.
- `Activity4.kt`: apresenta o resumo final e registra a finalização no console.
- `Atividade.kt`: modelo de dados que representa uma atividade.
- `AtividadeAdapter.kt`: vincula a lista de atividades aos cards do `RecyclerView` e disponibiliza o callback de seleção.

### Layouts XML

- `activity_main.xml`: formulário inicial da viagem.
- `activity_activity2.xml`: cabeçalho da viagem e lista de atividades.
- `item_atividade.xml`: card reutilizável de uma atividade.
- `activity_activity3.xml`: detalhes e controles de personalização da atividade.
- `activity_activity4.xml`: resumo final do planejamento.

## Fluxo da aplicação

```text
MainActivity → Activity2 → Activity3 → Activity4
```

1. **MainActivity:** o usuário informa o destino, seleciona as datas e marca uma ou mais preferências.
2. **Activity2:** as atividades são filtradas por categoria e exibidas em cards; o usuário seleciona uma delas.
3. **Activity3:** os detalhes da atividade são exibidos e o usuário ajusta a duração e a dificuldade.
4. **Activity4:** o aplicativo apresenta o resumo final da viagem e da atividade escolhida.

O encadeamento das telas está implementado por meio de `Intent`. No estado atual do projeto, o `AndroidManifest.xml` declara apenas `MainActivity` e `Activity2`; `Activity3` e `Activity4` também precisam ser registradas no Manifest para que o fluxo completo possa ser executado em um dispositivo.

## Como executar

### Android Studio

1. Clone o repositório:

   ```bash
   git clone git@github.com:gabrielll21/TripPlan-android.git
   ```

2. Abra a pasta do projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Configure um emulador ou conecte um dispositivo Android compatível, com a depuração USB habilitada.
5. Se desejar percorrer as quatro telas, registre `Activity3` e `Activity4` no `AndroidManifest.xml` antes da execução.
6. Selecione a configuração do módulo `app` e clique em **Run**.

### Linha de comando

No Linux ou macOS, gere o APK de depuração com o Gradle Wrapper incluído no projeto:

```bash
./gradlew assembleDebug
```

No Windows:

```powershell
.\gradlew.bat assembleDebug
```

O APK gerado fica em `app/build/outputs/apk/debug/`.

## Requisitos

- Android 12 ou superior (`minSdk` 31).
- Android SDK 37 para compilação (`compileSdk` 37 e `targetSdk` 37).
- JDK 25 para a JVM do Gradle, conforme `gradle/gradle-daemon-jvm.properties`.
- Compatibilidade de código-fonte e bytecode Java 11 no módulo `app`.
- Gradle Wrapper incluído no repositório.
- Android Studio com suporte às versões de Android Gradle Plugin e Kotlin usadas pelo projeto.

## Contexto acadêmico

O TripPlan foi desenvolvido como atividade acadêmica da disciplina de **Programação para Dispositivos Móveis**.
