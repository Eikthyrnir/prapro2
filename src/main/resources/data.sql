delete from work_office.employee_cars;
delete from work_office.employee_project;
delete from work_office.tasks;
delete from work_office.employees;
delete from work_office.projects;

insert into work_office.employees
    (id, first_name, last_name, pesel)
VALUES
    (1, 'first_name1', 'last_name1', '00251115897'),
    (2, 'first_name2', 'last_name2', '00251115898'),
    (3, 'first_name3', 'last_name3', '00251115899'),
    (4, 'first_name4', 'last_name4', '00251115900');

insert into work_office.projects
    (id, name, description)
VALUES
    (1, 'project1', 'project1_desc'),
    (2, 'project2', 'project2_desc');

insert into work_office.tasks
    (id, name, description, employee_id)
VALUES
    (1, 'taks1', 'taks1_desc', 1),
    (2, 'taks2', 'taks2_desc', 2),
    (3, 'taks3', 'taks3_desc', NULL),
    (4, 'taks4', 'taks4_desc', 1);

insert into work_office.employee_cars
    (id, number, model, employee_id)
VALUES
    (1, 'number1', 'model1', 3),
    (2, 'number2', 'model2', NULL),
    (3, 'number3', 'model3', 1);

insert into work_office.employee_project
    (id, employee_id, project_id)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 4, 2);