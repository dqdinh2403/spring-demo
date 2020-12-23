package com.example.springdemo.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Order {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Zip is required")
    private String zip;

}
