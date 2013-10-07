select 
    sum(h.valor)
from
    faddvm.Historico as h
        inner join
    (select 
        max(data) as ultimaData
    from
        faddvm.Historico as hh
    where
        hh.paciente_id = 4
    group by hh.variavel_id) hhh ON h.data = hhh.ultimaData