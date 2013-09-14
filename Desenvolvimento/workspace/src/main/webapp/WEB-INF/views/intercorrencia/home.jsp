<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Intercorrencia</title>
</head>
<body>
	<h1>Intercorrencias</h1>

	<table id ="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
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

	<a href="/faddvm/categoria/intercorrencia/nova" class="btn btn-primary">Nova</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
