BEGIN;

CREATE SCHEMA IF NOT EXISTS tsk AUTHORIZATION dp_user;

CREATE TABLE IF NOT EXISTS tsk.themes (
	id SERIAL PRIMARY KEY,
	department_id INTEGER NOT NULL,
	description VARCHAR(255)
);
COMMENT ON TABLE tsk.themes IS 'Таблица тем задач.';
COMMENT ON COLUMN tsk.themes.id IS 'Идентификатор темы.';
COMMENT ON COLUMN tsk.themes.department_id IS 'Идентификатор отдела.';
COMMENT ON COLUMN tsk.themes.description IS 'Описание темы.';

CREATE TABLE IF NOT EXISTS tsk.priorities (
	id INTEGER NOT NULL PRIMARY KEY,
	description VARCHAR(255)
);
COMMENT ON TABLE tsk.priorities IS 'Таблица приоритетов задач.';
COMMENT ON COLUMN tsk.priorities.id IS 'Идентификатор приоритета.';
COMMENT ON COLUMN tsk.priorities.description IS 'Описание.';

CREATE TABLE IF NOT EXISTS tsk.conditions (
	id INTEGER NOT NULL PRIMARY KEY,
	description VARCHAR(255)
);
COMMENT ON TABLE tsk.conditions IS 'Таблица состояний задач.';
COMMENT ON COLUMN tsk.conditions.id IS 'Идентификатор состояния.';
COMMENT ON COLUMN tsk.conditions.description IS 'Описание.';

CREATE TABLE IF NOT EXISTS tsk.tasks (
	id SERIAL PRIMARY KEY,
	created_at TIMESTAMPTZ NOT NULL,
	theme_id INTEGER NOT NULL,
	priority_id INTEGER NOT NULL,
	condition_id INTEGER NOT NULL,
	from_department_id INTEGER NOT NULL,
	from_employee_id INTEGER NOT NULL,
	to_department_id INTEGER NOT NULL,
	to_employee_id INTEGER,
	content VARCHAR(100),
	planned_start_dt TIMESTAMPTZ NOT NULL,
	planned_end_dt TIMESTAMPTZ NOT NULL,
	actual_start_dt TIMESTAMPTZ,
	actual_end_dt TIMESTAMPTZ,
	FOREIGN KEY (theme_id) REFERENCES tsk.themes(id) on DELETE CASCADE,
	FOREIGN KEY (priority_id) REFERENCES tsk.priorities(id) on DELETE CASCADE,
	FOREIGN KEY (condition_id) REFERENCES tsk.conditions(id) on DELETE CASCADE
);
COMMENT ON TABLE tsk.tasks IS 'Таблица задач.';
COMMENT ON COLUMN tsk.tasks.id IS 'Идентификатор задачи.';
COMMENT ON COLUMN tsk.tasks.created_at IS 'Дата и время создания.';
COMMENT ON COLUMN tsk.tasks.theme_id IS 'Идентификатор темы.';
COMMENT ON COLUMN tsk.tasks.priority_id IS 'Идентификатор приоритета.';
COMMENT ON COLUMN tsk.tasks.condition_id IS 'Идентификатор состояния.';
COMMENT ON COLUMN tsk.tasks.from_department_id IS 'Идентификатор отдела поставившего задачу.';
COMMENT ON COLUMN tsk.tasks.from_employee_id IS 'Идентификатор сотрудника поставившего задачу.';
COMMENT ON COLUMN tsk.tasks.to_department_id IS 'Идентификатор отдела, которому поставлена задача.';
COMMENT ON COLUMN tsk.tasks.to_employee_id IS 'Идентификатор сотрудника, которому поставлена задача.';
COMMENT ON COLUMN tsk.tasks.content IS 'Содержание.';
COMMENT ON COLUMN tsk.tasks.planned_start_dt IS 'Планируемая дата начала выполнения.';
COMMENT ON COLUMN tsk.tasks.planned_end_dt IS 'Планируемая дата завершения.';
COMMENT ON COLUMN tsk.tasks.actual_start_dt IS 'Актуальная дата начала выполнения.';
COMMENT ON COLUMN tsk.tasks.actual_end_dt IS 'Актуальная дата завершения.';

CREATE TABLE IF NOT EXISTS tsk.tasks_loan_parameters (
	task_id INTEGER NOT NULL,
	loan_parameter_id INTEGER NOT NULL,
	FOREIGN KEY (task_id) REFERENCES tsk.tasks(id) on DELETE CASCADE,
	PRIMARY KEY (task_id, loan_parameter_id)
);
COMMENT ON TABLE tsk.tasks_loan_parameters IS 'Таблица отношения задач к параметрам кредита.';
COMMENT ON COLUMN tsk.tasks_loan_parameters.loan_parameter_id IS 'Идентификатор параметров кредита.';
COMMENT ON COLUMN tsk.tasks_loan_parameters.task_id IS 'Идентификатор задачи.';

CREATE TABLE IF NOT EXISTS tsk.loan_parameters_clients (
	client_id INTEGER NOT NULL,
	task_id INTEGER NOT NULL,
	loan_parameter_id INTEGER NOT NULL,
	FOREIGN KEY (task_id, loan_parameter_id) REFERENCES tsk.tasks_loan_parameters(task_id, loan_parameter_id) on DELETE CASCADE,
	PRIMARY KEY (client_id, task_id, loan_parameter_id)
);
COMMENT ON TABLE tsk.loan_parameters_clients IS 'Таблица отношения параметров кредита к клиентам.';
COMMENT ON COLUMN tsk.loan_parameters_clients.client_id IS 'Идентификатор клиента.';
COMMENT ON COLUMN tsk.loan_parameters_clients.task_id IS 'Идентификатор задачи.';
COMMENT ON COLUMN tsk.loan_parameters_clients.loan_parameter_id IS 'Идентификатор параметров кредита.';

CREATE TABLE IF NOT EXISTS tsk.loan_parameters_collaterals (
	collateral_id INTEGER NOT NULL,
	task_id INTEGER NOT NULL,
	loan_parameter_id INTEGER NOT NULL,
	FOREIGN KEY (task_id, loan_parameter_id) REFERENCES tsk.tasks_loan_parameters(task_id, loan_parameter_id) on DELETE CASCADE,
	PRIMARY KEY (collateral_id, task_id, loan_parameter_id)
);
COMMENT ON TABLE tsk.loan_parameters_collaterals IS 'Таблица отношения задач к обеспечениям.';
COMMENT ON COLUMN tsk.loan_parameters_collaterals.collateral_id IS 'Идентификатор обеспечения.';
COMMENT ON COLUMN tsk.loan_parameters_collaterals.task_id IS 'Идентификатор задачи.';
COMMENT ON COLUMN tsk.loan_parameters_collaterals.loan_parameter_id IS 'Идентификатор параметров кредита.';

INSERT INTO tsk.priorities
	VALUES (1, 'Низкий'), (2, 'Средний'), (3, 'Высокий');
INSERT INTO tsk.conditions
	VALUES (1, 'К выполнению'), (2, 'В работе'), (3, 'Выполнено'), (4, 'Отменена'), (5, 'Отклонена');

END;