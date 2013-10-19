<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adiciona Indice</title>
</head>
<body>

	<h3 align="center">Novo indice</h3>
	<form:form method="POST" action="/faddvm/indice" commandName="indice" class="form-horizontal">
		<div class="form-group">
			<form:errors path="*" class="alert alert-danger" element="div" />
		</div>
		<div class="form-group">
			<form:label path="descricao" class="col-lg-4 control-label">Descricao</form:label>
			<div class="col-lg-6">
				<form:input path="descricao" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="valorMin" class="col-lg-4 control-label">valorMin</form:label>
			<div class="col-lg-3">
				<c:if test="${indice.valorMin == null}">
					<form:input path="valorMin" class="form-control" />
				</c:if>
				<c:if test="${indice.valorMin != null}">
					<form:input readonly="true" path="valorMin" class="form-control" />
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<form:label path="valorMax" class="col-lg-4 control-label">valorMax</form:label>
			<div class="col-lg-3">
				<form:input path="valorMax" class="form-control" />
			</div>
		</div>
		<form:hidden path="peso" />
		<form:hidden path="id" />
		<input type="submit" class="btn" value="Salvar">

	</form:form>
</body>
</html>