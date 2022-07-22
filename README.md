#SWAPIConsumer

#Cadastro de usuário  
Para criar um usuário, faça o login usuando o usuário master
<br/>
<strong>url:</strong>http://localhost:8080/login
<br/>
<strong>Método:</strong> POST
<br/>
<strong>JSON:</strong>
<br/>
{
	"login":"master",
	"password":"Ldm##2022"
}

Receberá como retorno um token que deve adicionado na autorização da requisição para criar um usuário.

#Criação de usuário
url: http://localhost:8080/swapi/users
<br/>
<strong>url:</strong>http://localhost:8080/users
<br/>
<strong>Método:</strong> POST
<br/>
<strong>JSON:</strong>
<br/>
{
	"firstName": "Nome",
	"lastName": "Sobrenome",
	"login": "nome_do_login",
	"password": "123456",
	"isActive": true
}

#Alteração de senha do usuário
<br/>
<strong>url:</strong>http://localhost:8080/users
<br/>
<strong>Método:</strong> PATCH
<br/>
<strong>JSON:</strong>
<br/>
{
	"userId": id_usuario_cadatrado,
	"password": "nova_senha",
}
