package br.com.dailyprogress.service;

import br.com.dailyprogress.controller.response.WorkProgressResponse;
import br.com.dailyprogress.entity.WorkProgress;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface WorkProgressService {
    void save(WorkProgress workProgress);
    List<WorkProgressResponse> findAll();
    WorkProgressResponse findById(Long id);
    List<WorkProgressResponse> findByDate(LocalDate date);
    List<WorkProgressResponse> findByDateBetween(LocalDate initDate, LocalDate finalDate);
    WorkProgressResponse alter(WorkProgress workProgress);
}
