<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Pagina Inicial</h1>
	<ul id="myTab" class="nav nav-tabs nav-stacked ">
		<li class="active"><a href="#1">Teste</a></li>
		<li><a href="#2">Teste</a></li>
		<li><a href="#3">Teste</a></li>
		<li><a href="#4">Teste</a></li>
	</ul>
	<div class="tab-content">
		<div id="1" class="tab-pane well">div 1</div>
		<div id="2" class="tab-pane">div 2</div>
		<div id="3" class="tab-pane">div 3</div>
		<div id="4" class="tab-pane well">div 4</div>
	</div>

	<script>
		$('#myTab a:first').tab('show');
		$('#myTab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});
	</script>
</body>
</html>
