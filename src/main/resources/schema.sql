DROP TABLE IF EXISTS work_office.employee_cars;
DROP TABLE IF EXISTS work_office.tasks;
DROP TABLE IF EXISTS work_office.employee_project;
DROP TABLE IF EXISTS work_office.employees;
DROP TABLE IF EXISTS work_office.projects;

DROP SCHEMA IF EXISTS work_office;

CREATE SCHEMA work_office AUTHORIZATION postgres;

CREATE TABLE work_office.employees
(
    id              SERIAL,
    first_name      VARCHAR NOT NULL,
    last_name       VARCHAR NOT NULL,
    pesel           VARCHAR NOT NULL,
    CONSTRAINT employees_pk PRIMARY KEY (id)
);

CREATE TABLE work_office.employee_cars
(
    id              SERIAL,
    number          VARCHAR NOT NULL,
    model           VARCHAR NOT NULL,
    employee_id     integer,
    CONSTRAINT employee_cars_pk PRIMARY KEY (id),
    CONSTRAINT employee_cars_employee_fk FOREIGN KEY (employee_id) REFERENCES work_office.employees (id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE work_office.projects
(
    id              SERIAL,
    name            VARCHAR NOT NULL,
    description     VARCHAR NOT NULL,
    CONSTRAINT projects_pk PRIMARY KEY (id)
);

CREATE TABLE work_office.employee_project
(
    id              SERIAL,
    employee_id     integer NOT NULL,
    project_id      integer NOT NULL,
    CONSTRAINT employee_project_pk PRIMARY KEY (id),
    CONSTRAINT employee_project_employee_fk FOREIGN KEY (employee_id) REFERENCES work_office.employees (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT employee_project_project_fk FOREIGN KEY (project_id) REFERENCES work_office.projects (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE work_office.tasks
(
    id              SERIAL,
    name            VARCHAR NOT NULL,
    description     VARCHAR NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    employee_id     integer,
    CONSTRAINT tasks_pk PRIMARY KEY (id),
    CONSTRAINT tasks_employee_fk FOREIGN KEY (employee_id) REFERENCES work_office.employees (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

