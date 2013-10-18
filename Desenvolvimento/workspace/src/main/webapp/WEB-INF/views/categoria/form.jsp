<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Categoria</title>
</head>
<body>
	<h3>Adicionar Categoria</h3>

	<form:form action="/faddvm/categoria" commandName="categoria">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label class="col-lg-3 control-label" path="descricao">Descrição:</form:label>
			<div class="col-lg-9">
				<form:input path="descricao" class="form-control" placeholder="Descrição da Categoria" />
			</div>
		</div>
		<form:hidden path="status" />
		<input type="submit" class="btn" value="Salvar" />
	</form:form>

</body>
</html>