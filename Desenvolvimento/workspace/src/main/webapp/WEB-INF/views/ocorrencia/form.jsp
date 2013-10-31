<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adiciona Ocorrência</title>
</head>
<body>

	<h3 align="center">Nova ocorrência</h3>
	<form:form action="/faddvm/ocorrencia" commandName="ocorrencia"
		class="form-horizontal">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label path="descricao" class="col-lg-4 control-label">Descrição</form:label>
			<div class="col-lg-6">
				<form:input path="descricao" class="form-control" />
			</div>
		</div>
		<form:hidden path="peso" />
		<form:hidden path="valorMin" />
		<form:hidden path="valorMax" />
		<form:hidden path="id" />
		<input type="submit" class="btn btn-primary" value="Salvar">

	</form:form>

	<script type="text/javascript">
		
	</script>

</body>
</html>