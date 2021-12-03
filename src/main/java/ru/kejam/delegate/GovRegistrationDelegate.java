package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GovRegistrationDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        log.info("Сделка успешно зарегистрирована в Росреестре! {}", id);
    }
}
