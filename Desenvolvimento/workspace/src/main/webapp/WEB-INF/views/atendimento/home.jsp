<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Atendimento</title>
</head>
<body>
	<div class="well">
		<p>
			<strong>Paciente:</strong> ${paciente.nome}
		</p>
		<p>
			<strong>Pontos:</strong> ${paciente.pontos}
		</p>
		<p>
			<strong>Indicação:</strong> ${paciente.indicacao.descricao}
		</p>
		<p>
			<a href="/faddvm/atendimento/${paciente.id}/detalhe">Ver detalhes indicação</a>
		</p>
	</div>
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
			<table id="tabelaHistorico" class="tabela table table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Fisioterapeuta</th>
						<th>Operação</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${paciente.historico}" var="historico">
						<tr>
							<td>${historico.dataHistorico}</td>
							<td>${historico.fisioterapeuta.nome}</td>
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
				</tbody>
			</table>
		</div>
		<div class="dataTables_scroll"></div>
		<div class="tab-pane" id="ocorrencias">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Fisioterapeuta</th>
						<th>Operação</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${paciente.historico}" var="historico">
						<c:if test="${1 == historico.variavel.id}">
							<tr>
								<td>${historico.dataHistorico}</td>
								<td>${historico.fisioterapeuta.nome}</td>
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
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="dataTables_scroll"></div>

			<form action="/faddvm/atendimento/${paciente.id}" method="post">
				<c:forEach items="${ocorrencias}" var="faixa">
					<label for="faixa_${faixa.id}">${faixa.descricao}</label>
					<input type="radio" id="faixa_${faixa.id}" name="valor" value="${faixa.valorMin}">
					<br />
				</c:forEach>
				<input type="text" name="dataHistorico" readonly class="form_datetime dataHistorico"> <input type="hidden"
					name="variavelId" value="${1}"
				> <input type="submit" class="btn" value="Salvar">
			</form>
		</div>
		<div class="tab-pane" id="intercorrencias">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>Data</th>
						<th>Fisioterapeuta</th>
						<th>Operação</th>
						<th>Valor</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${paciente.historico}" var="historico">
						<c:if test="${2 == historico.variavel.id}">
							<tr>
								<td>${historico.dataHistorico}</td>
								<td>${historico.fisioterapeuta.nome}</td>
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
						</c:if>
					</c:forEach>
				</tbody>
			</table>

			<div class="dataTables_scroll"></div>

			<form action="/faddvm/atendimento/${paciente.id}" method="post">
				<c:forEach items="${intercorrencias}" var="faixa">
					<label for="faixa_${faixa.id}">${faixa.descricao}</label>
					<input type="radio" id="faixa_${faixa.id}" name="valor" value="${faixa.valorMin}">
				</c:forEach>
				<input type="text" name="dataHistorico" readonly class="form_datetime dataHistorico"> <input type="hidden"
					name="variavelId" value="${2}"
				> <input type="submit" class="btn" value="Salvar">
			</form>
		</div>
		<c:forEach items="${categorias}" var="categoria">
			<div class="tab-pane" id="cat_${categoria.id}">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>Data</th>
							<th>Fisioterapeuta</th>
							<th>Operação</th>
							<th>Valor</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${paciente.historico}" var="historico">
							<c:forEach items="${categoria.variaveis}" var="variavel">
								<c:if test="${variavel.id == historico.variavel.id}">
									<tr>
										<td>${historico.dataHistorico}</td>
										<td>${historico.fisioterapeuta.nome}</td>
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
								</c:if>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>

				<div class="dataTables_scroll"></div>

				<c:forEach items="${categoria.variaveis}" var="variavel">
					<c:if test="${variavel.tipo eq 79}">
						<form action="/faddvm/atendimento/${paciente.id}" method="post">
							${variavel.descricao}<br>

							<c:forEach items="${variavel.faixaValores}" var="faixa">
								<label for="faixa_${faixa.id}">${faixa.descricao}</label>
								<input type="radio" id="faixa_${faixa.id}" name="valor" value="${faixa.valorMin}">
							</c:forEach>
							<input type="hidden" name="variavelId" value="${variavel.id}"> <input type="submit" class="btn"
								value="Salvar"
							>
						</form>
					</c:if>

					<c:if test="${variavel.tipo eq 82}">
						<form action="/faddvm/atendimento/${paciente.id}" method="post">
							<div id="div_var_${variavel.id}"></div>
							<label for="var_${variavel.id}"> ${variavel.descricao}</label> <input type="text" readonly="readonly"
								id="var_${variavel.id}" name="valor"
							> <input type="hidden" name="variavelId" value="${variavel.id}"> <input type="submit" class="btn"
								value="Salvar"
							> <br>
							<!-- Cria o spnnier para cada input  -->
							<script type="text/javascript">
								$(function() {
									$("#div_var_${variavel.id}").slider({
									min : parseInt("${variavel.valorMin}"),
									max : parseInt("${variavel.valorMax}"),
									slide : function(event, ui) {
										$("#var_${variavel.id}").val(ui.value);
									}
									});
								});
							</script>
							<!-- Fim do scpript spinner -->
						</form>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>


	<script type="text/javascript">
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});
		$(function() {
			$('#myTab a:first').tab('show')
		});

		$('.dataHistorico').datetimepicker({
		format : 'dd/mm/yyyy hh:ii',
		autoclose : 'true',
		language : 'pt-BR',
		todayBtn : true,
		startDate : '-120y',
		endDate : new Date()
		});

		$(document)
				.ready(
						function() {
							$('.table')
									.dataTable(
											{
												"oLanguage" : {
												"sProcessing" : "Aguarde enquanto os dados são carregados ...",
												"sLengthMenu" : "Mostrar _MENU_ registros",
												"sZeroRecords" : "Nenhum registro correspondente ao criterio encontrado",
												"sInfoEmtpy" : "Exibindo 0 a 0 de 0 registros",
												"sInfo" : "Exibindo de _START_ a _END_ de _TOTAL_ registros",
												"sInfoFiltered" : "",
												"sSearch" : "Procurar",
												"oPaginate" : {
												"sFirst" : "Primeiro",
												"sPrevious" : "Anterior",
												"sNext" : "Próximo",
												"sLast" : "Último"
												}
												}
											});
						});
	</script>
</body>
</html>
