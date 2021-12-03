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
public class FrodCheckOwnerDelegate implements JavaDelegate {
    private final boolean isFrod;
    private final ApplicantRepository applicantRepository;

    public FrodCheckOwnerDelegate(@Value("${test.frodowner}") boolean isFrod,
                                  ApplicantRepository applicantRepository) {
        this.isFrod = isFrod;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        final Applicant applicant = applicantRepository.findById(id).get();
        if (isFrod) {
            log.info("Обнаружено подозрение в собственнике! {}", id);
            execution.setVariable("frod", "true");
            applicant.setFrodBuyer(true);
            applicantRepository.save(applicant);
        } else {
            log.info("Заявка обработана успешно, собственик юридически чист! {}", id);
        }
    }
}
