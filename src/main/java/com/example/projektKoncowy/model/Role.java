package com.example.projektKoncowy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity (name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "name")
    private String name;



}
