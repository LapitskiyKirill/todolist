create schema public;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

create table if not exists "user"
(
	id bigserial not null
		constraint user_pk
			primary key,
	login varchar not null,
	hash varchar not null
);

alter table "user" owner to postgres;

create unique index if not exists user_login_uindex
	on "user" (login);

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

create table if not exists task_category
(
	id bigserial not null
		constraint task_category_pk
			primary key,
	user_id bigint not null
		constraint task_category_user_id_fk
			references "user",
	value varchar not null
);

alter table task_category owner to postgres;

create table if not exists task
(
	id bigserial not null
		constraint task_pk
			primary key,
	user_id bigint not null
		constraint task_user_id_fk
			references "user",
	text varchar not null,
	deadline timestamp,
	completed timestamp,
	category_id bigint
		constraint task_task_category_id_fk
			references task_category
);

alter table task owner to postgres;

create table if not exists main_task_subtask
(
	main_task_id bigint not null
		constraint linked_task_task_id_fk
			references task,
	subtask_id bigint not null
		constraint linked_task_task_id_fk_2
			references task
);

alter table main_task_subtask owner to postgres;

