<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Atendimento</title>
</head>
<body>
	<div class="panel">
		<div class="panel-body">
			<p>
				<strong>Paciente:</strong> ${paciente.nome}
			</p>
			<p>
				<strong>Pontos:</strong> ${paciente.pontos}
			</p>
			<p>
				<strong>Indicação:</strong> ${indicacao.descricao}
			</p>
			<p>
				<a href="/faddvm/atendimento/${paciente.id}/detalhe">Ver detalhes indicação</a>
			</p>
		</div>
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
			<div class="panel panel-default">
				<div class="panel-body">
					<table id="tabelaHistorico" class="datatable table table-striped table-bordered">
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
									<td>${historico.faixa.variavel.descricao}</td>
									<c:if test="${historico.faixa.variavel.tipo eq 79}">
										<c:forEach items="${historico.faixa.variavel.faixaValores}" var="faixa">
											<c:if test="${faixa.id eq historico.faixa.id}">
												<td>${faixa.descricao}</td>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${historico.faixa.variavel.tipo eq 82}">
										<td>${historico.valor}</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="dataTables_scroll"></div>

		<div class="tab-pane" id="ocorrencias">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">Formulário</h4>
				</div>
				<div class="panel-body">
					<%-- 										<form action="/faddvm/atendimento/${paciente.id}" method="post"> --%>
					<%-- 											<c:forEach items="${ocorrencias}" var="faixa"> --%>
					<%-- 												<label for="faixa_${faixa.id}">${faixa.descricao}</label> --%>
					<%-- 												<input type="radio" id="faixa_${faixa.id}" name="faixaId" value="${faixa.id}"> --%>
					<!-- 												<br /> -->
					<%-- 											</c:forEach> --%>
					<!-- 															<input type="text" name="dataHistorico" readonly="readonly" class="form_datetime dataHistorico">  -->
					<!-- 											<input type="submit" class="btn" value="Salvar"> -->
					<%-- 										</form> --%>
					<form class="form-inline" action="/faddvm/atendimento/${paciente.id}" method="post">
						<div class="form-group">
							<select name="faixaId" class="form-control">
								<c:forEach items="${ocorrencias}" var="faixa">
									<option value="${faixa.id}">${faixa.descricao}</option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" class="btn" value="Salvar">
					</form>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">Histórico</h4>
				</div>
				<div class="panel-body">
					<table class="datatable table table-striped table-bordered">
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
								<c:if test="${1 == historico.faixa.variavel.id}">
									<tr>
										<td>${historico.dataHistorico}</td>
										<td>${historico.fisioterapeuta.nome}</td>
										<td>${historico.faixa.variavel.descricao}</td>
										<c:if test="${historico.faixa.variavel.tipo eq 79}">
											<c:forEach items="${historico.faixa.variavel.faixaValores}" var="faixa">
												<c:if test="${faixa.id eq historico.faixa.id}">
													<td>${faixa.descricao}</td>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${historico.faixa.variavel.tipo eq 82}">
											<td>${historico.valor}</td>
										</c:if>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="dataTables_scroll"></div>

		</div>
		<div class="tab-pane" id="intercorrencias">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Formulário</h3>
				</div>
				<div class="panel-body">
					<%-- 					<form action="/faddvm/atendimento/${paciente.id}" method="post"> --%>
					<%-- 						<c:forEach items="${intercorrencias}" var="faixa"> --%>
					<%-- 							<label for="faixa_${faixa.id}">${faixa.descricao}</label> --%>
					<%-- 							<input type="radio" id="faixa_${faixa.id}" name="faixaId" value="${faixa.id}"> --%>
					<!-- 							<br /> -->
					<%-- 						</c:forEach> --%>
					<!-- 										<input type="text" name="dataHistorico" readonly="readonly" class="form_datetime dataHistorico">  -->
					<!-- 						<input type="submit" class="btn" value="Salvar"> -->
					<%-- 					</form> --%>

					<form class="form-inline" action="/faddvm/atendimento/${paciente.id}" method="post">
						<div class="form-group">
							<select name="faixaId" class="form-control">
								<c:forEach items="${intercorrencias}" var="faixa">
									<option value="${faixa.id}">${faixa.descricao}</option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" class="btn" value="Salvar">
					</form>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Historico</h3>
				</div>
				<div class="panel-body">
					<table class="datatable table table-striped table-bordered">
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
								<c:if test="${2 == historico.faixa.variavel.id}">
									<tr>
										<td>${historico.dataHistorico}</td>
										<td>${historico.fisioterapeuta.nome}</td>
										<td>${historico.faixa.variavel.descricao}</td>
										<c:if test="${historico.faixa.variavel.tipo eq 79}">
											<c:forEach items="${historico.faixa.variavel.faixaValores}" var="faixa">
												<c:if test="${faixa.id eq historico.faixa.id}">
													<td>${faixa.descricao}</td>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${historico.faixa.variavel.tipo eq 82}">
											<td>${historico.valor}</td>
										</c:if>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="dataTables_scroll"></div>
		</div>

		<c:forEach items="${categorias}" var="categoria">
			<div class="tab-pane" id="cat_${categoria.id}">
				<!-- form para todas as categorias -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Formulário</h3>
					</div>

					<div class="panel-body">

						<div>
							<c:forEach items="${categoria.variaveis}" var="variavel">
								<c:if test="${variavel.tipo eq 79}">
									<div class="panel panel-default">
										<div class="panel-heading">${variavel.descricao}</div>
										<div class="panel-body">
											<form class="form-inline" action="/faddvm/atendimento/${paciente.id}" method="post">
												<div class="form-group">
													<select name="faixaId" class="form-control">
														<c:forEach items="${variavel.faixaValores}" var="faixa">
															<option value="${faixa.id}">${faixa.descricao}</option>
														</c:forEach>
													</select>
												</div>
												<input type="submit" class="btn" value="Salvar">
											</form>
										</div>
									</div>
								</c:if>

								<c:if test="${variavel.tipo eq 82}">
									<div class="panel panel-default">
										<div class="panel-heading">${variavel.descricao}</div>
										<div class="panel-body">
											<form action="/faddvm/atendimento/${paciente.id}" method="post">
												<div id="div_var_${variavel.id}"></div>
												<input type="text" readonly="readonly" id="var_${variavel.id}" name="valor"> <input type="hidden"
													name="variavelId" value="${variavel.id}"
												> <input type="submit" class="btn" value="Salvar"> <br>
												<!-- Cria o spnnier para cada input  -->
												<script type="text/javascript">
													$(function() {
														$(
																"#div_var_${variavel.id}")
																.slider(
																		{
																		min : parseInt("${variavel.valorMin}"),
																		max : parseInt("${variavel.valorMax}"),
																		slide : function(
																				event,
																				ui) {
																			$(
																					"#var_${variavel.id}")
																					.val(
																							ui.value);
																		}
																		});
													});
												</script>
												<!-- Fim do scpript spinner -->
											</form>
										</div>
									</div>

								</c:if>
							</c:forEach>
						</div>

					</div>
				</div>
				<!-- Fim form para as categorias -->

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Histórico</h3>
					</div>
					<div class="panel-body">
						<table class="datatable table table-striped table-bordered">
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
										<c:if test="${variavel.id == historico.faixa.variavel.id}">
											<tr>
												<td>${historico.dataHistorico}</td>
												<td>${historico.fisioterapeuta.nome}</td>
												<td>${historico.faixa.variavel.descricao}</td>
												<c:if test="${historico.faixa.variavel.tipo eq 79}">
													<c:forEach items="${historico.faixa.variavel.faixaValores}" var="faixa">
														<c:if test="${faixa.id eq historico.faixa.id}">
															<td>${faixa.descricao}</td>
														</c:if>
													</c:forEach>
												</c:if>
												<c:if test="${historico.faixa.variavel.tipo eq 82}">
													<td>${historico.valor}</td>
												</c:if>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
						<div class="dataTables_scroll"></div>
					</div>
				</div>
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
	</script>
</body>
</html>
