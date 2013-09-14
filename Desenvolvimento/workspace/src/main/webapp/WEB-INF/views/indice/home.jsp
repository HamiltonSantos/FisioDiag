<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Indice</title>
</head>
<body>
	<h1>Indices</h1>

	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>ValorMinimo</th>
				<th>ValorMaximo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${indices}" var="indice">
				<tr>
					<td>${indice.descricao}</td>
					<td>${indice.valorMin}</td>
					<td>${indice.valorMax}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/faddvm/categoria/indice/novo" class="btn btn-primary">Novo</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
