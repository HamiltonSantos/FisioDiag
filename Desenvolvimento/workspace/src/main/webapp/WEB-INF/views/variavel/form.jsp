<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Variável</title>
</head>
<body>

	<h3 align="center">Nova Variável para a Categoria: ${variavel.categoria.descricao}</h3>
	<form:form action="/faddvm/variavel" commandName="variavel" class="form-horizontal" method="POST">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label path="descricao" class="col-lg-4 control-label">Descrição</form:label>
			<div class="col-lg-6">
				<form:input path="descricao" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="tipo" class="col-lg-4 control-label">Tipo Variável</form:label>
			<div class="col-lg-3">
				<form:radiobutton path="tipo" value="O" label="Opção" />
				<form:radiobutton path="tipo" value="R" label="Range" />
			</div>
		</div>
		<form:hidden path="status" />
		<form:hidden path="id" />
		<form:hidden path="categoria.id" />
		<input type="submit" class="btn btn-primary" value="Salvar">

	</form:form>


</body>
</html>