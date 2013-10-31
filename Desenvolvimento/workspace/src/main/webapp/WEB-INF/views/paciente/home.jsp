<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Pacientes</h1>
	<table id="tabela" class="datatable table table-striped table-bordered">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Data de Nascimento</th>
				<th>CPF</th>
				<th>Sexo</th>
				<th>Número de Registro</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pacientes}" var="paciente">
				<tr>
					<td>${paciente.nome}</td>
					<td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${paciente.dataNascimento}" /></td>
					<td>${paciente.cpf}</td>
					<td>${paciente.sexo}</td>
					<td>${paciente.numRegistro}</td>
					<td><a href="/faddvm/atendimento/${paciente.id}">Atender</a> <a href="/faddvm/paciente/${paciente.id}">Mostrar</a>
						<a href="/faddvm/paciente/${paciente.id}/editar">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_scroll"></div>
	<a href="/faddvm/paciente/novo" class="btn btn-primary">Novo</a>
</body>
</html>
