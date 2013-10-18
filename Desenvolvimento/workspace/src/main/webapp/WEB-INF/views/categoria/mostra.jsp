<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>
	<h1>Variaveis de ${categoria.descricao}</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>Tipo</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categoria.variaveis}" var="variavel">
				<tr>
					<td>${ variavel.descricao }</td>
					<td>${ variavel.tipo } </td>
					<td><a href="/faddvm/variavel/${variavel.id}">Mostrar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_scroll"></div>
	
	<a href="/faddvm/variavel/nova/${categoria.id}" class="btn btn-primary">Nova Variavel</a>

<%-- 	<div class="well">
		<h4>Adicionar Variável</h4>
		<form:form action="/faddvm/categoria/${categoria.id}/adicionaVariavel" commandName="variavel"
			cssClass="form-horizontal"
		>
			<div class="form-group">
				<label class="col-lg-2 control-label" for="nome">Nome:</label>
				<div class="col-lg-10">
					<form:input path="descricao" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label" for="tipo">Tipo Variavel:</label>
				<div class="col-lg-10">
					<form:radiobutton path="tipo" value="O" label="Opção" />
					<form:radiobutton path="tipo" value="R" label="Range" />
				</div>
			</div>
			<form:hidden path="status" />
			<input type="submit" class="btn" value="Adicionar Variavel" class="btn">
		</form:form>
	</div> --%>

	<%-- <div>
		<c:forEach items="${categoria.variaveis}" var="variavel">
			<div class="well">
				<h4>${variavel.descricao}</h4>
				<div class="well">
					<table class="table table-condensed">
						<tr>
							<th>Descricao</th>
							<th>Peso</th>
						</tr>
						<c:forEach items="${variavel.faixaValores}" var="faixa">
							<tr>
								<td>${faixa.descricao}</td>
								<td>${faixa.peso}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="well">
					<h4>Adicionar nova Faixa</h4>
					<form action="/faddvm/categoria/${categoria.id}/${variavel.id}/adicionaFaixa" method="post" class="form-horizontal">
						<c:if test="${variavel.tipo eq 79}">
							<div class="form-group">
								<label class="col-lg-3 control-label" for="descricao">Descrição:</label>
								<div class="col-lg-9">
									<input type="text" name="descricao" class="form-control" id="descricao">
								</div>
							</div>
						</c:if>
						<c:if test="${variavel.tipo eq 82}">
							<div class="form-group">
								<label class="col-lg-3 control-label" for="valorMin">Valor Mínimo:</label>
								<div class="col-lg-9">
									<input type="text" name="valorMin" id="valorMin" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label" for="valorMax">Valor Máximo:</label>
								<div class="col-lg-9">
									<input type="text" name="valorMax" id="valorMax" class="form-control">
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label class="col-lg-3 control-label" for="peso">Peso:</label>
							<div class="col-lg-9">
								<input type="text" name="peso" id="peso" class="form-control">
							</div>
						</div>
						<input type="submit" value="Adicionar Faixa" class="btn">
					</form>
				</div>
			</div>
		</c:forEach> 
	</div> --%>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#tabela')
									.dataTable(
											{
												"oLanguage" : {
													"sProcessing" : "Aguarde enquanto os dados são carregados ...",
													"sLengthMenu" : "Mostrar _MENU_ registros",
													"sZeroRecords" : "Nenhum registro correspondente ao criterio encontrado",
													"sInfoEmtpy" : "Exibindo 0 a 0 de 0 registros",
													"sInfo" : "Exibindo de _START_ a _END_ de _TOTAL_ registros",
													"sInfoFiltered" : "",
													"sSearch" : "Procurar",
													"oPaginate" : {
														"sFirst" : "Primeiro",
														"sPrevious" : "Anterior",
														"sNext" : "Próximo",
														"sLast" : "Último"
													}
												}
											});
						});
	</script>

</body>
</html>