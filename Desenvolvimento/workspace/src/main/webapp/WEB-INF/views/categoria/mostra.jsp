<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
</head>
<body>

	<div>
		Lista de Variaveis
		<c:forEach items="${variaveis}" var="variavel">
			<tr>
				<td>
					<div>${variavel.nome}
						Faixas de Valores <br /> Adicionar nova Faixa<br />
						<form action="/faddvm/categoria/${categoria.id}/${variavel.id}/adicionaFaixa" method="post">
						
							descricao: <input type="text" name="descricao"> <br />
							valorMin: <input type="text" name="valorMin"> <br />
							valorMax: <input type="text" name="valorMax"> <br />
							peso: <input type="text" name="peso"> <br />
							
							<input type="submit" value="Adicionar Variavel">
						</form>
					</div>
				</td>

			</tr>
		</c:forEach>
	</div>

	<div>
		Adicionar Variavel
		<form action="/faddvm/categoria/${categoria.id}/adicionaVariavel"
			method="post">
			nome: <input type="text" name="nome"> <br /> Tipo: <input
				type="text" name="tipo"> <br /> <input
				type="submit" value="Adicionar Variavel">
		</form>
	</div>
</body>
</html>