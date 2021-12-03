package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InsuranceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        log.info("По сделке успешно оформлена страхование жизни и объекта собственности! {}", id);
    }
}
