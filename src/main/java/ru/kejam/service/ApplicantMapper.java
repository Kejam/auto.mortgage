package ru.kejam.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kejam.domain.Applicant;

@Service
@Slf4j
public class ApplicantMapper {
    private final ObjectMapper objectMapper;

    public ApplicantMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public Applicant map(String value) {
        return objectMapper.readValue(value, Applicant.class);
    }
}
