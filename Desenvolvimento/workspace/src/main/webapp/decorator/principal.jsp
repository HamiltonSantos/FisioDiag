<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/faddvm/webjars/bootstrap/3.0.0-rc.2/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="/faddvm/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" rel="stylesheet" media="screen">
<link href="/faddvm/webjars/datatables/1.9.4/media/css/jquery.dataTables.css" rel="stylesheet" media="screen">
<link href="/faddvm/webjars/jqplot/1.0.8r1250/jquery.jqplot.css" rel="stylesheet" media="screen">
<link href="/faddvm/webjars/bootstrap-datetimepicker/6aa746736d/css/datetimepicker.css" rel="stylesheet" media="screen">
<link href="/faddvm/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="/faddvm/resources/css/datatables.css" media="screen">
<script type="text/javascript" src="/faddvm/webjars/jquery/1.10.2/jquery.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/bootstrap/3.0.0-rc.2/js/bootstrap.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/datatables/1.9.4/media/js/jquery.dataTables.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jquery-ui/1.10.3/ui/i18n/jquery.ui.datepicker-pt-BR.js"
	charset="UTF-8"
></script>
<script type="text/javascript" src="/faddvm/webjars/jquery-ui/1.10.3/ui/jquery-ui.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jquery-maskedinput/1.3.1/jquery.maskedinput.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jqplot/1.0.8r1250/jquery.jqplot.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jqplot/1.0.8r1250/plugins/jqplot.highlighter.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jqplot/1.0.8r1250/plugins/jqplot.cursor.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/faddvm/webjars/jqplot/1.0.8r1250/plugins/jqplot.dateAxisRenderer.min.js"
	charset="UTF-8"
></script>
<script type="text/javascript" src="/faddvm/webjars/bootstrap-datetimepicker/6aa746736d/js/bootstrap-datetimepicker.js"
	charset="UTF-8"
></script>
<script type="text/javascript" src="/faddvm/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"
	charset="UTF-8"
></script>
<script type="text/javascript"
	src="/faddvm/webjars/bootstrap-datetimepicker/6aa746736d/js/locales/bootstrap-datetimepicker.pt-BR.js" charset="UTF-8"
></script>
<script type="text/javascript" src="/faddvm/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.pt-BR.js"
	charset="UTF-8"
></script>
<script src="/faddvm/resources/js/datatables.js"></script>
<title><decorator:title default="Welcome!" /></title>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8"></div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-body">
						Seja bem vindo,
						<c:out value="${sessionScope.fisioterapeutaLogado.nome}" />
						<br> <a href="/faddvm/sair">Sair</a>
					</div>
				</div>

			</div>

		</div>
		<div class="row">
			<!--div para o menu esquerdo-->
			<div id="menu" class="col-md-2">
				<div class="panel panel-default">
					<ul class="nav">
						<li><a href="/faddvm">Home</a></li>
						<li><a href="/faddvm/paciente">Pacientes</a></li>
						<li><a href="/faddvm/categoria">Categorias</a></li>
						<li><a href="/faddvm/fisioterapeuta">Fisioterapeutas</a></li>
						<li><a href="/faddvm/ocorrencia">Ocorrências</a></li>
						<li><a href="/faddvm/intercorrencia">Intercorrências</a></li>
						<li><a href="/faddvm/indice">Índices</a></li>
					</ul>
				</div>
			</div>

			<!--div para o conteudo gerado-->
			<div id="conteudo" class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${ msgSucesso != null }">
							<div class="alert alert-success">
								<strong>${msgSucesso}</strong>
							</div>
						</c:if>
						<c:if test="${ msgErro != null }">
							<div class="alert alert-danger">
								<strong>${msgErro}</strong>
							</div>
						</c:if>
						<decorator:body />
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('.datatable')
									.dataTable(
											{
												"oLanguage" : {
												"sProcessing" : "Aguarde enquanto os dados são carregados ...",
												"sLengthMenu" : "Mostrar _MENU_ registros",
												"sZeroRecords" : "Nenhum registro correspondente ao critério encontrado",
												"sInfoEmpty" : "Exibindo 0 a 0 de 0 registros",
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

							$('.datatable')
									.each(
											function() {
												var datatable = $(this);
												// SEARCH - Add the placeholder for Search and Turn this into in-line form control
												var search_input =
														datatable
																.closest(
																		'.dataTables_wrapper')
																.find(
																		'div[id$=_filter] input');
												search_input
														.attr('placeholder',
																'Pesquisar');
												search_input
														.addClass('form-control input-sm');
												// LENGTH - Inline-Form control
												var length_sel =
														datatable
																.closest(
																		'.dataTables_wrapper')
																.find(
																		'div[id$=_length] select');
												length_sel
														.addClass('form-control input-sm');
											});
						});
	</script>
</body>
</html>