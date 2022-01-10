package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckEDSDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        final Long clinetedscode = Long.valueOf((String) execution.getVariable("clinetedscode"));
        log.info("Проверка подлниости ЭЦП {}", id);
        if (clinetedscode > 13) {
            log.info("ЭЦП не прошла проверку подлинности {}", id);
            execution.setVariable("clinetvalidationresult", "false");
        } else {
            log.info("ЭЦП прошла проверку подлинности {}", id);
            execution.setVariable("clinetvalidationresult", "true");
        }
    }
}
