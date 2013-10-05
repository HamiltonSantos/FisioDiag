<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class="well">
		<h4>Adicionar Variável</h4>
		<form:form action="/faddvm/categoria/${categoria.id}/adicionaVariavel"
			commandName="variavel" cssClass="form-horizontal">
			<div class="form-group">
				<label class="col-lg-2 control-label" for="nome">Nome:</label>
				<div class="col-lg-10">
					<form:input path="descricao" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-2 control-label" for="tipo">Tipo
					Variavel:</label>
				<div class="col-lg-10">
					<form:radiobutton path="tipo" value="O" label="Opção" />
					<form:radiobutton path="tipo" value="R" label="Range" />
				</div>
			</div>
			<input type="submit" value="Adicionar Variavel"
				class="btn btn-primary">
		</form:form>
	</div>

	<div>
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
					<form
						action="/faddvm/categoria/${categoria.id}/${variavel.id}/adicionaFaixa"
						method="post" class="form-horizontal">
						<c:if test="${variavel.tipo eq 79}">
							<div class="form-group">
								<label class="col-lg-3 control-label" for="descricao">Descrição:</label>
								<div class="col-lg-9">
									<input type="text" name="descricao" class="form-control"
										id="descricao">
								</div>
							</div>
						</c:if>
						<c:if test="${variavel.tipo eq 82}">
							<div class="form-group">
								<label class="col-lg-3 control-label" for="valorMin">Valor
									Mínimo:</label>
								<div class="col-lg-9">
									<input type="text" name="valorMin" id="valorMin"
										class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label" for="valorMax">Valor
									Máximo:</label>
								<div class="col-lg-9">
									<input type="text" name="valorMax" id="valorMax"
										class="form-control">
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label class="col-lg-3 control-label" for="peso">Peso:</label>
							<div class="col-lg-9">
								<input type="text" name="peso" id="peso" class="form-control">
							</div>
						</div>
						<input type="submit" value="Adicionar Faixa"
							class="btn btn-primary">
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>