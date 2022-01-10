package ru.kejam.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import ru.kejam.domain.Applicant;
import ru.kejam.repository.ApplicantRepository;
import ru.kejam.service.ApplicantMapper;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
public class GosuslusgiApplicantDelegate implements JavaDelegate {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;


    public GosuslusgiApplicantDelegate(ApplicantRepository applicantRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Старт обработки входящей заявки, обогащение с помощью Госуслуг");
        final String applicantString = String.valueOf(GosuslusgiApplicantDelegate.class.getClassLoader().getResourceAsStream("applicant.json"));
        final Applicant applicant = applicantMapper.map(applicantString);
        log.info("Заявка успешна обработана {}", applicant.getId());
        uploadDocuments(applicant);
        execution.setVariable("id", applicant.getId());
        execution.setVariable("frod", "false");
    }

    private void uploadDocuments(Applicant applicant) {
        applicant.setDApplicant("applicant".getBytes(StandardCharsets.UTF_8));
        applicant.setDKadastrPass("kadastr".getBytes(StandardCharsets.UTF_8));
        applicant.setDOwnerOrder("ownerorder".getBytes(StandardCharsets.UTF_8));
        applicant.setDVipiskaDomovoi("vipiska".getBytes(StandardCharsets.UTF_8));
        applicant.setDTechPass("techpass".getBytes(StandardCharsets.UTF_8));
        applicant.setDPriceOrder("priceorder".getBytes(StandardCharsets.UTF_8));
        applicant.setDNdfl("ndfl".getBytes(StandardCharsets.UTF_8));
        applicant.setDWork("work".getBytes(StandardCharsets.UTF_8));
        applicant.setAddDate(LocalDate.now().toString());
        applicant.setDateApplicant(Date.valueOf(LocalDate.now()));
        applicantRepository.save(applicant);
    }
}
