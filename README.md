#swapi

#Cadastro de usuário  
Para criar um usuário, faça o login usuando o usuário master
url: http://localhost:8080/login
Método: POST
Método: POST
JSON
{
	"login":"master",
	"password":"Ldm##2022"
}

Receberá como retorno um token que deve adicionado na autorização da requisição para criar um usuário.

#Criação de usuário
url: http://localhost:8080/swapi/users
Método: POST
JSON
{
	"id": "",
	"firstName": "Nome",
	"lastName": "Sobrenome",
	"login": "nome_do_login",
	"password": "123456",
	"isActive": true
}
