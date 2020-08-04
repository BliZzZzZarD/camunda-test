package ru.rainsoft.camunda.process.service;

public interface MainProcessService {
    String getDiscriminant(String processInstanceId);

    String getSolution(String processInstanceId);
}
