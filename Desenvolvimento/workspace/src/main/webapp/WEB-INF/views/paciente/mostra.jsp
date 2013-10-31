<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Paciente</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Informações Cadastrais</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Nome</dt>
						<dd>${paciente.nome}</dd>
						<dt>CPF</dt>
						<dd>${paciente.cpf}</dd>
						<dt>Número de Registro</dt>
						<dd>${paciente.numRegistro}</dd>
					</dl>
				</div>
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Data de Nascimento</dt>
						<dd>${paciente.dataNascimento}</dd>
						<dt>Sexo</dt>
						<dd>${paciente.sexo}</dd>
					</dl>
				</div>
			</div>

		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Informações</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Data entrada na UTI</dt>
						<dd>${entradaUTI}</dd>
						<dt>Data inicio VM</dt>
						<dd>${inicioVM}</dd>
						<dt>Data inicio Desmame</dt>
						<dd>${inicioDesmame}</dd>
						<dt>Pontos</dt>
						<dd>${paciente.pontos}</dd>

					</dl>
				</div>
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Data Extubação</dt>
						<dd>${dataExtubacao}</dd>
						<dt>Data Reintubação</dt>
						<dd>${dataReintubacao}</dd>
						<dt>Data Saida UTI</dt>
						<dd>${dataSaidaUTI}</dd>
						<dt>Indicação</dt>
						<dd>${indicacaoPaciente.descricao}</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Fatores que influenciaram na Indicação</h3>
		</div>
		<div class="panel-body">
			<table id="tabela" class="table table-hover">
				<thead>
					<tr>
						<th>Operação</th>
						<th>Valor</th>
						<th>Data</th>
						<th>Fisioterapeuta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${historicoIndicacao}" var="historico">
						<tr>
							<td>${historico.faixa.descricao}</td>
							<td>${historico.valor}</td>
							<td>${historico.dataHistorico}</td>
							<td>${historico.fisioterapeuta.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Histórico</h3>
		</div>
		<div class="panel-body">
			<table id="tabela" class="table table-hover datatable">
				<thead>
					<tr>
						<th>Operação</th>
						<th>Valor</th>
						<th>Data</th>
						<th>Fisioterapeuta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${paciente.historico}" var="historico">
						<tr>
							<td>${historico.faixa.descricao}</td>
							<td>${historico.valor}</td>
							<td>${historico.dataHistorico}</td>
							<td>${historico.fisioterapeuta.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<a href="/faddvm/atendimento/${paciente.id}" class="btn btn-primary">Atender</a>
</body>
</html>