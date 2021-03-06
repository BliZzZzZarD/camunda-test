package ru.rainsoft.camunda.process.task.impl;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rainsoft.camunda.process.task.EndProcessTask;
import ru.rainsoft.camunda.process.task.TaskCreator;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
public class EndProcessTaskImpl extends TaskCreator implements EndProcessTask {
    public EndProcessTaskImpl(@Autowired TaskService taskService) {
        super(taskService);
    }

    @Override
    public void endProcess(String processInstanceId) {
        Task solutionTask = getTask(processInstanceId);

        Map<String, Object> variables = Collections.singletonMap(ConfigProcess.END_PROCESS, true);
        taskService.complete(solutionTask.getId(), variables);
    }
}
