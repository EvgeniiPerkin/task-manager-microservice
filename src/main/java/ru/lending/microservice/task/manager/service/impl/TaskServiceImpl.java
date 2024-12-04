package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.FilterTaskDto;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.repository.ClientRepository;
import ru.lending.microservice.task.manager.repository.CollateralRepository;
import ru.lending.microservice.task.manager.repository.ConditionRepository;
import ru.lending.microservice.task.manager.repository.LoanParameterRepository;
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

  @Autowired
  private LoanParameterRepository loanRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private CollateralRepository collateralRepository;

  @Override
  public Mono<Task> findById(Long id) {		
    return taskRepository.findById(id).flatMap(tsk -> Mono.just(tsk)
      .zipWith(themeRepository.findById(tsk.getThemeId()))
      .map(t -> t.getT1().setTheme(t.getT2()))
      .zipWith(priorityRepository.findById(tsk.getPriorityId()))
      .map(t -> t.getT1().setPriority(t.getT2()))
      .zipWith(conditionRepository.findById(tsk.getConditionId()))
      .map(t -> t.getT1().setCondition(t.getT2()))
      .zipWith(loanRepository.findByTaskId(tsk.getId()).flatMap(lp -> Mono.just(lp)
        .zipWith(clientRepository.findByTaskIdAndLoanParameterId(lp.getTaskId(), lp.getLoanParameterId()).collectList())
        .map(t -> t.getT1().setClients(t.getT2()))
        .zipWith(collateralRepository.findByTaskIdAndLoanParameterId(lp.getTaskId(), lp.getLoanParameterId()).collectList())
        .map(t -> t.getT1().setCollaterals(t.getT2()))))
      .map(t -> t.getT1().setLoanParameter(t.getT2())));
  }

  @Override
  public Mono<Void> deleteById(Long id) {
    return taskRepository.deleteById(id);
  }

  @Override
  public Mono<Task> create(@Valid Mono<TaskDto> dto) {
    return dto
      .flatMap(d -> {
        Task t = Task.builder()
          .createdAt(d.createdAt())
          .themeId(d.themeId())
          .priorityId(d.priorityId())
          .conditionId(d.conditionId())
          .fromDepartmentId(d.fromDepartmentId())
          .fromEmployeeId(d.fromEmployeeId())
          .toDepartmentId(d.toDepartmentId())
          .toEmployeeId(d.toEmployeeId())
          .title(d.title())
          .content(d.content())
          .plannedStartDateTime(d.plannedStartDateTime())
          .plannedEndDateTime(d.plannedEndDateTime())
          .actualStartDateTime(null)
          .actualEndDateTime(null)
          .build();
        return taskRepository.save(t);
      });
  }

  @Override
  public Mono<Task> update(Task entity) {
    return taskRepository.save(entity);
  }

  @Override
  public Mono<Page<ViewTask>> findByFilterAndPagination(PageRequest pr, FilterTaskDto dto) {		
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
      .title(null)
      .plannedStartDateTime(null)
      .plannedEndDateTime(null)
      .actualStartDateTime(null)
      .actualEndDateTime(null)
      .loanParameterId(null)
      .build());
    return vewTaskRepository.findBy(example, p -> p.page(pr.withSort(Sort.by("id").descending())));
  }
}
