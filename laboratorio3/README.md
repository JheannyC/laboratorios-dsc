# Laboratório - roteiro 3


### Objetivos:
* Inserir alguma segurança em nossa API usando json web tokens (jwt) na API de disciplinas

### Tecnologias envolvidas:
* ORM - Mapeamento objeto relacional (Hibernate é a impementação por trás do que usaremos)
* JPA - interface unificada para facilitar mapeamento de objetos para registros de tabelas
* JWT - tokens para autorização de acesso a dados

Lembrete: use o [spring initizlizr](https://start.spring.io) para criar seu projeto spring. Desas vez marque as dependências "_Spring Web Starter_", "_H2 Database_" e "_Spring Data JPA_" na configuração do seu projeto. Você também pode inserir a dependência do Lombok caso queira usar suas facilidades para o desenvolvimento dos DTOs (data transfer objects).

Faça o unzip do arquivo zip criado para o seu workspace. Na sua IDE de preferência (eclipse, IntelliJ, etc.) importe o projeto criado como um projeto maven já existente. Agora você já pode desenvolver sua aplicação. Lembre de organizar tudo em pacotes específicos. Antes de continuar é preciso adicionar no pom.xml a dependência do JWT:

```xml
    <dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.1</version>
    </dependency>
```

Neste terceiro lab iremos inserir um novo recurso ao código que já vínhamos desenvolvendo no lab 2. Agora vamos adicionar usuários, autenticação (via login), e autorização com JWT. Até agora, a versão da API ainda é muito reduzida, pois a idéia de usuário ainda não tinha sido introduzida. Quando um like é dado apenas incrementa o contador de likes da disciplina e quando um comentário é feito o novo comentário não está associado a usuários. Neste terceiro roteiro de laboratório vamos continuar a partir do código do lab2.

Use Spring Boot e java para desenvolver as seguintes novas rotas à nossa API de disciplinas (por facilidade, vamos marcar o que já foi feito no lab 2):

**<MÉTODO HTTP> /api/usuarios**<br>
Adiciona um usuario com email, nome e senha (outros atributos podem ser inseridos se desejar). O email é o identificador único do usuario. Retorna um JSON que representa o usuário inserido (claro que sem a senha) e código <código de resposta HTTP>. Ou não retorna JSON e <código de resposta HTTP> caso o identificador de usuário passado já exista na base de dados.

**<MÉTODO HTTP> /api/login** <br>
Recebe email e senha de um usuário, verifica na base de dados de usuários se esse usuário existe, e se a senha está correta para realizar a autenticação. Se o usuário for autenticado este recurso deve gerar um JWT que deve ser retornado para o cliente. Retorna um JSON que representa o usuário inserido (claro que sem a senha) e código <código de resposta HTTP>.
* Informações adicionais sobre essa funcionalidade: o JWT gerado deve carregar a informação de subject (email do usuário), o tempo de expiração do token deve ser determinado por cada desenvolvedor (que deve saber justificar sua decisão).

**<MÉTODO HTTP>  /api/usuarios/{email}**  <br>
Remove o usuário cujo identificador é passado ({email}). É preciso garantir que o usuário requisitando este recurso é o mesmo usuário do {email} passado (esta identificação é feita através do token passado no authorization header da requisição HTTP). Só o próprio usuário pode remover sua conta. Retorna informação do usuário removido (em um JSON no corpo da resposta) e código <código de resposta HTTP>.
* Detalhes: Esta ação só pode ser realizada pelo próprio usuário que quer se remover, assim é preciso receber um JWT na requisição e recuperar credenciais do usuário. Retornar código HTTP adequado para as possíveis possibilidades de erro (ex. requisição sem JWT, com JWT inválido, ou com JWT de usuário inexistente).

**Reavaliando as rotas anteriores**

Agora que temos o conceito de usuários, é importante que eles estejam associados a suas ações na API. Então vamos realizar as seguintes configurações/mudanças no código:

1. Apenas usuários cadastrados podem dar likes nas disciplinas, e é preciso associar cada like ao respectivo usuário. Pense assim: se a gente for desenvolver o frontend é preciso ter essa informação pra quando for mostrar a disciplina o frontend saber se o símbolo de loke fica marcado ou não para o usuário logado.

2. Apenas usuarios cadastrados podem comentar as disciplinas e os comentários devem ficar associados aos usuários que os escreverm. Apenas o dono de um comentário pode apagar o comentário.

3. Apenas usuários cadastrados podem dar notas às disciplinas, mas não é preciso associar cada nota ao respectivo usuário.

Para que estas ações acima sejam implementadas você vai precisar lidar com os tokens JWT. Para automatizar a verificação do token você pode criar um filtro para JWT e configurar na sua aplicação (momento que você também configura as rotas privadas de acordo com o que discutimos acima).

As demais funcionalidades de sua API continuam como já estavam.

Seguem algumas dicas:
Seguem algumas dicas:

* Use o padrão DAO para acesso às bases de dados;
* Siga boas práticas de design, buscando desacoplamento utilize corretamente controladores, serviços e repositórios;
* Organize suas classes em packages com nomes significativos (xx.services, xx.controllers, xx.repositories, xx.entities, etc. - pode usar nomes em portugues também, mas mantenha a coerência, ou tudo em portugues ou tudo em ingles);
* Para ordenação aprenda a definir um novo método no repositório de disciplina seguindo o padrão de nomes do método. Mais dicas [aqui](https://www.baeldung.com/spring-data-sorting).
* Use o que aprendemos sobre relacionamentos JPA para relacionar entidades.

Execute a sua aplicação no terminal, dentro do diretório raiz do seu projeto com o seguinte comando:
$ ./mvnw spring-boot:run

Use Curl ou Postman para testar sua API.

**Não faça tudo de uma vez**. Desenvolva uma funcionalidade, teste, vá para a próxima…