# Desafio Java RF

## Tecnologias utilizadas
### Java 8
Optei pelo Java 8 principalmente pela nova API de datas. Existe apenas 1
lambda utilizado para implementar uma interface funcional.
### Gradle
Gradle pela familiaridade, já que é o gestor de dependências padrão para
desenvolvimento Android. Esse projeto vai com seu
[Gradle Wrapper](https://docs.gradle.org/3.3/userguide/gradle_wrapper.html),
para que os scripts Gradle (build, test, bootRun, etc.) possam ser
executados sem a necessidade de instalação do Gradle.
### Spring Boot (versão 1.5.4.RELEASE)
Com os starters:
  - Web
  - Thymeleaf
  - Test

Spring boot pela agilidade dos starters, Thymeleaf pela similaridade dos
templates com o desenvolvimento web convencional.
## Decisões Arquiteturais
Isolei os tipos de transferência em um  ```Enum``` e utilizei o padrão
de projeto _Strategy_. Fiz com que cada valor do ```Enum``` recebesse
sua implementação de cálculo no construtor, garantindo que possíveis
novos tipos de transferências não sejam criadas sem sua devida
implementação.
## Como executar o projeto
#### Bash (Unix/OSX)
```
./gradlew bootRun
```
#### CMD (Windows)
```
gradlew.bat bootRun
```
O servidor vai iniciar na porta ```8080```
