package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.kejam.domain.Applicant;
import ru.kejam.repository.ApplicantRepository;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
public class SigningDelegate implements JavaDelegate {
    private final ApplicantRepository applicantRepository;

    public SigningDelegate(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long id = (Long) execution.getVariable("id");
        final Applicant applicant = applicantRepository.getById(id);
        applicant.setDateSigning(Date.valueOf(LocalDate.now()));
        applicantRepository.save(applicant);
        log.info("Сделка успешно подписана! {}", id);
    }
}
