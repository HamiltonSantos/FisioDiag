-- Admin
insert into faddvm.Fisioterapeuta (id,login,nome,senha) values (1,"admin","admin","admin");

-- Categorias
insert into faddvm.Categoria (id,descricao,status) values (1,"ocorrencia",'A');
insert into faddvm.Categoria (id,descricao,status) values (2,"intercorrencia",'A');
insert into faddvm.Categoria (id,descricao,status) values (3,"indice",'A');

-- Variaveis
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (1,"ocorrencia",'A','O',1);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (2,"intercorrencia",'A','O',2);
insert into faddvm.Variavel (id,descricao,status,tipo,categoria_id) values (3,"indice",'A','O',3);