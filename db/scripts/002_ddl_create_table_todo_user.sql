CREATE table if not exists todo_user
(
    id       SERIAL PRIMARY KEY,
    name     TEXT unique not null ,
    login    TEXT unique not null ,
    password TEXT not null
)