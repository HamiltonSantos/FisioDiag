<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Pacientes</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Data de Nascimento</th>
				<th>CPF</th>
				<th>Sexo</th>
				<th>Numero de Registro</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pacientes}" var="paciente">
				<tr>
					<td>${paciente.nome}</td>
					<td>${paciente.dataNascimento}</td>
					<td>${paciente.cpf}</td>
					<td>${paciente.sexo}</td>
					<td>${paciente.numRegistro}</td>
					<td>
					<a href="/faddvm/atendimento/${paciente.id}">Atender</a>
					<a href="/faddvm/paciente/${paciente.id}">Mostra</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="/faddvm/paciente/novo" class="btn btn-primary">Novo</a>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable();
		});
	</script>
</body>
</html>
