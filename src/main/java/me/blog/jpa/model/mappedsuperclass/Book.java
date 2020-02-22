package me.blog.jpa.model.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = "book_id")) })
public class Book extends AbstractEntity {
    // Id와 time 상
}
