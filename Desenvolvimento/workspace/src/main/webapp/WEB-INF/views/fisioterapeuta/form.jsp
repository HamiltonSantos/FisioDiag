<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adiciona Fisioterapeuta Simples</title>
</head>
<body>

	<h3 align="center">Adicionar Fisioterapeuta</h3>
	<form:form action="/faddvm/fisioterapeuta" commandName="fisioterapeuta"
		class="form-horizontal">
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
				<form:input path="senha" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="contrasenha" class="col-lg-4 control-label">Repita a Senha</label>
			<div class="col-lg-4">
				<input type="text" class="form-control" name="contrasenha" />
			</div>
		</div>
		<div class="form-group" align="center">
			<button type="submit" class="btn btn-primary btn-lg">
				<span class="glyphicon glyphicon-user"></span> Adicionar
			</button>
		</div>

	</form:form>
</body>
</html>