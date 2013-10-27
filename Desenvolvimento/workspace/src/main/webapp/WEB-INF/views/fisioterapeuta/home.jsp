<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Fisioterapeutas</h1>
	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Acoes</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${fisioterapeutas}" var="fisioterapeuta">
				<tr>
					<td>${fisioterapeuta.nome}</td>
					<td>${fisioterapeuta.login}</td>
					<td>*****</td>
					<td><a href="/faddvm/fisioterapeuta/${fisioterapeuta.id}">Mostrar</a>
						<a href="/faddvm/fisioterapeuta/${fisioterapeuta.id}/editar">Editar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="dataTables_scroll"></div>

	<a href="/faddvm/fisioterapeuta/novo" class="btn btn-primary">Novo</a>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabela').dataTable( {
			    "oLanguage": {
			    	"sProcessing": "Aguarde enquanto os dados são carregados ...",
				    "sLengthMenu": "Mostrar _MENU_ registros",
				    "sZeroRecords": "Nenhum registro correspondente ao criterio encontrado",
				    "sInfoEmpty": "Exibindo 0 a 0 de 0 registros",
				    "sInfo": "Exibindo de _START_ a _END_ de _TOTAL_ registros",
				    "sInfoFiltered": "",
				    "sSearch": "Procurar",
				    "oPaginate": {
				       "sFirst":    "Primeiro",
				       "sPrevious": "Anterior",
				       "sNext":     "Próximo",
				       "sLast":     "Último"	
					}
			    }
			  } );
		});
	</script>
</body>
</html>
