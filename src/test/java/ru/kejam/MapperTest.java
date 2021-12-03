package ru.kejam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kejam.domain.Applicant;
import ru.kejam.repository.ApplicantRepository;
import ru.kejam.service.ApplicantMapper;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class MapperTest {
    @Autowired
    private ApplicantMapper applicantMapper;
    @Autowired
    private ApplicantRepository applicantRepository;
    private String value = "{\n" +
            "  \"date\": \"2021-10-10\",\n" +
            "  \"clientId\": \"1\",\n" +
            "  \"amount\": \"2000000\",\n" +
            "  \"corrId\": \"1\",\n" +
            "  \"sellerId\": \"1\",\n" +
            "  \"objectId\": \"1\",\n" +
            "  \"address\": \"Moscow\",\n" +
            "  \"typeObject\": \"Flat\",\n" +
            "  \"bankName\": \"VTB\",\n" +
            "  \"codeClient\": \"12\",\n" +
            "  \"fName\": \"Ivan\",\n" +
            "  \"sName\": \"Ivanov\",\n" +
            "  \"oName\": \"Ivanovich\",\n" +
            "  \"sex\": \"m\",\n" +
            "  \"dateBirth\": \"1990-10-10\",\n" +
            "  \"phone\": \"+7-999-999-99-99\",\n" +
            "  \"email\": \"test@test.com\",\n" +
            "  \"civil\": \"russia\",\n" +
            "  \"snils\": \"123-123-123-12\",\n" +
            "  \"inn\": \"123456789\",\n" +
            "  \"nPass\": \"4545\",\n" +
            "  \"sPass\": \"111222\",\n" +
            "  \"typeSeller\": \"owner\"\n" +
            "}";


    @Test
    public void testDB() {
        final Applicant applicant = applicantMapper.map(value);
        assertEquals("m",applicant.getSex());
        final Applicant applicant1 = applicantRepository.save(applicant);
        assertNotNull(applicant1.getId());
    }

    @Test
    public void testMapper() {
        final Applicant applicant = applicantMapper.map(value);
        assertEquals("m",applicant.getSex());
    }
}
