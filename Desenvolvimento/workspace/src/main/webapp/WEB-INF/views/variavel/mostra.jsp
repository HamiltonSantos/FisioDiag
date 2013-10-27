<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Variavel</title>
</head>
<body>
	<h1>${variavel.descricao} </h1>

	<table id="tabela" class="table">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>Peso</th>
				<th>Acoes</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${variavel.faixaValores}" var="faixa">
				<tr>
					<td>${faixa.descricao}</td>
					<td>${faixa.peso}</td>
					<td><a href="/faddvm/faixaValor/remover/${faixa.id}">Excluir</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="dataTables_scroll"></div>
	<a href="/faddvm/categoria/${variavel.categoria.id }" class="btn btn-primary">Voltar</a>
	<a href="/faddvm/faixaValor/nova/${variavel.id}" class="btn btn-primary">Nova Faixa</a>
	
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
