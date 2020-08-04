package ru.rainsoft.camunda.process.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.math.BigDecimal;

@Slf4j
@Component("DiscriminantCalculation")
public class DiscriminantCalculationDelegate implements JavaDelegate {
    private static final BigDecimal FOUR = new BigDecimal(4);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("start calc of discriminant");
        BigDecimal argA = new BigDecimal(delegateExecution.getVariable(ConfigProcess.ARG_A).toString());
        BigDecimal argB = new BigDecimal(delegateExecution.getVariable(ConfigProcess.ARG_B).toString());
        BigDecimal argC = new BigDecimal(delegateExecution.getVariable(ConfigProcess.ARG_C).toString());

        BigDecimal discriminant = getDiscriminant(argA, argB, argC);
        log.info("Discriminant is calculated: {}", discriminant);

        delegateExecution.setVariable(ConfigProcess.DISCRIMINANT, discriminant);
    }

    private BigDecimal getDiscriminant(BigDecimal argA, BigDecimal argB, BigDecimal argC) {
        return argB.pow(2).subtract(FOUR.multiply(argA).multiply(argC));
    }
}

