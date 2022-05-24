package com.example.springd3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Park {

    @NotNull(message = "ID can't be null ")
    @Size(min = 2,message = "ID should be more than 2 ")
    private String rideID;

    @NotNull(message = "Name can't be null ")
    @Size(min = 4,message = "Name should be more than 4 ")
    private String rideName;

    @NotNull(message = "Type can't be null ")
    @Pattern(regexp = "rollercoaster|thriller|water")
    private String rideType;

    @NotNull(message = "Ticket can't be null ")
   // @Pattern(regexp = "0-9",message = "Ticket must be number")
   // @Digits(integer = 10,fraction = 0)
    @Min(0)
    private int ticket;

    @NotNull(message = "Ticket can't be null ")
    //@Pattern(regexp="0-9",message = " not allowed ")
    //@Digits(integer = 10,fraction = 0)
    @Min(0)
    private int price;
}
