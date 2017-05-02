-- password admin1
insert into USER (id, user_name, pass, nivel) values (1, 'admin', '$2a$12$2O2mnHcxLPtao/wVJ3vwo.pdbd13Z9WRuZ/79m8fi1eZJ2TbNPfUW', 'admin');
-- password test1
insert into USER (id, user_name, pass, nivel) values (2, 'test', '$2a$12$t6TyjGcTFwMn9pBk.2rwj.UfJu8lxtoaK8yo05X/lVet/Ja2OfOwq', 'user');
insert into FAV_ACTOR (id, USER_ID, actor_id) values (1, 2, 1);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (2, 2, 2);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (3, 2, 3);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (4, 2, 4);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (5, 2, 5);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (6, 2, 6);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (7, 2, 7);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (8, 2, 8);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (9, 2, 9);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (10, 2, 10);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (11, 2, 11);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (12, 2, 12);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (13, 2, 13);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (14, 2, 14);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (18, 2, 18);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (19, 2, 19);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (20, 2, 20);
insert into FAV_ACTOR (id, USER_ID, actor_id) values (22, 2, 22);


insert into FAV_MOVIE (id, name, USER_ID) values (1, 'Mi primera lista', 2);
insert into FAV_MOVIE (id, name, USER_ID) values (2, 'Mi segunda lista', 2);

insert into FAV_MOVIE (ID,NAME,USER_ID) values ('bac25bb4c3dd', 'Top peliculas 1', 1);
insert into FAV_MOVIE (ID,NAME,USER_ID) values ('838987df153f', 'Peliculas de terror', 1);
insert into FAV_MOVIE (ID,NAME,USER_ID) values ('db67970a4304', 'Las mejores peliculas', 2);
insert into FAV_MOVIE (ID,NAME,USER_ID) values ('5b377bf69c18', 'Otra lista', 2);
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('182e1a2cbb34', 2, 'bac25bb4c3dd');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('b0de755ad68d', 9, 'bac25bb4c3dd');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('c852bd8933e8', 2, '838987df153f');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('5b97ba8c355d', 11, '838987df153f');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('e5f48069cc6c', 6, 'db67970a4304');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('3d60fd0592d8', 2, 'db67970a4304');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('c8faf2e9f111', 3, '5b377bf69c18');
insert into MOVIE (ID,ID_MOVIE,FAVMOVIE_ID) values ('60398be848c0', 2, '5b377bf69c18');




