# Financas

Um projeto desenvolvido como o objetivo de ser uma base para futuras aplicações. 
A regra de negócio é bem simplificada: temos uma coleção de usuários que poderão cadastrar entradas e saídas de dinheiro.

# Tecnologias

Esta é uma aplicação Java Spring com as seguintes tecnologias utilizadas:

 - Java 21
 - Spring Boot 3.5.4
 - Spring Data JPA
 - PostgresSQL
    - Essa base de dados foi selecionada por ser simples e com bastantes recursos dos quais tenho domínio.
 - Liquibase
    - Ferramenta para gerir as migrations do banco de dados.
 - Lombok
    - Dependência para reduzir o volume de código escrito. A utilização é objetiva.
 - Swagger
    - Documentação da API e os seus endpoints.

# Módulos

Até o momento, foram levantados 4 módulos. 2 deles, são como uma "SDK" de desenvolvimento para projetos pessoais.

 - Utils (Módulo reutilizável)
    - Um módulo de classes e recursos utilitários e compartilhado entre os demais módulos;
 - Exception (Módulo reutilizável)
    - Este módulo visa tratar as exceções da nossa aplicação, definido os tipos das mesmas e a definição base do GlobalExceptionHandler;
 - Model (Módulo de regra de negócio)
    - Módulo que contém as entidades JPAs e seus DTOs;
 - Core (Módulo de regra de negócio)
    - Módulo que contem a implementação da regra de negócio de fato
    
## Utils

Até o momento, este módulo contém utilitários referente ao modelMapper e a validação de objetos em geral.

## Exception

Módulo que define as exceções customizadas que utilizaremos na aplicação, tendo até o presente momento a BusinessException.
Também está definido o GlobalExceptionHandler, com os retornos tratados das exceptions disparadas pela aplicação.


## Model

No módulo model estão as entidades do sistema. Não há regras de negócio nesta camada, apenas as entidades e seus 
respetivos DTOs e encapsulamentos básicos.

As colunas das tabelas são mapeadas para as entidades por annotations do JPA. 

Já as nomeclaturas das colunas seguem os seguintes padrões:

 - ID_[Nome da Entidade]: Para a chave primária da entidade.
 - NM_[Nome da Entidade]: Para os campos que representam o nome da entidade.
 - DS_[Nome da Entidade]: Para os campos que representam a descrição da entidade.
 - DT_...: Para os campos que representam datas.
 - CD_...: Para os campos que representam códigos.
 - FL_...: Para os campos que representam flags.
 - NR_...: Para os campos que representam números.
 
 
## Core

Neste módulo estão as Controllers, Services, Repositories e entre outras soluções para 
a implementação da regra de negócio da nossa API.

# Como executar o projeto localmente

Para rodar este projeto na sua máquina, você precisará ter o **Docker** (para o banco de dados) e o **Java 21** instalados.

### 1. Subindo o Banco de Dados (PostgreSQL)

O projeto possui um arquivo `docker-compose.yml` pré-configurado para subir o banco de dados necessário para a aplicação.

Abra o seu terminal, navegue até a pasta onde o `docker-compose.yml` está localizado e execute o comando abaixo:

```bash
cd env/local
docker-compose up -d
```

Este comando irá baixar a imagem do PostgreSQL e iniciar um container chamado `gems-postgresserver` rodando na porta `5432` em segundo plano. O banco `financas_db` será criado automaticamente.

Para parar o banco de dados posteriormente, você pode executar:
```bash
docker-compose down
```

### 2. Rodando a Aplicação (Spring Boot)

Após garantir que o banco de dados está a rodar, você pode iniciar a aplicação Spring Boot. 
Como se trata de um projeto estruturado com múltiplos módulos, navegue até a raiz do projeto e execute através da sua IDE ou utilizando o wrapper do Maven:

```bash
./mvnw spring-boot:run -pl finance-core
```

Ao iniciar a aplicação, o **Liquibase** se encarregará de criar as tabelas e a estrutura do banco automaticamente no schema `public`.

Após a inicialização completa, você poderá acessar a documentação dos endpoints via **Swagger** através da URL (geralmente):
`http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/swagger-ui/index.html` (dependendo das configurações do `application.yml`).

# Observações

Como não há um interesse real em tornar este projeto em um produto de fato, as informações de senhas e outras sensíveis estão anexadas neste repositório, sendo isso claramente uma péssima prática, mas não é o meu interesse no momento, lidar neste nível de segurança para um projeto local.
