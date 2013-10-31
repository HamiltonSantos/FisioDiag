<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Indice</title>
</head>
<body>
	<h1>Indices</h1>

	<table id="tabela" class="datatable table table-striped table-bordered">
		<thead>
			<tr>
				<th>Descricao</th>
				<th>Valor Minimo</th>
				<th>Valor Maximo</th>
				<th>Ação</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${indices}" var="indice">
				<tr>
					<td>${indice.descricao}</td>
					<td>${indice.valorMin}</td>
					<td>${indice.valorMax}</td>
					<td><a onclick="myFunction(${indice.id})">Deletar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="dataTables_scroll"></div>

	<a href="/faddvm/indice/novo" class="btn btn-primary">Novo</a>

	<script>
		function myFunction(id) {
			bootbox.confirm(
					"Voce tem certeza que quer deseja deletar essa Indice?",
					function(result) {
						if (result) {
							var url = "/faddvm/indice/remover/" + id;
							$(location).attr('href', url);
						}
					});
		};
	</script>

</body>
</html>
