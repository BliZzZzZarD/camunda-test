package ru.rainsoft.camunda.process.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

@Slf4j
@Component("NoSolution")
public class NoSolutionDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("start NoSolutionDelegate");
        delegateExecution.setVariable(ConfigProcess.SOLUTION, "Уравнение не имеет корней");
    }
}

