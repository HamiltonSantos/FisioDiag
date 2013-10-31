<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Intercorrência</title>
</head>
<body>
	<h1>Intercorrências</h1>

	<table id="tabela" class="datatable table table-striped table-bordered">
		<thead>
			<tr>
				<th>Descrição</th>
				<th>Peso</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${intercorrencias}" var="intercorrencia">
				<tr>
					<td>${intercorrencia.descricao}</td>
					<td>${intercorrencia.peso}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="dataTables_scroll"></div>

	<a href="/faddvm/intercorrencia/nova" class="btn btn-primary">Nova</a>

</body>
</html>
