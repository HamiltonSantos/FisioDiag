<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Intercorrencia</title>
</head>
<body>
	<h1>Intercorrencias</h1>

	<table class="table">
		<tr>
			<th>Descricao</th>
			<th>Peso</th>
		</tr>
		<c:forEach items="${intercorrencias}" var="intercorrencia">
			<tr>
				<td>${intercorrencia.descricao}</td>
				<td>${intercorrencia.peso}</td>
			</tr>
		</c:forEach>

	</table>

	<a href="/faddvm/categoria/intercorrencia/nova" class="btn btn-primary">Nova</a>
</body>
</html>
