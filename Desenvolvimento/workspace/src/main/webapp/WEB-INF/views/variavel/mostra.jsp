<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Variavel</title>
</head>
<body>
	<h1>Faixas de ${variavel.descricao}</h1>

	<table id="tabela" class="datatable table table-striped table-bordered">
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
					<td><a onclick="myFunction(${faixa.id})">Deletar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="dataTables_scroll"></div>
	<a href="/faddvm/categoria/${variavel.categoria.id }" class="btn btn-primary">Voltar</a>
	<a href="/faddvm/faixaValor/nova/${variavel.id}" class="btn btn-primary">Nova Faixa</a>

	<script>
		function myFunction(id) {
			bootbox.confirm(
					"Voce tem certeza que quer deseja deletar essa Faixa?",
					function(result) {
						if (result) {
							var url = "/faddvm/faixaValor/remover/" + id;
							$(location).attr('href', url);
						}
					});
		};
	</script>

</body>
</html>
