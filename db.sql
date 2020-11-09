create table userp
(
    id       serial,
    login    varchar,
    password varchar,
    email    varchar,
    salt     varchar,
    constraint user_email unique (email),
    constraint user_pk primary key (id)
);

create table recipe
(
    r_id        serial,
    user_id     int,
    title       varchar,
    description text,
    constraint recipe_pk primary key (r_id),
    constraint userp_fk foreign key (user_id) references userp (id)
        on update cascade on delete set null
);

create table ingredient
(
    i_id  serial,
    title varchar,
    constraint ingredient_pk primary key (i_id),
    constraint ing_title unique (title)
);

create table tag
(
    t_id  serial,
    title varchar,
    constraint tag_pk primary key (t_id),
    constraint tag_title unique (title)
);

create table recipe_tag
(
    r_id int,
    t_id int,
    constraint r_t_pk primary key (r_id, t_id),
    constraint r_fk foreign key (r_id) references recipe (r_id)
        on update cascade on delete cascade,
    constraint t_fk foreign key (t_id) references tag (t_id)
        on update cascade on delete cascade
);

create table userp_recipe
(
    u_id int,
    r_id int,
    constraint u_r_pk primary key (u_id, r_id),
    constraint u_fk foreign key (u_id) references userp (id)
        on update cascade on delete cascade,
    constraint r_fk foreign key (r_id) references recipe (r_id)
        on update cascade on delete cascade
);

create table recipe_ingr
(
  r_id int,
  i_id int,
  amount varchar,
  constraint r_i_pk primary key (r_id, i_id),
  constraint r_fk foreign key (r_id) references recipe (r_id)
      on update cascade on delete cascade,
  constraint i_fk foreign key (i_id) references ingredient (i_id)
      on update cascade on delete cascade
);
