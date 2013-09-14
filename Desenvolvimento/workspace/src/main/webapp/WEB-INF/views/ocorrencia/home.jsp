<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Ocorrencia</title>
</head>
<body>
	<h1>Ocorrências</h1>

	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>Peso</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ocorrencias}" var="ocorrencia">
				<tr>
					<td>${ocorrencia.descricao}</td>
					<td>${ocorrencia.peso}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/faddvm/categoria/ocorrencia/nova" class="btn btn-primary">Nova</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
