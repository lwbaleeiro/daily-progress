package br.com.dailyprogress.service.impl;

import br.com.dailyprogress.controller.response.WorkProgressResponse;
import br.com.dailyprogress.entity.WorkProgress;
import br.com.dailyprogress.repository.WorkProgressRepository;
import br.com.dailyprogress.service.WorkProgressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WorkProgressServiceImpl implements WorkProgressService {

    private final WorkProgressRepository workProgressRepository;

    @Override
    public void save(WorkProgress workProgress) {

        log.info(" repository save");

        workProgressRepository.save(workProgress);
    }

    @Override
    public WorkProgressResponse alter(WorkProgress workProgress) {

        log.info(" workProgressRepository alter {}", workProgress);

        try {
            WorkProgress entity = workProgressRepository.findById(workProgress.getId()).orElseThrow(EntityNotFoundException::new);
            
            entity.setDescription(workProgress.getDescription());
            if (workProgress.getDate() != null)
                entity.setDate(workProgress.getDate());

            workProgressRepository.save(entity);
            log.info(" workProgressRepository save new {}", entity);

            return WorkProgressResponse
                    .builder()
                    .id(entity.getId())
                    .description(entity.getDescription())
                    .date(entity.getDate())
                    .build();

        } catch (EntityNotFoundException e) {
            log.error(" WorkProgress not found by id {} ", workProgress.getId());
            throw new EntityNotFoundException("WorkProgress not found by id: " + workProgress.getId());
        }
    }

    @Override
    public List<WorkProgressResponse> findAll() {

        log.info(" workProgressRepository findAll");

        List<WorkProgress> progressList = workProgressRepository.findAll();
        log.info(" workProgressRepository sizer {}", progressList.size());

        return getWorkProgressResponses(progressList);
    }

    @Override
    public WorkProgressResponse findById(Long id) {

        log.info(" workProgressRepository findById {}", id);
        try {
            WorkProgress workProgress = workProgressRepository.findById(id).orElseThrow(EntityNotFoundException::new);

            return WorkProgressResponse
                    .builder()
                    .id(workProgress.getId())
                    .description(workProgress.getDescription())
                    .date(workProgress.getDate())
                    .build();

        } catch (EntityNotFoundException e) {
            log.error(" WorkProgress not found by id {}", id);
            throw new EntityNotFoundException("WorkProgress not found by id: " + id);
        }
    }

    @Override
    public List<WorkProgressResponse> findByDate(LocalDate date) {

        log.info(" workProgressRepository findByDate {}", date);

        List<WorkProgress> progressList = workProgressRepository.findByDate(date).orElseThrow(EntityNotFoundException::new);

        return getWorkProgressResponses(progressList);
    }

    @Override
    public List<WorkProgressResponse> findByDateBetween(LocalDate initDate, LocalDate finalDate) {

        log.info(" workProgressRepository findByDateBetween initDate {} finalDate {}", initDate, finalDate);

        List<WorkProgress> progressList = workProgressRepository.findByDateBetween(initDate, finalDate).orElseThrow(EntityNotFoundException::new);

        return getWorkProgressResponses(progressList);
    }

    /*
        Funções auxiliares
     */

    private List<WorkProgressResponse> getWorkProgressResponses(List<WorkProgress> progressList) {

        log.info(" getWorkProgressResponses");

        List<WorkProgressResponse> workProgressResponseList = new ArrayList<>();
        for (WorkProgress workProgress : progressList) {

            workProgressResponseList.add(
                    WorkProgressResponse
                            .builder()
                            .id(workProgress.getId())
                            .description(workProgress.getDescription())
                            .date(workProgress.getDate())
                            .build());
        }

        return workProgressResponseList;
    }
}
