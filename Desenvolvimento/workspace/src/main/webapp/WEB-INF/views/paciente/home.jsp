<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Pacientes</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Data de Nascimento</th>
				<th>CPF</th>
				<th>Sexo</th>
				<th>N�mero de Registro</th>
				<th>A��es</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pacientes}" var="paciente">
				<tr>
					<td>${paciente.nome}</td>
					<td>${paciente.dataNascimento}</td>
					<td>${paciente.cpf}</td>
					<td>${paciente.sexo}</td>
					<td>${paciente.numRegistro}</td>
					<td><a href="/faddvm/atendimento/${paciente.id}">Atender</a> <a href="/faddvm/paciente/${paciente.id}">Mostra</a>
						<a href="/faddvm/paciente/${paciente.id}/editar">Editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_scroll"></div>
	<a href="/faddvm/paciente/novo" class="btn btn-primary">Novo</a>
	
	<script type="text/javascript">
		$(document).ready( function() {
		  $('#tabela').dataTable( {
		    "oLanguage": {
		    	"sProcessing": "Aguarde enquanto os dados s�o carregados ...",
			    "sLengthMenu": "Mostrar _MENU_ registros",
			    "sZeroRecords": "Nenhum registro correspondente ao crit�rio encontrado",
			    "sInfoEmpty": "Exibindo 0 a 0 de 0 registros",
			    "sInfo": "Exibindo de _START_ a _END_ de _TOTAL_ registros",
			    "sInfoFiltered": "",
			    "sSearch": "Procurar",
			    "oPaginate": {
			       "sFirst":    "Primeiro",
			       "sPrevious": "Anterior",
			       "sNext":     "Pr�ximo",
			       "sLast":     "�ltimo"	
				}
		    }
		  } );
		} );
	</script>

</body>
</html>
