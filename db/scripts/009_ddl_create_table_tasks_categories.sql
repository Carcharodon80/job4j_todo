CREATE TABLE IF NOT EXISTS tasks_categories
(
    task_id     int references tasks (id),
    category_id int references categories (id)
);