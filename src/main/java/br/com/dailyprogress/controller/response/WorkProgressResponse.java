package br.com.dailyprogress.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkProgressResponse {

    private Long id;
    private String description;
    private LocalDate date;

}
