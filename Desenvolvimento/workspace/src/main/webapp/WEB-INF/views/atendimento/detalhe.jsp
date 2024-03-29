<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Paciente</title>
</head>
<body>
	<div class="panel">
		<div class="panel-body">
			<h1>Paciente: ${paciente.nome}</h1>
			<div>
				<h2>Pontos: ${paciente.pontos}</h2>
			</div>
			<div>CPF: ${paciente.cpf}</div>
			<div>Número de Registro: ${paciente.numRegistro}</div>
			<div>Data de Nascimento: ${paciente.dataNascimento}</div>
			<div>Sexo: ${paciente.sexo}</div>
		</div>
	</div>

	<div class="panel">
		<div class="panel-body">
			<h1>Indicação: ${indicacao.descricao}</h1>
			<ol>
				<c:forEach items="${historicoIndicacao}" var="historico">

					<li>${historico.faixa.variavel.descricao}-Valor:${historico.valor}</li>

				</c:forEach>
			</ol>
		</div>
	</div>
</body>
</html>