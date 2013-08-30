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

	<h3>Paciente</h3>
	<form:form action="/faddvm/pacientes" commandName="paciente">
		<div>
			<form:errors path="*" class="alert alert-danger" element="div"/>
			<%-- <form:errors path="cpf" class="alert alert-danger" element="div"/>
			<form:errors path="sexo" class="alert alert-danger" element="div"/> --%>
		</div>
		<div class="form-group">
			<form:label path="nome">Nome</form:label>
			<form:input path="nome" class="form-control" />
		</div>
		<form:label path="dataNascimento">Data de Nascimento</form:label>
		<form:input path="dataNascimento" />
		<br />
		<form:label path="cpf">CPF</form:label>
		<form:input path="cpf" />
		<br />
		<form:label path="numRegistro">Numero de Registro Hospitalar</form:label>
		<form:input path="numRegistro" />
		<br />
		Sexo <br />
		<form:label path="sexo">Masculino</form:label>
		<form:radiobutton path="sexo" value="M" />
		<form:label path="sexo">Feminino</form:label>
		<form:radiobutton path="sexo" value="F" />
		<br />
		<input type="submit" value="Savar">

	</form:form>


</body>
</html>