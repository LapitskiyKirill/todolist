create table "user"
(
	id    bigserial not null
		constraint user_pk
			primary key,
	login varchar   not null,
	hash  varchar   not null
);

alter table "user"
	owner to postgres;

create unique index user_login_uindex
	on "user" (login);

create table token
(
	id      bigserial not null
		constraint token_pk
			primary key,
	user_id bigint    not null
		constraint token_user_id_fk
			references "user",
	value   varchar   not null
);

alter table token
	owner to postgres;

create unique index token_value_uindex
	on token (value);

create table task_category
(
	id      bigserial not null
		constraint task_category_pk
			primary key,
	user_id bigint    not null
		constraint task_category_user_id_fk
			references "user",
	value   varchar   not null
);

alter table task_category
	owner to postgres;

create table task
(
	id          bigserial not null
		constraint task_pk
			primary key,
	user_id     bigint    not null
		constraint task_user_id_fk
			references "user",
	text        varchar   not null,
	deadline    timestamp,
	completed   timestamp,
	category_id bigint
		constraint task_task_category_id_fk
			references task_category
);

alter table task
	owner to postgres;

create table main_task_subtask
(
	main_task_id bigint not null
		constraint linked_task_task_id_fk
			references task,
	subtask_id   bigint not null
		constraint linked_task_task_id_fk_2
			references task
);

alter table main_task_subtask
	owner to postgres;

create table scheduled
(
	id          bigserial not null
		constraint scheduled_pk
			primary key,
	task_id     integer   not null
		constraint scheduled_task_id_fk
			references task,
	"from"      timestamp not null,
	periodicity varchar   not null
);

alter table scheduled
	owner to postgres;

create unique index scheduled_id_uindex
	on scheduled (id);

create unique index scheduled_task_id_uindex
	on scheduled (task_id);

create table scheduled_activity
(
	id           bigserial not null
		constraint scheduled_activity_pk
			primary key,
	scheduled_id bigint    not null
		constraint scheduled_activity_scheduled_id_fk
			references scheduled,
	completed    timestamp
);

alter table scheduled_activity
	owner to postgres;

create unique index scheduled_activity_id_uindex
	on scheduled_activity (id);

