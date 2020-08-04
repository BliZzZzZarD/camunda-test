package ru.rainsoft.camunda.process.task;

import io.vavr.control.Try;
import lombok.NonNull;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import ru.rainsoft.camunda.exception.BusinessException;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.util.Optional;

public abstract class TaskCreator {
    protected final TaskService taskService;

    protected TaskCreator(@NonNull TaskService taskService) {
        this.taskService = taskService;
    }

    public Task getTask(String processInstanceId) {
        return Optional
                .ofNullable(createTask(processInstanceId, taskService))
                .orElseThrow(() -> new BusinessException(ConfigProcess.ERROR_MESSAGE));
    }

    public Task createTask(String processInstanceId, TaskService taskService) {
        return taskService
                .createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }
}
