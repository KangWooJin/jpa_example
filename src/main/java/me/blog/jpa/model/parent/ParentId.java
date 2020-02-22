package me.blog.jpa.model.parent;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ParentId implements Serializable {
    private Long id1;
    private Long id2;
}
