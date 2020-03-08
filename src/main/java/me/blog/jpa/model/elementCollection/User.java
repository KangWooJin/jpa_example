package me.blog.jpa.model.elementCollection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long price;

    @OneToOne
    @JoinColumn
    private UserInventory userInventory;

    @CollectionTable
    @ElementCollection
    List<String> interestList = new ArrayList<>();
}
