delete from work_office.employee_cars;
delete from work_office.employee_project;
delete from work_office.tasks;
delete from work_office.employees;
delete from work_office.projects;

insert into work_office.employees
    (first_name, last_name, pesel)
VALUES
    ('first_name1', 'last_name1', '00251115897'),
    ('first_name2', 'last_name2', '00251115898'),
    ('first_name3', 'last_name3', '00251115899'),
    ('first_name4', 'last_name4', '00251115900');

insert into work_office.projects
    (name, description)
VALUES
    ('project1', 'project1_desc'),
    ('project2', 'project2_desc');

insert into work_office.tasks
    (name, description, employee_id)
VALUES
    ('taks1', 'taks1_desc', 1),
    ('taks2', 'taks2_desc', 2),
    ('taks3', 'taks3_desc', NULL),
    ('taks4', 'taks4_desc', 1);

insert into work_office.employee_cars
    (number, model, employee_id)
VALUES
    ('number1', 'model1', 3),
    ('number2', 'model2', NULL),
    ('number3', 'model3', 1);

insert into work_office.employee_project
    (employee_id, project_id)
VALUES
    (1, 1),
    (1, 2),
    (4, 2);

insert into work_office.users
    (login, password)
VALUES
    ('1', '$2a$10$zc86pQ6ujG0MZKaaS1EY6uWb.Zmx6SGmRhwU6et9AeXsY5Bd8UKP.'), -- password '111'
    ('2', '$2a$10$6aJFNP1VyKwMaSBXJoJgG.Mwar1iclX7hse0e09eAElaA1XFG1F1q'), -- password '222'
    ('3', '$2a$10$4zh5WbznTQMQruMIpxZtce5Lm4.DJcGSgwMdyDyo.f1NfNNQpKg/e'); -- password '333'
