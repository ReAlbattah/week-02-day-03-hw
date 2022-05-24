package com.example.springd3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
public class Employee {

    @NotNull(message = " id cannot be null")
    @Size(min = 2 , message = " id must be more than 2")
    private String id;

    @NotNull(message = "name cannot be null")
    @Size(min = 4 , message = "name must be more than 4")
    private String name;

    @NotNull(message = "age cannot be null")
    @Min(value = 25,message = " age must be more than 25 ")
    private Integer age;


    @AssertFalse(message = "must be false")
    private boolean onLeave;

    @NotNull(message = "employmentYear cannot be null")
    @Pattern(regexp = "year", message = "must be year")
    private String employmentYear;

    @NotNull(message = "annualLeave cannot be null")
    @PositiveOrZero(message = "must be number")
    private Integer annualLeave;

}
