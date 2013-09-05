<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Indice</title>
</head>
<body>
	<h1>Indices</h1>

	<table class="table">
		<tr>
			<th>Descricao</th>
			<th>ValorMinimo</th>
			<th>ValorMaximo</th>
		</tr>
		<c:forEach items="${indices}" var="indice">
			<tr>
				<td>${indice.descricao}</td>
				<td>${indice.valorMin}</td>
				<td>${indice.valorMax}</td>
			</tr>
		</c:forEach>

	</table>

	<a href="/faddvm/categoria/indice/novo" class="btn btn-primary">Novo</a>
</body>
</html>
