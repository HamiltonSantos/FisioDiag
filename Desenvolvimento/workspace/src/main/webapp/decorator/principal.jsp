<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/faddvm/webjars/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<!--div para o menu esquerdo-->
			<div id="menu" class="col-md-2">
				<div class="well">
					<ul class="nav">
						<li><a href="/faddvm">Home</a></li>
						<li><a href="/faddvm/pacientes">Pacientes</a></li>
						<li><a href="/faddvm/categoria/nova">Categorias</a></li>
						<li><a href="/faddvm/fisioterapeuta/novo">Fisioterapeutas</a></li>
					</ul>
				</div>
			</div>

			<!--div para o conteudo gerado-->
			<div id="conteudo" class="col-md-9">
				<div class="well">
					<decorator:body />
				</div>
			</div>
		</div>
	</div>
</body>
</html>