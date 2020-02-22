package me.blog.jpa.model.parent;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Parent {
    @EmbeddedId
    private ParentId id;
}
