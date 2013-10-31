<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adicionar/Editar Paciente</title>
</head>
<body>

	<h3 align="center">Paciente</h3>
	<form:form action="/faddvm/paciente" commandName="paciente" class="form-horizontal">
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
				<form:input path="dataNascimento" readonly="true" id="dataNasicmento" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="cpf" class="col-lg-4 control-label">CPF</form:label>
			<div class="col-lg-3">
				<form:input path="cpf" class="form-control" id="cpf" />
			</div>
		</div>
		<div class="form-group">
			<form:label path="numRegistro" class="col-lg-4 control-label">Registro Hospitalar</form:label>
			<div class="col-lg-3">
				<form:input path="numRegistro" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<form:label class="col-lg-4 control-label" path="sexo">Sexo</form:label>
			<div class="col-lg-3">
				<form:radiobutton path="sexo" value="M" label=" Masculino" />
				<form:radiobutton path="sexo" value="F" label=" Feminino" />
			</div>
		</div>

		<form:hidden path="id" />
		<input type="submit" class="btn btn-primary" value="Salvar">

	</form:form>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#cpf").mask("999.999.999-99");
			// 			$("#dataNasicmento").mask("99/99/9999");

			$('#dataNasicmento').datepicker({
			format : 'dd/mm/yyyy',
			autoclose : 'true',
			language : 'pt-BR',
			startDate : '-120y',
			endDate : new Date()
			});

			// 			$("#dataNasicmento").datepicker({
			// 			showOn : "both",
			// 			buttonText : "Selecionar",
			// 			yearRange : "1850:2013",
			// 			autoSize : true,
			// 			minDate : new Date(1850, 01, 01),
			// 			maxDate : new Date(),
			// 			changeMonth : true,
			// 			changeYear : true
			// 			});

		});
	</script>



</body>
</html>