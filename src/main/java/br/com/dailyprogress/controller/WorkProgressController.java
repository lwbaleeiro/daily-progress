package br.com.dailyprogress.controller;


import br.com.dailyprogress.controller.form.WorkProgressForm;
import br.com.dailyprogress.controller.response.WorkProgressResponse;
import br.com.dailyprogress.entity.WorkProgress;
import br.com.dailyprogress.service.WorkProgressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/work-progress")
@AllArgsConstructor
@Slf4j
public class WorkProgressController {

    private final WorkProgressService workProgressService;

    @PostMapping("/create")
    public void create(final @RequestBody WorkProgressForm workProgressForm) {

        log.info(" create {} ", workProgressForm);

        if (workProgressForm.getDate() == null) {
            log.info(" create set date {} ", LocalDate.now());
            workProgressForm.setDate(LocalDate.now());
        }

        WorkProgress workProgress = WorkProgress
                .builder()
                .description(workProgressForm.getDescription())
                .date(workProgressForm.getDate())
                .build();

        workProgressService.save(workProgress);
    }

    @PutMapping("/alter/{id}")
    public WorkProgressResponse alter(final @RequestBody WorkProgressForm workProgressForm, final @PathVariable Long id) {

        log.info(" alter id {} form {}", id, workProgressForm);

        WorkProgress workProgress = WorkProgress
                .builder()
                .id(id)
                .description(workProgressForm.getDescription())
                .date(workProgressForm.getDate())
                .build();

       return workProgressService.alter(workProgress);
    }

    @GetMapping("/find-all")
    public List<WorkProgressResponse> findAll() {

        log.info(" findAll");

        return workProgressService.findAll();
    }

    @GetMapping("/find-by-id/{id}")
    public WorkProgressResponse findById(final @PathVariable(value = "id") Long id) {

        log.info(" findById {}", id);

        return workProgressService.findById(id);
    }

    @GetMapping("/find-by-date/{date}")
    public List<WorkProgressResponse> findByDate(final @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        log.info(" findByDate {}", date);

        return workProgressService.findByDate(date);
    }

    @GetMapping(value = {"/find-by-date-between/{initDate}/{finalDate}", "/find-by-date-between/"})
    public List<WorkProgressResponse> findByDateBetween(@PathVariable(value = "initDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate initDate,
                                                        @PathVariable(value = "finalDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finalDate) {

        if (initDate == null && finalDate == null) {
            log.info(" last 30 days");
            initDate = LocalDate.now().minusDays(30);
            finalDate = LocalDate.now();
        }

        log.info(" findByDateBetween initDate {} finalDate {}", initDate, finalDate);

        return workProgressService.findByDateBetween(initDate, finalDate);
    }

}
