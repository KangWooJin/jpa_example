package me.blog.jpa.model.elementCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserInventory {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
