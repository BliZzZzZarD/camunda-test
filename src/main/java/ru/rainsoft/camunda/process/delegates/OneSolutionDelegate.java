package ru.rainsoft.camunda.process.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component("OneSolution")
public class OneSolutionDelegate implements JavaDelegate {
    private static final String SOLUTION_VALUE_FORMAT = "Уровнение имеет один корень: %s";

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("start OneSolutionDelegate");
        delegateExecution.setVariable(ConfigProcess.SOLUTION, getSolution(delegateExecution));
    }

    private String getSolution(DelegateExecution delegateExecution) {
        log.info("start getSolution for OneSolutionDelegate");
        BigDecimal discriminant = getVariable(delegateExecution, ConfigProcess.DISCRIMINANT);
        BigDecimal sqrtDiscriminant = BigDecimal.valueOf(Math.sqrt(Double.parseDouble(discriminant.toString())));

        BigDecimal argA = getVariable(delegateExecution, ConfigProcess.ARG_A);
        BigDecimal argB = getVariable(delegateExecution, ConfigProcess.ARG_B);

        BigDecimal solution = argB.negate().add(sqrtDiscriminant).divide(ConfigProcess.TWO.multiply(argA), 2, RoundingMode.DOWN);
        log.info("solution: {}", solution);

        return String.format(SOLUTION_VALUE_FORMAT, solution);
    }

    private BigDecimal getVariable(DelegateExecution delegateExecution, String name) {
        return new BigDecimal(delegateExecution.getVariable(name).toString());
    }
}

