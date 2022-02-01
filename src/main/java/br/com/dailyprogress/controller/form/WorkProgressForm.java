package br.com.dailyprogress.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkProgressForm {

    private String description;
    private LocalDate date;

}
