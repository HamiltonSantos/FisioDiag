<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adiciona Paciente</title>
</head>
<body>

	<h3 align="center">Paciente</h3>
	<form:form action="/faddvm/paciente" commandName="paciente"
		class="form-horizontal">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label path="nome" class="col-lg-4 control-label">Nome</form:label>
			<div class="col-lg-6">
				<form:input path="nome" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="dataNascimento" class="col-lg-4 control-label">Data de Nascimento</form:label>
			<div class="col-lg-3">
				<form:input path="dataNascimento" type="date" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="cpf" class="col-lg-4 control-label">CPF</form:label>
			<div class="col-lg-3">
				<form:input path="cpf" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="numRegistro" class="col-lg-4 control-label">Registro Hospitalar</form:label>
			<div class="col-lg-3">
				<form:input path="numRegistro" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 control-label">Sexo</label>
			<div class="col-lg-3">
				<form:label path="sexo">Masculino</form:label>
				<form:radiobutton path="sexo" value="M" />
				<form:label path="sexo">Feminino</form:label>
				<form:radiobutton path="sexo" value="F" />
			</div>
		</div>
		<input type="submit" value="Salvar">

	</form:form>


</body>
</html>