Feature: Gerenciamento de Livros

  Scenario: Cadastrar um novo livro com sucesso
    Given que tenho um livro válido para cadastro
    When eu envio uma requisição POST para "/api/livros"
    Then devo receber o status 201
    And devo ver o livro criado no corpo da resposta

  Scenario: Listar todos os livros
    When eu envio uma requisição GET para "/api/livros"
    Then devo receber o status 200
    And devo ver uma lista de livros em JSON