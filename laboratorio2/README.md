##Laboratório - roteiro 2
Objetivos:
Aprender a escrever APIs com dados persistentes usando um esquema de dados relacional e relacionamentos JPA
Tecnologias envolvidas:
ORM - Mapeamento objeto relacional (Hibernate é a impementação por trás do que usaremos)
JPA - interface unificada para facilitar mapeamento de objetos para registros de tabelas e definir relações entre entidades
Lembrete: use o spring initizlizr para criar seu projeto spring. Desas vez marque as dependências “Spring Web Starter”, “H2 Database” e “Spring Data JPA” na configuração do seu projeto. Pode também usar Lombok para os DTOs.

Faça o unzip do arquivo zip criado para o seu workspace. Na sua IDE de preferência (eclipse, IntelliJ, etc.) importe o projeto criado como um projeto maven já existente. Agora você já pode desenvolver sua aplicação. Lembre de organizar tudo em pacotes específicos.

Neste segundo lab o design da API REST a ser desenvolvida também estará encaminhado, exceto pelos métodos HTTP usados e códigos de resposta. Continuaremos o desenvolvimento do primeiro lab no contexto de disciplinas. Mas agora vamos adicionar persistência, vamos iniciar todas as disciplinas de uma vez. Relembrando, por enquanto, no contexto da nossa API, uma Disciplina é uma classe que tem os seguintes atributos: id:long, nome:String, nota:double, e likes:int. Dependendo do relacionamento que você definir entre Disciplina e Comentário, pode ainda haver uma coleção de comentários em disciplina. O mesmo vale para nota.

O objetivo desta API é permitir que alunos comentem sobre as disciplinas e deem likes nas disciplinas do curso de Sistemas de Informação.

Temos um arquivo JSON já com os nomes de todas as disciplinas que devem ser criadas. A ideia é programar sua API para povoar o banco de dados com todas as disciplinas já existentes neste arquivo. Neste documento encontra-se uma discussão sobre como ler dados de um json e adicionar ao banco de dados usando spring boot (você vai ter que entender e implementar o seu próprio). Lembre que a própria API deve se encarregar de gerar os identificadores únicos das disciplinas no banco (@GeneratedValues). Com isso, não precisaremos mais de uma rota na API para adicionar disciplinas. Outro lembrete: essa atividade envolve já o uso do banco, então você deve criar o repositório de Disciplinas, marcar as classes que vão para o banco como @Entity, e já deve ter configurado o banco em application.properties. (para testar você pode usar a rota GET /v1/api/disciplinas que retornará todas as disciplinas inseridas no sistema).

Use Spring Boot e java para desenvolver a seguinte API:

** /api/disciplinas** Retorna um JSON (com campos id, nome) com todas as disciplinas inseridas no sistema e (ver https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

** /api/disciplinas/{id}** Retorna um JSON que representa a disciplina completa (id, nome, nota, likes e comentarios) cujo identificador único é id e código . Ou não retorna JSON e caso o id passado não tenha sido encontrado.

** /api/disciplinas/likes/{id}** Incrementa em um o número de likes da disciplina cujo identificador é id. Retorna a disciplina que foi atualizada (incluindo o id, nome e likes) e . Ou não retorna JSON e caso o id passado não tenha sido encontrado.

** /api/disciplinas/nota/{id}** Atualiza a nota da disciplina de identificador id no sistema. No corpo da requisição HTTP deve estar um JSON com a nova nota atribuída à disciplina. A nova nota da disciplina é a média aritmética da nota anterior da disciplina e da nova nota passada nesta chamada. Se for a primeira nota sendo adicionada então esta nota é a que vai valer para a disciplina. Todas as notas atribuídas à disciplina devem ser guardadas. Retorna a disciplina que foi atualizada (incluindo o id, nome e nota) e . Ou não retorna JSON e caso o id passado não tenha sido encontrado.

** /api/disciplinas/comentarios/{id}** Insere um novo comentário na disciplina de identificador id. No corpo da requisição HTTP deve estar um JSON com o novo comentário (chave "comentario") a ser adicionado na disciplina a ser atualizada no sistema. Retorna a disciplina que foi atualizada (incluindo o id, nome e os comentarios atualizados) e . Ou não retorna JSON e caso o id passado não tenha sido encontrado.

** /api/disciplinas/ranking/notas** Retorna todas as disciplinas inseridas no sistema ordenadas pela nota (da maior para a menor) e .

** /api/disciplinas/ranking/likes** Retorna todas as disciplinas inseridas no sistema ordenadas pelo número de likes (da que tem mais likes para a que tem menos likes) e .

Seguem algumas dicas:

Use o padrão DAO para acesso às bases de dados;
Siga boas práticas de design, buscando desacoplamento utilize corretamente controladores, serviços e repositórios;
Organize suas classes em packages com nomes significativos (xx.services, xx.controllers, xx.repositories, xx.entities, etc. - pode usar nomes em portugues também, mas mantenha a coerência, ou tudo em portugues ou tudo em ingles);
Para ordenação aprenda a definir um novo método no repositório de disciplina seguindo o padrão de nomes do método. Mais dicas aqui.
Use o que aprendemos sobre relacionamentos JPA para relacionar entidades.
Execute a sua aplicação no termina, dentro do diretório raiz do seu projeto com o seguinte comando: $ ./mvnw spring-boot:run

Use Curl ou Postman para testar sua API.


https://github.com/raquelvl/psoft