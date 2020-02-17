package com.example.projektKoncowy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Furniture {

    @Id
    private String id;
    private String name;
    private TypeOfFurniture typeOfFurniture;


}
