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
		<c:forEach items="${categoria.variaveis}" var="variavel">
			<tr>
				<td>
					<div class="well">
						${variavel.nome} <br>
						<div class="well">
							Faixas de Valores <br />
							<c:forEach items="${variavel.faixaValores}" var="faixa">
								<tr>
									<td>${faixa.descricao}</td>
								</tr>
							</c:forEach>
						</div>
						<div class="well">
							<h4>Adicionar nova Faixa</h4>
							<form
								action="/faddvm/categoria/${categoria.id}/${variavel.id}/adicionaFaixa"
								method="post" class="form-horizontal">
								
								<div class="form-group">
									<label class="col-lg-3 control-label" for="descricao">Descrição:</label>
									<div class="col-lg-9">
										<input type="text" name="descricao" class="form-control"
											id="descricao">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label" for="valorMin">Valor Mínimo:</label> 
									<div class="col-lg-9">
										<input type="text" name="valorMin" id="valorMin" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label" for="valorMax">Valor Máximo:</label> 
									<div class="col-lg-9">
										<input type="text" name="valorMax" id="valorMax" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label" for="peso">Peso:</label> 
									<div class="col-lg-9">
										<input type="text" name="peso" id="peso" class="form-control">
									</div>
								</div>
								<input type="submit" value="Adicionar Faixa" class="btn btn-primary">
							</form>
						</div>
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
				type="text" name="tipo"> <br /> <input type="submit"
				value="Adicionar Variavel">
		</form>
	</div>
</body>
</html>