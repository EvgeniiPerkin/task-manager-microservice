package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.entity.dto.ViewTaskDto;
import ru.lending.microservice.task.manager.repository.ConditionRepository;
import ru.lending.microservice.task.manager.repository.PriorityRepository;
import ru.lending.microservice.task.manager.repository.TaskRepository;
import ru.lending.microservice.task.manager.repository.ThemeRepository;
import ru.lending.microservice.task.manager.repository.ViewTaskRepository;
import ru.lending.microservice.task.manager.servce.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private PriorityRepository priorityRepository;

	@Autowired
	private ConditionRepository conditionRepository;
	
	@Autowired
	private ViewTaskRepository vewTaskRepository;

	@Override
	public Mono<Task> findById(Long id) {
		return taskRepository.findById(id).flatMap(tsk ->
		Mono.just(tsk)
            .zipWith(themeRepository.findById(tsk.getThemeId()))
            .map(t -> t.getT1().setTheme(t.getT2()))
            .zipWith(priorityRepository.findById(tsk.getPriorityId()))
            .map(t -> t.getT1().setPriority(t.getT2()))
            .zipWith(conditionRepository.findById(tsk.getConditionId()))
            .map(t -> t.getT1().setCondition(t.getT2()))
				);
	}
		
	@Override
	public Mono<Void> deleteById(Long id) {
		return taskRepository.deleteById(id);
	}

	@Override
	public Mono<Task> create(TaskDto dto) {
		Task t = Task.builder()
				.createdAt(dto.createdAt())
				.themeId(dto.themeId())
				.priorityId(dto.priorityId())
				.conditionId(dto.conditionId())
				.fromDepartmentId(dto.fromDepartmentId())
				.fromEmployeeId(dto.fromEmployeeId())
				.toDepartmentId(dto.toDepartmentId())
				.toEmployeeId(dto.toEmployeeId())
				.content(dto.content())
				.plannedStartDateTime(dto.plannedStartDateTime())
				.plannedEndDateTime(dto.plannedEndDateTime())
				.actualStartDateTime(dto.actualStartDateTime())
				.actualEndDateTime(dto.actualEndDateTime())
				.build();
		
		return taskRepository.save(t);
	}

	@Override
	public Mono<Task> update(Task entity) {
		return taskRepository.save(entity);
	}
	
	@Override
	public Mono<Page<ViewTask>> findByFilterAndPagination(PageRequest pr, ViewTaskDto dto) {		
		Example<ViewTask> example = Example.of(ViewTask.builder()
				.id(null)
				.createdAt(null)
				.themeId(dto.themeId() == 0 ? null : dto.themeId())
				.themeDesc(null)
				.priorityId(dto.priorityId() == 0 ? null : dto.priorityId())
				.priorityDesc(null)
				.conditionId(dto.conditionId() == 0 ? null : dto.conditionId())
				.conditionDesc(null)
				.fromDepartmentId(dto.fromDepartmentId() == 0 ? null : dto.fromDepartmentId())
				.fromEmployeeId(dto.fromEmployeeId() == 0 ? null : dto.fromEmployeeId())
				.toDepartmentId(dto.toDepartmentId() == 0 ? null : dto.toDepartmentId())
				.toEmployeeId(dto.toEmployeeId() == 0 ? null : dto.toEmployeeId())
				.content(null)
				.plannedStartDateTime(null)
				.plannedEndDateTime(null)
				.actualStartDateTime(null)
				.actualEndDateTime(null)
				.build());
		return vewTaskRepository.findBy(example, p -> p.page(pr.withSort(Sort.by("id").descending())));
	}

}
