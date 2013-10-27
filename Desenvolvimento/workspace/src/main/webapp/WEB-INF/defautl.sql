-- Admin
insert into faddvm.Fisioterapeuta (id,login,nome,senha) values (1,"admin","admin","21232F297A57A5A743894A0E4A801FC3");

-- Categorias
insert into faddvm.Categoria (id,descricao,status) values (1,"ocorrencia",'A');
insert into faddvm.Categoria (id,descricao,status) values (2,"intercorrencia",'A');
insert into faddvm.Categoria (id,descricao,status) values (3,"indice",'A');

-- Variaveis
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (1,"ocorrencia",'A','O',1);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (2,"intercorrencia",'A','O',2);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (3,"indice",'A','R',3);

-- Pontos de um paciente
select sum(f.peso)
	from faddvm.Historico h, faddvm.Variavel v, faddvm.FaixaValor f
	where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh where hh.paciente_id = 9 group by hh.variavel_id)
	and h.variavel_id = v.id
	and v.id = f.variavel_id
	and h.valor between f.valorMin and f.valorMax 
	and h.paciente_id = 9
		
-- Pega Indicacao (FaixaValor) de um paciente atraves dos pontos dele
select ff.*
	from faddvm.FaixaValor ff
	where ( 
		select sum(f.peso)
			from faddvm.Historico h, faddvm.Variavel v, faddvm.FaixaValor f
			where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh group by hh.variavel_id)
			and h.variavel_id = v.id
			and v.id = f.variavel_id
			and h.valor between f.valorMin and f.valorMax 
			and h.paciente_id = 9
		) between ff.valorMin and ff.valorMax and ff.variavel_id = 3
		
-- Lista Historico do paciente que estao contandos na pontuacao.
select h.*, f.peso
	from faddvm.Historico h, faddvm.Variavel v, faddvm.FaixaValor f
	where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh group by hh.variavel_id)
	and h.variavel_id = v.id
	and v.id = f.variavel_id
	and h.valor between f.valorMin and f.valorMax 
	and h.paciente_id = 1
	order by f.peso desc		