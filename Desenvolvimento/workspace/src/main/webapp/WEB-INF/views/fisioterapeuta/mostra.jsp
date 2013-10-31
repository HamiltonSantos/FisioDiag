<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fisioterapeuta</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Informações</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Nome</dt>
						<dd>${fisioterapeuta.nome}</dd>
						<dt>Login</dt>
						<dd>${fisioterapeuta.login}</dd>
					</dl>
				</div>
				<div class="col-md-6">
					<dl class="dl-horizontal">
						<dt>Total Atendimentos</dt>
						<dd>${numAtendimentos}</dd>
						<dt>Total Intubacoes</dt>
						<dd>${numIntubacoes}</dd>
						<dt>Total Desmames</dt>
						<dd>${numDesmames}</dd>
						<dt>Total Extubacoes</dt>
						<dd>${numExtubacoes}</dd>
						<dt>Total Reintubacoes</dt>
						<dd>${numReintubacoes}</dd>
					</dl>
				</div>


			</div>

		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Ultimos Atendimentos</h3>
		</div>

		<div class="panel-body">
			<table id="tabela" class="table table-hover datatable">
				<thead>
					<tr>
						<th>Operação</th>
						<th>Data</th>
						<th>Paciente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${atendimentos}" var="historico">
						<tr>
							<td>${historico.faixa.variavel.descricao}</td>
							<td>${historico.dataHistorico}</td>
							<td>${historico.paciente.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>