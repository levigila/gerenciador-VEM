# Projeto Urbana-PE

## Descrição
O **Urbana-PE** é uma aplicação desenvolvida para facilitar o gerenciamento de usuários e cartões de transporte na cidade do Recife. O sistema foi projetado para ser uma mão na roda para os administradores que precisam gerenciar milhares de cartões, evitando fraudes e uso indevido, como o uso excessivo de cartões ou recebimento indevido de valores de verba pública por meio de cartões passe-livre. 

## Objetivo
A ideia principal do projeto é garantir o fácil acesso aos cartões de forma rápida e que todos os cartões sejam utilizados corretamente, reduzindo as irregularidades e proporcionando um controle mais eficaz sobre os benefícios concedidos.

## Funcionalidades
- **Gerenciamento de Usuários**:
  - Adicionar novos usuários ao sistema.
  - Editar informações dos usuários existentes.
  - Remover usuários do sistema.

- **Gerenciamento de Cartões**:
  - Adicionar cartões para cada usuário, garantindo que cada cartão esteja associado a um único usuário.
  - Editar as informações dos cartões, como status e tipo (COMUM, ESTUDANTE, TRABALHADOR).
  - Ativar ou desativar cartões conforme necessário.
  - Remover cartões que não estão mais em uso.

- **Painel Administrativo**:
  - Interface intuitiva para visualização de todos os usuários e seus respectivos cartões.
    
- **Futuras Implementações**:
  - Filtros para busca rápida de usuários e cartões, facilitando a gestão.
  - Alertas e notificações para possíveis irregularidades ou usos indevidos dos cartões.

## Tecnologias Utilizadas
- **Backend**: Spring Boot 3.0 , Java
- **Frontend**: Angular 17
- **Banco de Dados Relacional**: PostgreSQL
- **Gerenciamento de Migrações**: Liquibase
- **Responsividade**: CSS
- **Processo de Build**: Maven
- **Persistência de dados**: JPA

## Como Iniciar o Projeto

### Backend

1. Navegue até a pasta do backend:
   ```bash
   cd urbana
2. Execute a aplicação
   ```bash
   ./mvnw spring-boot:run
### Frontend
1. Navegue até a pasta do frontend:
    ```bash
    cd frontend
2. Instale as dependências:
    ```bash
       npm install
3. Execute a aplicação:
   ```bash
   ng serve

### Acesso
Após iniciar o backend e frontend, você pode acessar a aplicação em:

Frontend: http://localhost:4200
Backend: http://localhost:8080
Contribuição
Contribuições são bem-vindas! Se você deseja contribuir com o projeto, sinta-se à vontade para abrir um pull request ou criar uma issue para discutir melhorias e sugestões.

## Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.


