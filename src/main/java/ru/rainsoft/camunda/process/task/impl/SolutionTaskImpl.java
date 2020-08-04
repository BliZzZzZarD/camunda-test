package ru.rainsoft.camunda.process.task.impl;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rainsoft.camunda.process.task.SolutionTask;
import ru.rainsoft.camunda.process.task.TaskCreator;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
public class SolutionTaskImpl extends TaskCreator implements SolutionTask {
    protected SolutionTaskImpl(@Autowired TaskService taskService) {
        super(taskService);
    }

    @Override
    public void calcSolution(String processInstanceId) {
        Task solutionTask = getTask(processInstanceId);

        Map<String, Object> variables = Collections.singletonMap(ConfigProcess.NEED_CALC, true);
        taskService.complete(solutionTask.getId(), variables);
    }
}
