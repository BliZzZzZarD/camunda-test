package ru.rainsoft.camunda.process.starter.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import ru.rainsoft.camunda.process.starter.QuadraticEquationStarter;
import ru.rainsoft.camunda.process.utils.ConfigProcess;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuadraticEquationStarterImpl implements QuadraticEquationStarter {
    @NonNull
    private final RuntimeService runtimeService;

    @Override
    public String startCalc(BigDecimal argA, BigDecimal argB, BigDecimal argC) {
        log.info("start calc for argA: {}, argB: {}, argC: {}", argA, argB, argC);
        return runtimeService.createProcessInstanceByKey(ConfigProcess.PROCESS_DEFINITION_KEY)
                .businessKey(getProcessKey(argA, argB, argC))
                .setVariable(ConfigProcess.ARG_A, argA)
                .setVariable(ConfigProcess.ARG_B, argB)
                .setVariable(ConfigProcess.ARG_C, argC)
                .execute()
                .getProcessInstanceId();
    }

    public String getProcessKey(BigDecimal argA, BigDecimal argB, BigDecimal argC) {
        return argA
                .toString()
                .concat(argB.toString())
                .concat(argC.toString());
    }
}
