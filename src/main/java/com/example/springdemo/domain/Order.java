package com.example.springdemo.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Zip is required")
    private String zip;

    private List<Design> designs;


    public void addDesign(Design design){
        if(designs.isEmpty()){
            designs = new ArrayList<>();
        }

        designs.add(design);
    }

}
