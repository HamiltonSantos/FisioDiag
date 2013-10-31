-- Admin // administrador - 123456
insert into faddvm.Fisioterapeuta (id,login,nome,senha) values (1,"administrador","Administrador do Sistema","E10ADC3949BA59ABBE56E057F20F883E");

-- Paciente 
insert into faddvm.Paciente (id,cpf,dataNascimento,nome,numRegistro,sexo) values (1,'020.401.371-22','1992-02-25','Talles Henrique Souza Borges','10203040','M');

-- Categorias
insert into faddvm.Categoria (id,descricao,status) values (1,"Ocorrência",'A');
insert into faddvm.Categoria (id,descricao,status) values (2,"Intercorrência",'A');
insert into faddvm.Categoria (id,descricao,status) values (3,"Indice",'A');

-- Variaveis
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (1,"Ocorrência",'A','O',1);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (2,"Intercorrência",'A','O',2);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (3,"Indice",'A','R',3);

-- Faixas de valores - Ocorrencias
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (1,'Entrada UTI',0,0,0,1);
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (2,'Saida UTI',0,0,0,1);
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (3,'Inicio Ventilação Mecanica',0,0,0,1);
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (4,'Inicio Desmame',0,0,0,1);
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (5,'Extubação',0,0,0,1);
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (6,'Reintubação',0,0,0,1);

-- Faixas de Valores -- Intercorrencias
insert into faddvm.FaixaValor (id,descricao,peso,valorMax,valorMin,variavel_id) values (7,'Óbito',0,0,0,2);

-- Pontos de um paciente. parametro(paciente_id)
select sum(f.peso)
	from faddvm.Historico h, faddvm.FaixaValor f
	where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh, faddvm.FaixaValor ff, faddvm.Variavel vv where hh.paciente_id = ?1 and hh.faixa_id = ff.id and ff.variavel_id = vv.id group by vv.id)
	and h.faixa_id = f.id
	and h.valor between f.valorMin and f.valorMax 
	and h.paciente_id = ?1
		
-- Pega Indicacao (FaixaValor) de um paciente atraves dos pontos dele. parametro (pontos)
select * from faddvm.FaixaValor f where ?1 between f.valorMin and f.valorMax and f.variavel.id = 3
		
-- Lista Historico do paciente que estao contandos na pontuacao. parametros(paciente_id)
select h.*, f.peso
	from faddvm.Historico h, faddvm.FaixaValor f
	where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh, faddvm.FaixaValor ff, faddvm.Variavel vv where hh.paciente_id = 1 and vv.id != 2 and vv.id != 1 and hh.faixa_id = ff.id and ff.variavel_id = vv.id group by vv.id)
	and h.faixa_id = f.id
	and h.valor between f.valorMin and f.valorMax 
	and h.paciente_id = 1
	order by f.peso desc
	
-- Pega qual faixa eh de acordo com um valor de uma variavel. parametro(variavel_id,valor)
SELECT * FROM faddvm.FaixaValor f 
	where f.variavel_id = ?1
	and ?2 between f.valorMin and f.valorMax
	
-- Pega um Historico de quando foi a ultima entrada do paciente na UTI. paremtro(paciente_id)
select h.* from faddvm.Historico h 
	where faixa_id = 1 and h.paciente_id = 1
	and h.dataHistorico = (select max(hh.dataHistorico) from faddvm.Historico hh where hh.paciente_id = 1 and hh.faixa_id = 1 )
	
-- Pega o Historico mais recente, entrada ou saida. parametro(paciente_id)
select h.* from faddvm.Historico h 
	where h.paciente_id = 1
	and h.dataHistorico = (select max(hh.dataHistorico) from faddvm.Historico hh where hh.paciente_id = 1 and hh.faixa_id = 1 or hh.faixa_id = 2)
	
-- Pega o Historico Inicio de VM mais recente. parametro(paciente_id)
select h.* from faddvm.Historico h 
	where h.faixa_id = 3 and h.paciente_id = 1
	and h.dataHistorico >= (select max(hh.dataHistorico) from faddvm.Historico hh where hh.faixa_id = 1 and hh.paciente_id = 1)
	
-- Pega o Historico Extubacao mais recente. parametro(paciente_id)
select h.* from faddvm.Historico h 
	where h.faixa_id = 5
	and h.dataHistorico >= (select max(hh.dataHistorico) from faddvm.Historico hh where hh.faixa_id = 1 and hh.paciente_id = 1)
	
-- Lista todos os pacientes que estao em VM
select h.* from faddvm.Historico h
	where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh
	where (hh.faixa_id != 2 or hh.faixa_id != 5)
	and hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where (hhh.faixa_id = 5 or hhh.faixa_id = 2) and hhh.paciente_id = hh.paciente_id), 0)
	and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7)
group by hh.paciente_id )
