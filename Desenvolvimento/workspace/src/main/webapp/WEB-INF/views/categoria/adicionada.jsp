<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria Adicionada</title>
</head>
<body>

	<h2>Categorira ${categoria.descricao} adicionada.</h2>
	<br />

	Adicionar Variavel
	<form action="/faddvm/variavel/adicionaVariavel" method="post">
		nome: <input type="text" name="nome"> <br />
		Tipo: <input type="text" name="tipo"> <br />
		<input type="hidden" name="categoria_id" value="${categoria.id}">
		<input type="submit" value="Adicionar Variavel">
	</form>

</body>
</html>