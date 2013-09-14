<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Categorias</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>Ação</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorias}" var="categoria">
				<tr>
					<td>${categoria.descricao}</td>
					<td><a href="/faddvm/categoria/${categoria.id}">Mostrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/faddvm/categoria/nova" class="btn btn-primary">Novo</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
