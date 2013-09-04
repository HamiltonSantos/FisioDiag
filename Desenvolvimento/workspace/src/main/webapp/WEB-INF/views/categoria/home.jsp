<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Categorias</h1>
	<table class="table">
		<tr>
			<th>Descricao</th>
			<th>Ação</th>
		</tr>
		<c:forEach items="${categorias}" var="categoria">
			<tr>
				<td>${categoria.descricao}</td>
				<td><a href="/faddvm/categoria/${categoria.id}">Mostrar</a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="/faddvm/categoria/nova" class="btn btn-primary">Novo</a>
</body>
</html>
