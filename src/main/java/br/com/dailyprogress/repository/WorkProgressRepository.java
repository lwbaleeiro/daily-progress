package br.com.dailyprogress.repository;

import br.com.dailyprogress.entity.WorkProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkProgressRepository extends JpaRepository<WorkProgress, Long> {

    Optional<List<WorkProgress>> findByDate(LocalDate date);
    Optional<List<WorkProgress>> findByDateBetween(LocalDate initDate, LocalDate finalDate);
}
