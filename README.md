# apiClientes

Para visualizar a documentacao da api, acesse: http://localhost:8080/swagger-ui/index.html

## Tecnologias utilizadas
- Java 17
- Gradle
- Postman
- Swagger / Spring Open Api
- Spring
- Bean Validation
- JUnit
- H2 Database
  O H2 Database trata-se de um banco de dados relacional escrito em Java. Ele foi escolhido pela facilidade de construir e rodar o banco junto com o aplicativo, pois, uma vez configurado, ele sobe e está acessível junto da aplicação.


## Execução
### Requisitos
- Java Development Kit (JDK): Verifique se você tem o JDK instalado. O Spring requer o Java para funcionar.
- Git: Certifique-se de ter o Git instalado para clonar o repositório.
- Gradle: O Gradle é necessário para gerenciar dependências e construir o projeto.

### Como rodar
1. Clonar o Repositório
Abra o terminal e navegue até o diretório onde você deseja armazenar o projeto. Execute o seguinte comando para clonar o repositório:
````
git clone https://github.com/kassimentz/apiClientes.git
````
2. Acessar o Diretório do Projeto
   Navegue até o diretório do projeto recém-clonado:
```
cd apiClientes
```

3. Baixar as dependências
  Use o Gradle para baixar as dependências necessárias para a aplicação. No terminal, execute o seguinte comando:
```
./gradlew build
```
Isso fará com que o Gradle baixe as dependências listadas no arquivo build.gradle e compile o projeto.

4. Baixar as dependências
   Com as dependências baixadas, você está pronto para executar a aplicação. No terminal, execute:
   ```
   ./gradlew bootRun
   ```
   Aguarde até ver uma mensagem indicando que a aplicação foi iniciada na porta padrão, neste caso, na porta 8080.

5. Testar a API
   Agora podemos testar a api. Importe a collection do postman contida no projeto (src/main/resources/postman/Bhub Client Api.postman_collection.json):
   - Abra o postman
   - Clique em import
   - Clique em upload files
   - Selecione o json contido dentro do projeto e você está pronto para realizar as chamadas dos endpoints
