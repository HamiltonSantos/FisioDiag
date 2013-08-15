<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adiciona Fisioterapeuta Simples</title>
</head>
<body>

	<h3>Adicionar Fisioterapeuta</h3>
	<form action="adicionaFisioterapeuta" method="post">
		Nome: <input type="text" name="nome"> <br />
		Login: <input type="text" name="login"> <br />
		Senha: <input type="text" name="senha"> <br />
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>