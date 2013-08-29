<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Categoria</title>
</head>
<body>
	<h3>Adicionar Categoria</h3>
	<form action="adicionaCategoria" method="post">
		<div class="form-group">
			<label for="descricao">Descrição:</label>
    		<input type="text" class="form-control" id="descricao" placeholder="Descrição da Categoria" name="descricao">
    	</div>
		<button type="submit" class="btn btn-primary">Adicionar</button>
	</form>
</body>
</html>