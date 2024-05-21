# Spring Security 6 + JSON Web Token

[![General badge](https://img.shields.io/badge/Java-17-orange.svg)](https://shields.io/)
[![General badge](https://img.shields.io/badge/Spring_Boot-3-green.svg)](https://shields.io/)
[![General badge](https://img.shields.io/badge/Spring_Security-6-green.svg)](https://shields.io/)
[![General badge](https://img.shields.io/badge/JSON_Web_Token-purple.svg)](https://shields.io/)

### Sobre o repositório:
<div style="color: #32E2BD; font-family: system-ui; text-align: justify;">
    Este repositório é resultado do estudo sobre a implementação do processo de autenticação e autorização de
    usuários em microsserviços Java. Este projeto-exemplo apoia-se no framework Spring Security (versão 6)
    e no uso de bibliotecas especializadas em gerar e descriptografar tokens JWT.
</div>


### Sobre o código:

#### 1. Principais dependências:

<div style="font-family: system-ui; text-align: justify;">
    Além do Spring Web, Spring Security, Spring JPA e H2, foram utilizadas as seguintes dependências para manipular
    JWTs:
    
<ul>
    <li>jjwt-api</li>
    <li>jjwt-impl</li>
    <li>jjwt-jackson</li>
 </ul>
</div>

Todas elas estão na versão 0.10.7 e pertencem ao groupId: io.jsonwebtoken.

#### 2. Principais Classes:

2.1. A classe JWTUtils:

<div style="font-family: system-ui; text-align: justify;">
    É uma classe Java pública, anotada com <span style="color: gold;">@Component</span> do pacote
    <span style="color: gray;">org.springframework.stereotype</span>, responsável por fazer uma ponte entre
    a aplicação e as libs do io.jsonwebtoken. Seus principais métodos são generateToken, extractUsername e 
    validateToken responsáveis por, respectivamente, gerar um token, obter o username a partir de um token e
    validar um token.
</div>

2.2. A classe JWTFilter:

<div style="font-family: system-ui; text-align: justify;">
    Filtro de requisições. Obtém token do cabeçalho da requisição, verifica se o token é válido e adiciona o
    usuário no contexto de autenticação do Spring Security.
</div>

2.3. A classe SecurityConfig:

<div style="font-family: system-ui; text-align: justify;">
    Define as authorities necessárias para o usuário acessar cada endpoint, declara manipuladores de exceções,
    e especifica o provedor de autenticação e o encriptador de senhas.
</div>

#### 3. Testando a Aplicação:

<div style="font-family: system-ui; text-align: justify;">
    Os três principais endpoints desta API são:

* <span style="color: red">/hello/user</span> (Acessível apenas aos usuários autenticados)
* <span style="color: red">/hello/admin</span> (Acessível apenas a administradores)
* <span style="color: red">/hello/all</span> (Endpoint público)

Para fazer login, acesse o path /login e passe, no corpo da requisição, o seguinte JSON:

> {
    "username" : <_username>,
    "password" : <_password>
}

E, após enviar a requisição com as credenciais corretas, será obtido um token como no exemplo abaixo:

> eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2FvIiwiaWF0IjoxNzE0MTQ5NDM1LCJleHAiOjE3MTQxNDk3MzUsInJvbGUiOlsiVVNFUiJdfQ.hOQo-ZBdGHvAWT9ZhHa6ZByXbENRxjalVZr-HL7hSEE

Para realizar os testes, use as seguintes credenciais:

| username | password | roles        |
|----------|----------|--------------|
| joao     | joao     | USER         |
| maria    | maria    | USER, ADMIN  |


</div>