<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar/Editar Fisioterapeuta Simples</title>
</head>
<body>

	<h3 align="center">Fisioterapeuta</h3>
	<form:form action="/faddvm/fisioterapeuta" commandName="fisioterapeuta" class="form-horizontal">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label path="nome" class="col-lg-4 control-label">Nome</form:label>
			<div class="col-lg-4">
				<form:input path="nome" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="login" class="col-lg-4 control-label">Login</form:label>
			<div class="col-lg-4">
				<form:input path="login" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="senha" class="col-lg-4 control-label">Senha</form:label>
			<div class="col-lg-4">
				<form:password path="senha" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="contraSenha" class="col-lg-4 control-label">Repita a Senha</label>
			<div class="col-lg-4">
				<form:password path="contraSenha" class="form-control" />
			</div>
		</div>
		<div class="form-group" align="center">
			<form:hidden path="id" />
			<button type="submit" class="btn">
				<span class="glyphicon glyphicon-user"></span> Salvar
			</button>
		</div>

	</form:form>
</body>
</html>