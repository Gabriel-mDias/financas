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
    - Documentação da API e seus endpoints.

# Módulos

Até o momento, foram levantados 4 módulos. 2 deles, são como uma "SDK" de desenvolvimento para projetos pessoais.

 - Utils (Módulo reutilizável)
    - Um módulo de classes e recursos utilitários e compartilhado entre os demais módulos;
 - Exception (Módulo reutilizável)
    - Este módulo tem como objetivo único a tratativa relacionada a exceções, com as definições das mesmas e a definição do GlobalExceptionHandler;
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
respectivos DTOs e encapsulamentos básicos.

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

Neste módulo estão as Controllers, Services e Repositories necessárias para a implementação da regra de negócio da nossa API.
