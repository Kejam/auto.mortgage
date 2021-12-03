package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kejam.domain.Applicant;
import ru.kejam.repository.ApplicantRepository;

@Service
@Slf4j
public class FrodCheckObjectDelegate implements JavaDelegate {
    private final boolean isFrod;
    private final ApplicantRepository applicantRepository;

    public FrodCheckObjectDelegate(@Value("${test.frodobject}") boolean isFrod,
                                   ApplicantRepository applicantRepository) {
        this.isFrod = isFrod;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        final Applicant applicant = applicantRepository.findById(id).get();
        if (isFrod) {
            log.info("Обнаружено подозрение в объекте кредитования! {}", id);
            execution.setVariable("frod", "true");
            applicant.setFrodBuyer(true);
            applicantRepository.save(applicant);
        } else {
            log.info("Заявка обработана успешно, объект юридически чист! {}", id);
        }
    }
}
