create table if not exists "user"
(
	id bigserial not null
		constraint user_pk
			primary key,
	login varchar not null,
	password varchar not null
);

alter table "user" owner to postgres;

create unique index if not exists user_login_uindex
	on "user" (login);

create table if not exists task
(
	id bigserial not null
		constraint task_pk
			primary key,
	user_id bigint not null
		constraint task_user_id_fk
			references "user",
	category varchar not null,
	text varchar not null,
	deadline timestamp,
	completed boolean
);

alter table task owner to postgres;

create table if not exists token
(
	id bigserial not null
		constraint token_pk
			primary key,
	user_id bigint not null
		constraint token_user_id_fk
			references "user",
	value varchar not null
);

alter table token owner to postgres;

create unique index if not exists token_value_uindex
	on token (value);

create table if not exists linked_task
(
	main_task_id bigint not null
		constraint linked_task_task_id_fk
			references task,
	subtask_id bigint not null
		constraint linked_task_task_id_fk_2
			references task
);

alter table linked_task owner to postgres;
