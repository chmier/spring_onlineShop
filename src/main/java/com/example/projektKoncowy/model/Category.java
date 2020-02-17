package com.example.projektKoncowy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must not be null")
    private String Name;

    @ManyToMany(targetEntity = Furniture.class)
    @Size(min = 1, message = "You have to choose one")
    private List<Furniture> furnitures;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
