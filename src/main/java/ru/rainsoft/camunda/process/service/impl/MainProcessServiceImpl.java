package ru.rainsoft.camunda.process.service.impl;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import ru.rainsoft.camunda.exception.BusinessException;
import ru.rainsoft.camunda.process.service.MainProcessService;
import ru.rainsoft.camunda.process.task.EndProcessTask;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MainProcessServiceImpl implements MainProcessService {
    @NonNull
    private final RuntimeService runtimeService;

    @NonNull
    private final EndProcessTask endProcessTask;

    @Override
    public String getDiscriminant(String processInstanceId) {
        return Optional
                .ofNullable(runtimeService.getVariable(processInstanceId, ConfigProcess.DISCRIMINANT).toString())
                .orElse(ConfigProcess.CALCULATION_NOT_YET_COMPLETED);
    }

    @Override
    public String getSolution(String processInstanceId) {
        String solution = Optional
                .ofNullable(getSolutionFromRuntime(processInstanceId))
                .orElse(ConfigProcess.CALCULATION_NOT_YET_COMPLETED);

        if (!ConfigProcess.CALCULATION_NOT_YET_COMPLETED.equals(solution)) {
            endProcessTask.endProcess(processInstanceId);
        }

        return solution;
    }

    private String getSolutionFromRuntime(String processInstanceId) {
        return Try
                .of(() -> runtimeService.getVariable(processInstanceId, ConfigProcess.SOLUTION).toString())
                .getOrElseThrow(() -> new BusinessException(ConfigProcess.ERROR_MESSAGE));
    }
}
