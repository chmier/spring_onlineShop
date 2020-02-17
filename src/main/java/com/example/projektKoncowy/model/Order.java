package com.example.projektKoncowy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Order_Furniture")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;


    @NotBlank(message = "Giving the name is obligatory")
    private String name;
    private String lastname;
    private String street;
    private String City;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "")
    private String zip;

    @ManyToMany(targetEntity = Category.class)
    private List<Category> categories = new ArrayList<>();

    public void addDesign(Category design) {
        this.categories.add(design);
    }



}
