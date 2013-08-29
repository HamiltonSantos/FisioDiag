<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Pacientes</h1>
	<table class="table">
		<tr>
			<th>Nome</th>
			<th>Data de Nascimento</th>
			<th>CPF</th>
			<th>Sexo</th>
			<th>Numero de Registro</th>
			<th>Ações</th>
		</tr>
		<c:forEach items="${pacientes}" var="paciente">
			<tr>
				<td>${paciente.nome}</td>
				<td>${paciente.dataNascimento}</td>
				<td>${paciente.cpf}</td>
				<td>${paciente.sexo}</td>
				<td>${paciente.numRegistro}</td>
				<td><a>Atender</a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="/faddvm/pacientes/novo" class="btn btn-primary">Novo</a>
</body>
</html>
