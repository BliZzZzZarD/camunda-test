package ru.rainsoft.camunda.process.utils;

import java.math.BigDecimal;

public abstract class ConfigProcess {
    public static final String PROCESS_DEFINITION_KEY = "camunda-test-process";
    public static final String ARG_A = "argA";
    public static final String ARG_B = "argB";
    public static final String ARG_C = "argC";
    public static final String DISCRIMINANT = "discriminant";
    public static final String CALCULATION_NOT_YET_COMPLETED = "Расчет еще не закончен";
    public static final String NEED_CALC = "needCalc";
    public static final String SOLUTION = "solution";
    public static final String END_PROCESS = "endProcess";
    public static final String ERROR_MESSAGE = "Произошла ошибка при получеии процесса, либо процесс уже завершен по таймауту. Запустите расчет заново";

    public static final BigDecimal TWO = new BigDecimal(2);
}
