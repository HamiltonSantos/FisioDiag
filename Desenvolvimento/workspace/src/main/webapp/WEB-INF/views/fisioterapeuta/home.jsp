<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Fisioterapeutas</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Senha</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fisioterapeutas}" var="fisioterapeuta">
				<tr>
					<td>${fisioterapeuta.nome}</td>
					<td>${fisioterapeuta.login}</td>
					<td>*****</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/faddvm/fisioterapeuta/novo" class="btn btn-primary">Novo</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
