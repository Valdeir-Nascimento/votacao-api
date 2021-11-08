insert into sessao(id, data_inicio, minutos, pauta_id) values
(1,  DATE_ADD(now(), INTERVAL -1 DAY), 120, 1),
(2,  DATE_ADD(now(), INTERVAL -2 DAY), 150, 2),
(3,  DATE_ADD(now(), INTERVAL -3 DAY), 60, 3),
(4,  DATE_ADD(now(), INTERVAL -4 DAY), 120, 4),
(5,  DATE_ADD(now(), INTERVAL -5 DAY), 180, 5),
(6,  DATE_ADD(now(), INTERVAL -6 DAY), 60, 6);
