insert into pauta(id, nome) values
(1, "PRESTAÇÃO DE CONTAS DE PARTIDO POLÍTICO"),
(2, "PRESTAÇÃO DE CONTAS DE ÓRGÃO DE DIREÇÃO REGIONAL"),
(3, "PEDIDO DE PROVIDÊNCIAS"),
(4, "RECURSO CRIMINAL - FALSIDADE IDEOLÓGICA"),
(5, "RECURSO ELEITORAL - CAPTAÇÃO OU GASTO ILÍCITO DE RECURSOS FINANCEIROS DE CAMPANHA ELEITORAL"),
(6, "ABUSO DE PODER ECONÔMICO - PEDIDO DE PROVIDÊNCIAS");

insert into voto(id, cpf, escolha, pauta_id) values
(1, "05081581097", true, 1),
(2, "09892608011", true, 1),
(3, "17112500079", true, 2),
(4, "58588804018", true, 2),
(5, "72762539021", true, 3),
(6, "31990931081", true, 3),
(7, "92035641012", true, 4),
(8, "41455998028", true, 4),
(9, "20346300096", true, 5),
(10, "13060640076", true, 5);

insert into sessao(id, data_inicio, minutos, pauta_id) values
(1,  DATE_ADD(now(), INTERVAL -1 DAY), 120, 1),
(2,  DATE_ADD(now(), INTERVAL -2 DAY), 150, 2),
(3,  DATE_ADD(now(), INTERVAL -3 DAY), 60, 3),
(4,  DATE_ADD(now(), INTERVAL -4 DAY), 120, 4),
(5,  DATE_ADD(now(), INTERVAL -5 DAY), 180, 5),
(6,  DATE_ADD(now(), INTERVAL -6 DAY), 60, 6);




