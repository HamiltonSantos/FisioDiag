<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Fisioterapeutas</h1>
	<table id="tabela" class="datatable table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Acoes</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fisioterapeutas}" var="fisioterapeuta">
				<tr>
					<td>${fisioterapeuta.nome}</td>
					<td>${fisioterapeuta.login}</td>
					<td>*****</td>
					<td><a href="/faddvm/fisioterapeuta/${fisioterapeuta.id}">Mostrar</a> <a
						href="/faddvm/fisioterapeuta/${fisioterapeuta.id}/editar"
					>Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_scroll"></div>

	<a href="/faddvm/fisioterapeuta/novo" class="btn btn-primary">Novo</a>

</body>
</html>
