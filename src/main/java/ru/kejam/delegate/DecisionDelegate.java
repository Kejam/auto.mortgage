package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kejam.domain.Applicant;
import ru.kejam.repository.ApplicantRepository;

import java.util.Optional;

@Service
@Slf4j
public class DecisionDelegate implements JavaDelegate {
    private final boolean decision;
    private final ApplicantRepository repository;

    public DecisionDelegate(@Value("${test.bankdecision}") boolean decision, ApplicantRepository repository) {
        this.decision = decision;
        this.repository = repository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        final Applicant applicant = repository.findById(id).get();
        if (decision) {
            log.info("Заявка обработана успешно, принято положительное решение! {}", id);
            execution.setVariable("bankdecision", "true");
            applicant.setDecision("true");
            repository.save(applicant);
        } else {
            log.info("Заявка обработана успешно, принято отрицательное решение! {}", id);
            execution.setVariable("bankdecision", "false");
            applicant.setDecision("false");
            repository.save(applicant);
        }
    }
}
