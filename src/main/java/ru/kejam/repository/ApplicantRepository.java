package ru.kejam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kejam.domain.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}