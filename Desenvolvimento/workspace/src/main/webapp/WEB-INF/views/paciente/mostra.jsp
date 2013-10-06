<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Paciente</title>
</head>
<body>

	<div class="well">
	
		<div>
			Nome: ${paciente.nome};
		</div>
	
		<div>
			CPF: ${paciente.cpf}
		</div>
		
		<div>
			Numero de Registro: ${paciente.numRegistro}
		</div>
		
		<div>
			Data de Nascimento: ${paciente.dataNascimento}
		</div>
		
		<div>
			Sexo: ${paciente.sexo}
		</div>
	</div>

</body>
</html>