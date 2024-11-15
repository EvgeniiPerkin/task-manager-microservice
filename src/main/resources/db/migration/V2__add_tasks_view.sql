BEGIN;

CREATE OR REPLACE VIEW tsk.view_tasks AS
SELECT
	t.id,
	t.created_at,
	t.theme_id,
	th.description as theme_desc,
	t.condition_id,
	c.description as condition_desc,
	t.priority_id,
	p.description as priority_desc,
	t.from_department_id,
	t.from_employee_id,
	t.to_department_id,
	t.to_employee_id,
	t.content,
	t.planned_start_dt,
	t.planned_end_dt,
	t.actual_start_dt,
	t.actual_end_dt
FROM tsk.tasks t 
	INNER JOIN tsk.conditions c ON t.condition_id = c.id
	INNER JOIN tsk.priorities p ON t.priority_id = p.id
	INNER JOIN tsk.themes th ON t.theme_id = th.id;

END;