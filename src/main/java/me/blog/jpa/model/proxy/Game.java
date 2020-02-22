package me.blog.jpa.model.proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
