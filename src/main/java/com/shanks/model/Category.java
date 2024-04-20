package com.shanks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id ;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

}
