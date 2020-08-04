package ru.rainsoft.camunda.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.rainsoft.camunda.exception.BusinessException;
import ru.rainsoft.camunda.process.service.MainProcessService;
import ru.rainsoft.camunda.process.starter.QuadraticEquationStarter;
import ru.rainsoft.camunda.process.task.SolutionTask;

import java.math.BigDecimal;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {
    private static final String INDEX = "index";
    private static final String DEFAULT_ERROR_MESSAGE = "Request has finished with error";
    private static final String ONE = "1";
    private static final String ZERO = "0";

    @NonNull
    private final QuadraticEquationStarter quadraticEquationStarter;

    @NonNull
    private final MainProcessService mainProcessService;

    @NonNull
    private final SolutionTask solutionTask;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleDefaultException() {
        return new ResponseEntity<>(DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public String index() {
        return INDEX;
    }

    @GetMapping("/calc")
    @ResponseBody
    public String calc(@RequestParam(defaultValue = ONE) BigDecimal argA,
                     @RequestParam(defaultValue = ONE) BigDecimal argB,
                     @RequestParam(defaultValue = ZERO) BigDecimal argC) {
        log.info("start calc of quadratic equation");
        return quadraticEquationStarter.startCalc(argA, argB, argC);
    }

    @GetMapping("/discriminant")
    @ResponseBody
    public String discriminant(@RequestParam String processInstanceId) {
        log.info("get discriminant from process: {}", processInstanceId);
        return mainProcessService.getDiscriminant(processInstanceId);
    }

    @GetMapping("/calc_solution")
    @ResponseBody
    public void calcSolution(@RequestParam String processInstanceId) {
        log.info("start calcSolution from process: {}", processInstanceId);
        solutionTask.calcSolution(processInstanceId);
    }

    @GetMapping("/solution")
    @ResponseBody
    public String solution(@RequestParam String processInstanceId) {
        log.info("get discriminant from process: {}", processInstanceId);
        return mainProcessService.getSolution(processInstanceId);
    }
}
