alter table tasks add column priority_id int references priorities(id);

update tasks set priority_id = (select id from priorities where name = 'urgently');