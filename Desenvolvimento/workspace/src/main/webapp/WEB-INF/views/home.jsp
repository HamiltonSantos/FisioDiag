<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pacientes em VM</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Ocorrência</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pacientesVM}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td>${historico.faixa.descricao}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pacientes em Desmame</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pacientesDesmame}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pacientes Extubados</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pacientesExtubados}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pacientes Reintubados</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pacientesReintubados}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Pacientes na UTI</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Ocorrência</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pacientesUTI}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td>${historico.faixa.descricao}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Ultimos atendimentos realizados na UTI</h3>
		</div>
		<table id="tabela" class="table table-hover">
			<thead>
				<tr>
					<th>Nome Paciente</th>
					<th>Data</th>
					<th>Fisioterapeuta</th>
					<th>Operação</th>
					<th>Valor</th>
					<th>Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${atendimentosRealizados}" begin="0" end="4" var="historico">
					<tr>
						<td>${historico.paciente.nome}</td>
						<td>${historico.dataHistorico}</td>
						<td>${historico.fisioterapeuta.nome}</td>
						<td>${historico.faixa.variavel.descricao}</td>
						<td>${historico.faixa.descricao}</td>
						<td><a href="/faddvm/atendimento/${historico.paciente.id}">Atender</a> <a
							href="/faddvm/paciente/${historico.paciente.id}"
						>Mostrar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="dataTables_scroll"></div>
	</div>
</body>
</html>
