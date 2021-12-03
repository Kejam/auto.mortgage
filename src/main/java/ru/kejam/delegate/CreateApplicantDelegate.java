package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.kejam.repository.ApplicantRepository;
import ru.kejam.domain.Applicant;
import ru.kejam.service.ApplicantMapper;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
public class CreateApplicantDelegate implements JavaDelegate {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    public CreateApplicantDelegate(ApplicantRepository applicantRepository,
                                   ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Старт обработки входящей заявки");
        final String strApplicant = (String) delegateExecution.getVariable("applicant");
        log.info("Входящая заявка: {}", strApplicant);
        final Applicant applicant = applicantMapper.map(strApplicant);
        applicantRepository.save(applicant);
        log.info("Заявка успешна обработана {}", applicant.getId());
        uploadDocuments(applicant);
        delegateExecution.setVariable("id", applicant.getId());
        delegateExecution.setVariable("frod", "false");
    }

    private void uploadDocuments(Applicant applicant) {
        applicant.setDApplicant("applicant".getBytes(StandardCharsets.UTF_8));
        applicant.setDKadastrPass("kadast".getBytes(StandardCharsets.UTF_8));
        applicant.setDOwnerOrder("ownerorder".getBytes(StandardCharsets.UTF_8));
        applicant.setDVipiskaDomovoi("vipiska".getBytes(StandardCharsets.UTF_8));
        applicant.setDTechPass("techpass".getBytes(StandardCharsets.UTF_8));
        applicant.setDPriceOrder("priceorder".getBytes(StandardCharsets.UTF_8));
        applicant.setDNdfl("ndfl".getBytes(StandardCharsets.UTF_8));
        applicant.setDWork("work".getBytes(StandardCharsets.UTF_8));
        applicant.setAddDate(LocalDate.now().toString());
        applicant.setDateApplicant(Date.valueOf(LocalDate.now()));
        applicantRepository.save(applicant);
        log.info("Загружены документы по заявке {}", applicant.getId());
    }

}
