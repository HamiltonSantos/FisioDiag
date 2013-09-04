<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
<title>Atendimento</title>
</head>
<body>
	<h1>Atendimento ao paciente: ${paciente.nome}</h1>

	<ul id="myTab" class="nav nav-tabs nav-justified">
		<li><a href="#historico">Histórico</a></li>
		<c:forEach items="${categorias}" var="categoria">
			<li><a href="#cat_${categoria.id}">${categoria.descricao}</a></li>
		</c:forEach>
		<li><a href="#intercorrencias">Intercorrências</a></li>
		<li><a href="#ocorrencias">Ocorrências</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane" id="historico">
			<table class="table">
			<tr>
				<th>Data</th>
				<th>Operação</th>
				<th>Valor</th>
			</tr>
			<c:forEach items="${paciente.historico}" var="historico">
				<tr>
					<td>${historico.data}</td>
					<td>${historico.variavel.descricao}</td>
					<c:if test="${historico.variavel.tipo eq 79}">
						<c:forEach items="${historico.variavel.faixaValores}" var="faixa">
							<c:if test="${faixa.valorMin eq historico.valor}">
								<td>${faixa.descricao}</td>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${historico.variavel.tipo eq 82}">
						<td>${historico.valor}</td>
					</c:if>
				</tr>
			
			</c:forEach>
			
			</table>
		</div>
		<div class="tab-pane" id="ocorrencias">
			Ocorrencias blablabla
		</div>
		<div class="tab-pane" id="intercorrencias">
			Intercorrencias blablabla
		</div>
		<c:forEach items="${categorias}" var="categoria">
			<div class="tab-pane" id="cat_${categoria.id}">
					<c:forEach items="${categoria.variaveis}" var="variavel">
					
						<c:if test="${variavel.tipo eq 79}">
							<form action="/faddvm/atendimento/${paciente.id}" method="post">
								${variavel.descricao}<br>
								
								<c:forEach items="${variavel.faixaValores}" var="faixa">
									<label for="faixa_${faixa.id}">${faixa.descricao}</label>
									<input type="radio" id="faixa_${faixa.id}" name="valor" value="${faixa.valorMin}">
								</c:forEach>
								<br>
								<input type="hidden" name="variavelId" value="${variavel.id}"> 
								<input type="submit" value="Salvar">
							</form>
						</c:if>
					
						<c:if test="${variavel.tipo eq 82}">
							<form action="/faddvm/atendimento/${paciente.id}" method="post">
								<label for="var_${variavel.id}"> ${variavel.descricao}</label>
								<input type="text" id="var_${variavel.id}" name="valor">
								<input type="hidden" name="variavelId" value="${variavel.id}"> 
								<input type="submit" value="Salvar">
								<br>
							</form>
						</c:if>
						
					</c:forEach>
					
			</div>
		</c:forEach>
	</div>


	<script>
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		$(function () {
    		$('#myTab a:first').tab('show')
  		})
	</script>
</body>
</html>
