<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Faixa</title>
</head>
<body>

	<h3 align="center">Nova Faixa de Variavel para: ${faixaValor.variavel.descricao}</h3>
	<form:form action="/faddvm/faixaValor" commandName="faixaValor" class="form-horizontal">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<c:if test="${faixaValor.variavel.tipo eq 79}">
			<div class="form-group">
				<form:label class="col-lg-3 control-label" path="descricao">Descrição:</form:label>
				<div class="col-lg-9">
					<form:input path="descricao" class="form-control" />
				</div>
			</div>
			<form:hidden path="valorMin" />
			<form:hidden path="valorMax" />
		</c:if>
		<c:if test="${faixaValor.variavel.tipo eq 82}">
			<div class="form-group">
				<form:label class="col-lg-3 control-label" path="valorMin">Valor Mínimo:</form:label>
				<div class="col-lg-9">
					<form:input path="valorMin" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label class="col-lg-3 control-label" path="valorMax">Valor Máximo:</form:label>
				<div class="col-lg-9">
					<form:input path="valorMax" class="form-control" />
				</div>
			</div>
			<form:hidden path="descricao" />
		</c:if>
		<div class="form-group">
			<form:label class="col-lg-3 control-label" path="peso">Peso:</form:label>
			<div class="col-lg-9">
				<form:input path="peso" class="form-control" />
			</div>
		</div>
		<form:hidden path="variavel.id" />
		<input type="submit" class="btn" value="Salvar">

	</form:form>


</body>
</html>