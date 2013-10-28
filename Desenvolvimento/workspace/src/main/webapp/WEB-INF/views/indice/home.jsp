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
				<th>ValorMinimo</th>
				<th>ValorMaximo</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${indices}" var="indice">
				<tr>
					<td>${indice.descricao}</td>
					<td>${indice.valorMin}</td>
					<td>${indice.valorMax}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="dataTables_scroll"></div>

	<a href="/faddvm/indice/novo" class="btn btn-primary">Novo</a>

</body>
</html>
