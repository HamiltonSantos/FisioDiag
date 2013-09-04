<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Ocorrencia</title>
</head>
<body>
	<h1>Ocorrências</h1>

	<table class="table">
		<tr>
			<th>Descricao</th>
			<th>Peso</th>
		</tr>
		<c:forEach items="${ocorrencias}" var="ocorrencia">
			<tr>
				<td>${ocorrencia.descricao}</td>
				<td>${ocorrencia.peso}</td>
			</tr>
		</c:forEach>

	</table>

	<a href="/faddvm/categoria/ocorrencia/nova" class="btn btn-primary">Nova</a>
</body>
</html>
